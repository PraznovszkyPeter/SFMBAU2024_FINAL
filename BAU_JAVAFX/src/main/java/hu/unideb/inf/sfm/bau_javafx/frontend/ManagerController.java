package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.List;
import static hu.unideb.inf.sfm.bau_javafx.frontend.UserEditor.UserEdit;

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
        UserEdit(getClass(),loggedInUser.getUsername() + " adatainak változtatása", loggedInUser, this);
    }

    @FXML
    public void initialize() {
        forenameColumn.setCellValueFactory(cellData -> cellData.getValue().forenameProperty());
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        userTable.setItems(users);

        userTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showUserDetails(newValue);
        });

        resetButton.setDisable(true);
    }

    @FXML
    void CreateUser(ActionEvent event) throws Exception {
        UserEdit(getClass(),"Új felhasználó létrehozása", null, this);
    }

    @FXML
    void DeleteUser(ActionEvent event) {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            if (JavaFXMain.manager.DeleteUser(selectedUser)){
                new Alerts().InformationAlert("Felhasználó törlés", "Sikeresen törölve lett a felhasználó!");
            }
            else {
                new Alerts().ErrorAlert("Felhasználó törlés", "Nem sikerült a felhasználó törlése!");
            }
        }
        this.reloadUsers();
    }

    @FXML
    void EditUser(ActionEvent event) throws Exception {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            UserEdit(getClass(),selectedUser.getUsername() + " szerkesztése", selectedUser, this);
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

    public void reloadUsers() {
        users.clear();

        List<User> updatedUserList = JavaFXMain.manager.getUsers(this.loggedInUser.getUsername());

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