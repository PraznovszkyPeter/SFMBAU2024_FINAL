package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class UserEditor {
    public static void UserEdit(Class<?> clas, String header, User selected) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(clas.getResource("/fxml_files/UserEditorFXML.fxml"));

            Parent root = fxmlLoader.load();

            UserEditorController controller = fxmlLoader.getController();

            controller.setUser(selected);

            Stage managerStage = new Stage();
            managerStage.setTitle(header);
            managerStage.setScene(new Scene(root));

            Image image = new Image("/images/baulog.png");
            managerStage.getIcons().add(image);
            managerStage.show();
        } catch (IOException e) {
            System.err.println("Error loading UserEditorFXML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}