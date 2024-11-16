package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.db.Manager;
import hu.unideb.inf.sfm.bau_javafx.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static hu.unideb.inf.sfm.bau_javafx.frontend.Password.SetPassword;


public class UserEditorController {
    @FXML
    private TextField forenameText;

    @FXML
    private TextField registrationNumber;

    @FXML
    private TextField surnameText;

    @FXML
    private ComboBox<User.usertype> userTypes;

    @FXML
    private TextField usernameText;

    @FXML
    private Button saveButton;

    private User user = null;

    @FXML
    void Cancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void saveUser(ActionEvent event) {
        this.user.setUsername(usernameText.getText());
        this.user.setForename(forenameText.getText());
        this.user.setSurname(surnameText.getText());
        this.user.setUsertype(userTypes.getValue());
        this.user.setRegistrationNumber(registrationNumber.getText().isEmpty() ? null : Integer.valueOf(registrationNumber.getText().trim()));

        if (this.user.getPassword() == null) {
            this.user.setPassword(SetPassword("Adja meg a létrejövő felhasználó jelszavát!"));
        }

        JavaFXMain.manager.saveUser(this.user);
        ManagerController managerController = new ManagerController();
        managerController.reloadUsers();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {
        saveButton.setDisable(true);

        userTypes.setItems(FXCollections.observableArrayList(User.usertype.values()));
        userTypes.setValue(User.usertype.DOCTOR); // or whatever is suitable as the default
        userTypes.valueProperty().addListener((observable, oldValue, newValue) -> {
            handleUserTypeSelection(newValue); // Call method to handle the logic when value changes
        });

        usernameText.textProperty().addListener((observable, oldValue, newValue) -> checkFields());
        surnameText.textProperty().addListener((observable, oldValue, newValue) -> checkFields());
        forenameText.textProperty().addListener((observable, oldValue, newValue) -> checkFields());
        registrationNumber.textProperty().addListener((observable, oldValue, newValue) -> checkFields());
        userTypes.valueProperty().addListener((observable, oldValue, newValue) -> checkFields());
    }

    private void checkFields() {
        // Check if all required fields are non-empty
        boolean allFieldsFilled = !isNullOrBlank(usernameText.getText()) &&
                !isNullOrBlank(surnameText.getText()) &&
                !isNullOrBlank(forenameText.getText()) &&
                (userTypes.getValue() == User.usertype.MANAGER || isNumber(registrationNumber.getText()));

        // Enable or disable the Save button based on whether all fields are filled
        saveButton.setDisable(!allFieldsFilled);
    }
    private boolean isNullOrBlank(String text) {
        return text == null || text.trim().isEmpty();
    }

    private boolean isNumber(String text) {
        try {
            Integer.parseInt(text);  // Try to parse the text as an integer
            return true;  // If it is a valid integer, set isNumber to true
        } catch (NumberFormatException e) {
            return false;  // If it's not a valid integer, catch the exception and set isNumber to false
        }
    }


    private void handleUserTypeSelection(User.usertype selectedType) {
        if (selectedType == User.usertype.MANAGER) {
            // Disable registration number if 'MANAGER' is selected
            registrationNumber.setDisable(true);
            registrationNumber.setText("");
        } else {
            // Enable registration number if 'DOCTOR' or other value is selected
            registrationNumber.setDisable(false);
        }
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null) {
            usernameText.setText(this.user.getUsername());
            surnameText.setText(this.user.getSurname());
            forenameText.setText(this.user.getForename());
            userTypes.setValue(this.user.getUsertype());
            if (this.user.getUsertype() == User.usertype.MANAGER) {
                registrationNumber.setDisable(true);
            } else {
                registrationNumber.setDisable(false);
                registrationNumber.setText(this.user.getRegistrationNumber().toString());
            }
        }
        else {
            this.user = new User();
        }
    }
}
