package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {
    /**
     * Opens and displays a new post in a separate window.
     * 
     * This method loads the FXML file for displaying a post, creates a new stage,
     * sets the scene with the loaded FXML content, and shows the stage.
     * 
     * @param actionEvent The event that triggered this method call
     * @throws IOException If there is an error loading the FXML file
     */
    public void openPost(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AfficherPost.fxml"));
        try {
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
