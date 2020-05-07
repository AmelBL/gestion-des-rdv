package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("principale.fxml"));
        primaryStage.setTitle("gestion des rendez-vous");
        primaryStage.setScene(new Scene(root, 853, 475));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
