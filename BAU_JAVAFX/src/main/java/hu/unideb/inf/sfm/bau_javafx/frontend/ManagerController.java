package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.model.User;
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
import java.security.AsymmetricKey;
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
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> forenameColumn;

    @FXML
    private TableColumn<User, String> surnameColumn;

    @FXML
    public void initialize() {
        ObservableList<User> users = refreshTable();

        userTable.setItems(users);
        forenameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getForename()));
        surnameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSurname()));

        userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showUserDetails(newValue);  // Show selected user's details in labels
        });
    }

    public void showUserDetails(User user) {
        if (user != null) {
            forename.setText(user.getForename());
            surname.setText(user.getSurname());
            username.setText(user.getUsername());
            reg_num.setText(user.getRegistrationNumber() == null ? "" : Integer.toString(user.getRegistrationNumber()));
            userType.setText(user.getUsertype().toString());
        }
        else {
            forename.setText("");
            surname.setText("");
            username.setText("");
            reg_num.setText("");
            userType.setText("");
        }
    }

    @FXML
    void CreateUser(ActionEvent event) throws Exception {
        UserEdit("Új felhasználó létrehozása", null);
        refreshTable();
    }

    @FXML
    void DeleteUser(ActionEvent event) {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            JavaFXMain.manager.DeleteUser(selectedUser);
        }
        refreshTable();
    }

    @FXML
    void EditUser(ActionEvent event) throws Exception {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            UserEdit(selectedUser.getUsername() + " szerkesztése", selectedUser);
        }
        refreshTable();
    }

    @FXML
    void resetPassword(ActionEvent event) {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            selectedUser.setPassword(Password.SetPassword(selectedUser.getUsername() + " jelszavának visszaállítása."));
            selectedUser.setResetPassword(true);
            if (!JavaFXMain.manager.saveUser(selectedUser)) {
                new Alerts().ErrorAlert("Mentési hiba","Nem sikerült elmenteni a változásokat! Próbálja később");
                return;
            }
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
    private ObservableList<User> refreshTable() {
        List<User> userList = JavaFXMain.manager.getUsers();  // Get the List<User>
        System.out.println(userList.size());
        return FXCollections.observableArrayList(userList);  // Convert List<User> to ObservableList<User>
    }
}
