package com.example.apidbfront;

import com.example.apidbfront.controller.HomeController;
import com.example.apidbfront.controller.IController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    private Stage primaryStage;

    public void switchScene(String fichierFxml, int v, int v1/*Scene scene*/) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fichierFxml));
        primaryStage.setScene(new Scene(fxmlLoader.load(), v, v1));
        IController controller = fxmlLoader.getController();
        controller.setMain(this);
        primaryStage.show();
    }

    @Override
    public void start(Stage stage) throws IOException {

        this.primaryStage = stage;
        stage.setTitle("Gestionnaire Utilisateurs");

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("homeView.fxml"));
        Parent root = fxmlLoader.load();
        HomeController homeController = fxmlLoader.getController();
        homeController.setMain(this);
        Scene homeScene = new Scene(root);

        stage.setScene(homeScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}