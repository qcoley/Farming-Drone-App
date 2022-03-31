package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.view.DashboardController;

import java.io.IOException;



// Team 2, Farming Drone App  -----------------------------------------------------------------------------------------------------------------------------------
public class MainApp extends Application {

    private Stage primaryStage;


    public MainApp() { }


    @Override
    public void start(Stage primaryStage) {

    	this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FarmDroneApp");

        initRootLayout(); }

    // ----------------------------------------------------------------------------------------------------------------------------------------------------------
    public void initRootLayout() {

    	try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Dashboard.fxml"));
            BorderPane rootLayout = loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            DashboardController controller = loader.getController();
            controller.setMainApp();

    	} catch (IOException e) { e.printStackTrace(); } }


    public static void main(String[] args) { launch(args); }
    
}



