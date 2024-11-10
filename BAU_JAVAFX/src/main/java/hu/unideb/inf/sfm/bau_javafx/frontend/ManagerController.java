package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.model.User;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ManagerController {
    @FXML
    private Label forename;
    @FXML
    private Label reg_num;
    @FXML
    private Label surname;
    @FXML
    private Label username;

    @FXML
    private Label userType;

    @FXML
    private Button resetButton;

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> forenameColumn;
    @FXML
    private TableColumn<User, String> surnameColumn;

    private ObservableList<User> users = FXCollections.observableArrayList();

    private ScheduledExecutorService scheduler;
    private Runnable updateUsersTask;
    private Stage stage;

    @FXML
    public void initialize() {
        // Set up columns
        forenameColumn.setCellValueFactory(cellData -> cellData.getValue().forenameProperty());
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());

        // Bind the ObservableList to the TableView
        userTable.setItems(users);

        // Add listener for table selection
        userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showUserDetails(newValue);  // Show selected user's details in labels
        });

        // Add listener to handle button enable/disable state based on selection and firstLogin
        resetButton.setDisable(true);

        scheduler = Executors.newSingleThreadScheduledExecutor();
        updateUsersTask = () -> {
            // Check the task is running on the JavaFX thread
            javafx.application.Platform.runLater(this::reloadUsers);
        };
        scheduler.scheduleAtFixedRate(updateUsersTask, 0, 30, TimeUnit.SECONDS);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setOnCloseRequest(event -> {
            stopScheduler();  // Stop the scheduler before exiting
            Platform.exit();  // Forcefully exit the JavaFX application
            System.exit(0);  // Ensures the JVM terminates after closing the JavaFX application
        });
    }



    private void stopScheduler() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();  // Stop any running tasks and prevent future tasks
            System.out.println("Scheduler has been stopped.");
        }
    }

    public void showUserDetails(User user) {
        forename.setText("");
        surname.setText("");
        username.setText("");
        reg_num.setText("");
        userType.setText("");
        resetButton.setDisable(true);

        if (user != null) {
            forename.setText(user.forenameProperty().get());
            surname.setText(user.surnameProperty().get());
            username.setText(user.usernameProperty().get());
            reg_num.setText(user.registrationNumberProperty().get() == null ? "" : Integer.toString(user.registrationNumberProperty().get()));
            userType.setText(user.usertypeProperty().get().toString());

            resetButton.setDisable(!user.resetPasswordProperty().get());
        }
    }

    @FXML
    void CreateUser(ActionEvent event) throws Exception {
        UserEdit("Új felhasználó létrehozása", null);
    }

    @FXML
    void DeleteUser(ActionEvent event) {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            JavaFXMain.manager.DeleteUser(selectedUser);
        }
        this.reloadUsers();
        resetUserListTimer();
    }

    @FXML
    void EditUser(ActionEvent event) throws Exception {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            UserEdit(selectedUser.getUsername() + " szerkesztése", selectedUser);
        }
    }

    public void UserEdit(String header, User selected) throws Exception {
        try {
            // Load the UserEditorFXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml_files/UserEditorFXML.fxml"));

            // Load the FXML into a Parent root node
            Parent root = fxmlLoader.load();

            // Get the controller that was injected by FXMLLoader
            UserEditorController controller = fxmlLoader.getController();

            // Set the user in the controller (you can pass the selected user here)
            controller.setUser(selected);
            controller.setManagerController(this);

            // Create a new stage and set the scene
            Stage managerStage = new Stage();
            managerStage.setTitle(header);
            managerStage.setScene(new Scene(root));

            // Set the icon for the window
            Image image = new Image(getClass().getResourceAsStream("/images/baulog.png"));
            managerStage.getIcons().add(image);
            // Show the stage (open the window)
            managerStage.show();
        } catch (IOException e) {
            System.err.println("Error loading UserEditorFXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void reloadUsers() {
        users.clear();

        // Log the current state of the backend data
        System.out.println("Fetching updated users...");
        List<User> updatedUserList = JavaFXMain.manager.getUsers();

        if (updatedUserList != null) {
            users.setAll(updatedUserList);
        }

        // Log the users list
        System.out.println("Loaded users: " + updatedUserList);

        // Refresh the table view
        userTable.refresh();

        if (!users.isEmpty()) {
            userTable.getSelectionModel().clearSelection();
            showUserDetails(null);  // Clear the details when no user is selected
        }
    }

    @FXML
    void resetPassword(ActionEvent event) {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            // Reset the password and flag the user for reset
            String newPassword = Password.SetPassword(selectedUser.getUsername() + " jelszavának visszaállítása.");
            if (newPassword != null) {
                selectedUser.setPassword(newPassword);
                selectedUser.setResetPassword(true);
                if (JavaFXMain.manager.saveUser(selectedUser)) {
                    reloadUsers();
                    new Alerts().InformationAlert("Jelszóváltoztatás", "Sikeres megadás.");
                } else {
                    new Alerts().ErrorAlert("Mentési hiba", "Nem sikerült elmenteni a változásokat! Próbálja később");
                }
            }
            else {
                new Alerts().InformationAlert("Jelszóváltoztatás", "Meghiúsult változtatás.");
            }
        }
    }

    public void resetUserListTimer() {
        if (scheduler != null && !scheduler.isShutdown()) {
            // If a task is already running, cancel it
            scheduler.shutdownNow();  // Shut down and cancel any current tasks
            scheduler = Executors.newSingleThreadScheduledExecutor();  // Reinitialize the scheduler
        }

        // Schedule the task again
        scheduler.scheduleAtFixedRate(updateUsersTask, 0, 30, TimeUnit.SECONDS);
    }
}
