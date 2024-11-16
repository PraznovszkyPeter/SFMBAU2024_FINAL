package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.model.User;
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
    private User loggedInUser;

    @FXML
    void reloadData(ActionEvent event) {
        this.reloadUsers();
    }

    @FXML
    void changePassword(ActionEvent event) {
        Password.ChangePassword(loggedInUser);
    }

    @FXML
    void showMyData(ActionEvent event) throws Exception {
        UserEdit(loggedInUser.getUsername() + " adatainak változtatása", loggedInUser);
    }

    @FXML
    public void initialize() {
        // Set up TableView columns and bindings
        forenameColumn.setCellValueFactory(cellData -> cellData.getValue().forenameProperty());
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        userTable.setItems(users);

        // Add listener for table selection
        userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showUserDetails(newValue);
        });

        // Disable reset button initially
        resetButton.setDisable(true);
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
    }

    @FXML
    void EditUser(ActionEvent event) throws Exception {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            UserEdit(selectedUser.getUsername() + " szerkesztése", selectedUser);
        }
    }

    @FXML
    void resetPassword(ActionEvent event) {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
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

    public void UserEdit(String header, User selected) throws Exception {
        try {
            // Load the UserEditorFXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml_files/UserEditorFXML.fxml"));

            // Load the FXML into a Parent root node
            Parent root = fxmlLoader.load();

            // Get the controller that was injected by FXMLLoader
            UserEditorController controller = fxmlLoader.getController();

            controller.setUser(selected);

            Stage managerStage = new Stage();
            managerStage.setTitle(header);
            managerStage.setScene(new Scene(root));

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

        List<User> updatedUserList = JavaFXMain.manager.getUsers(loggedInUser.getUsername());

        if (updatedUserList != null) {
            users.setAll(updatedUserList);
        }

        userTable.refresh();

        if (!users.isEmpty()) {
            userTable.getSelectionModel().clearSelection();
            showUserDetails(null);
        }
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.reloadUsers();
    }
}
