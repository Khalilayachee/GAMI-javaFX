package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Home extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * Initializes and displays the primary stage of the JavaFX application.
     * 
     * This method sets up the main window of the application by loading the FXML file,
     * creating a scene, setting it on the primary stage, and displaying the stage.
     * 
     * @param primaryStage The primary stage for this application, onto which
     *                     the application scene can be set.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"));
        try
        {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
