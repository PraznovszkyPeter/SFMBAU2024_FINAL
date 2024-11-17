package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;

public class Password {

    public static String SetPassword(String message) {
        final String[] password = new String[1];  // Used to store the password

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Jelszóbeállítás");
        alert.setHeaderText(message);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Jelszó");
        alert.getDialogPane().setContent(passwordField);

        ButtonType saveButton = new ButtonType("Mentés");
        alert.getButtonTypes().setAll(saveButton);

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
            } else {
                password[0] = null;
            }
        });

        return password[0];
    }

    public static void ChangePassword(User user) {
        final String[] password = new String[1];  // Used to store the password

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Jelszóbeállítás");
        alert.setHeaderText("Adja meg jelenlegi jelszavát!");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Jelszó");
        alert.getDialogPane().setContent(passwordField);

        ButtonType saveButton = new ButtonType("Ellenőrzés");
        alert.getButtonTypes().setAll(saveButton);

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
            } else {
                password[0] = null;
            }
        });

        if (user.checkPassword(password[0])) {
            String newPassword = Password.SetPassword(user.getUsername() + " jelszóváltoztatása.");
            if (newPassword != null) {
                user.setPassword(newPassword);
                if (JavaFXMain.manager.saveUser(user)) {
                    new Alerts().InformationAlert("Jelszóváltoztatás", "Sikeres megadás.");
                } else {
                    new Alerts().ErrorAlert("Mentési hiba", "Nem sikerült elmenteni a változásokat! Próbálja később");
                }
            } else {
                new Alerts().InformationAlert("Jelszóváltoztatás", "Meghiúsult változtatás.");
            }
        } else {
            new Alerts().InformationAlert("Jelszóváltoztatás", "Hibás jelszót adott meg!");
        }
    }
}