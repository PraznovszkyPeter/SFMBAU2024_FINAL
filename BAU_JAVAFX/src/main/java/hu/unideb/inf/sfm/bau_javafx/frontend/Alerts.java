package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.model.User;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.security.AsymmetricKey;

public class Alerts {
    private String header;
    private String message;


    public Alerts() {
        //empty constructor
    }

    public void ErrorAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Hiba");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(new ButtonType("Bezárás"));
        alert.showAndWait();
    }

    public void LoginAlert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("HIBA");
        alert.setHeaderText("Nem megfelelő bejelentkezési paraméterek!");
        alert.setContentText(message);
        alert.getButtonTypes().setAll(new ButtonType("Bezárás"));
        alert.showAndWait();
    }

    public void InformationAlert(String header, String message)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Információ");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(new ButtonType("Bezárás"));
        alert.showAndWait();
    }

    private boolean PasswordSetting(User user){
        String password = Password.SetPassword("Adja meg új jelszavát!");
        user.setPassword(password);
        return JavaFXMain.manager.saveUser(user);
    }

    public void ResetPasswordAlert(User user)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Jelszóváltoztatás");
        alert.setHeaderText("Üdvözöljük! Jelszóváltoztatást kért!");
        alert.setContentText(message);
        alert.getButtonTypes().setAll(new ButtonType("Bezárás"));
        alert.showAndWait();
        user.setResetPassword(false);

        if (!PasswordSetting(user)){
            new Alerts().ErrorAlert("Mentési hiba","Nem sikerült elmenteni a változásokat! Próbálja később");
            return;
        }
        new Alerts().InformationAlert("Megváltozott jelszó", "Kérjük lépjen be az új bejelentkezési adatokkal!");
    }

    public void FirstLoginAlert(User user)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Jelszóváltoztatás");
        alert.setHeaderText("Üdvözöljük! Önnek ez az első alkalma itt.");
        alert.setContentText("Kérjük adjon meg egy új jelszót, amelyet csak Ön tud!");
        alert.getButtonTypes().setAll(new ButtonType("Bezárás"));
        alert.showAndWait();
        user.setFirstLogin(false);

        if (!PasswordSetting(user)){
            new Alerts().ErrorAlert("Mentési hiba","Nem sikerült elmenteni a változásokat! Próbálja később");
            return;
        }
        new Alerts().InformationAlert("Megváltozott jelszó", "Kérjük lépjen be az új bejelentkezési adatokkal!");
    }

    public void VisitAlert(String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Vizsgálat lezárása");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(new ButtonType("Nem jelent meg"), new ButtonType("Gyógyszer felírása"), new ButtonType("Műtét javasolt"));
        alert.showAndWait();
    }

}
