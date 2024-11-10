package hu.unideb.inf.sfm.bau_javafx.frontend;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;

public class Password {

    public static String SetPassword(String message) {
        final String[] password = new String[1];  // Used to store the password

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Jelszóbeállítás");
        alert.setHeaderText(message);

        //Adding a passwordField
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Jelszó");
        alert.getDialogPane().setContent(passwordField);

        //Only 1 button, called Save ("Mentés")
        ButtonType saveButton = new ButtonType("Mentés");
        alert.getButtonTypes().setAll(saveButton);

        //Disable button, until the password is least 8 character
        alert.getDialogPane().lookupButton(saveButton).setDisable(true);
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() >= 8) {
                alert.getDialogPane().lookupButton(saveButton).setDisable(false);
            } else {
                alert.getDialogPane().lookupButton(saveButton).setDisable(true);
            }
        });

        alert.showAndWait().ifPresent(response -> {
            if (response == saveButton) {
                password[0] = passwordField.getText();
            }
            else{
                password[0] = null;
            }
        });

        // Return the password after the alert is closed
        return password[0];
    }
}
