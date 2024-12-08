package hu.unideb.inf.sfm.bau_javafx.frontend;

import hu.unideb.inf.sfm.bau_javafx.db.Manager;
import hu.unideb.inf.sfm.bau_javafx.db.SpringManager;
import hu.unideb.inf.sfm.bau_javafx.model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class JavaFXMain extends Application {
    static Manager manager = new SpringManager();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml_files/LoginFXML.fxml"));
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        Rectangle clip = new Rectangle(scene.getWidth(), scene.getHeight());
        clip.setArcWidth(40);
        clip.setArcHeight(40);
        root.setClip(clip);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();

        manager.start();
    }
}
