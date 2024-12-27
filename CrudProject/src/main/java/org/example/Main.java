package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

import static org.example.DataBase.DataBaseCreate.createDatabase;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        createDatabase();


        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/NotesGui.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Notes");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
