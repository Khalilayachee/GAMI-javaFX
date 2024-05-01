package Controller;

import Entity.Game;
import Outil.DataBase;
import Service.Game_s;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class Containergame {
    @FXML
    private ImageView imagev;

    @FXML
    private Label nameTF;

    @FXML
    private Label note;
    private Game games;
    @FXML
    private Label lienTF;
    private final Connection conn = DataBase.getInstance().getConn();
    private final Game_s gamesS = new Game_s(conn);
    public void setTournois(Game g) {
      this.games = g;
        nameTF.setText(g.getName());
        Image img = new Image("file:" + g.getImage());
        imagev.setImage(img);
        lienTF.setText(g.getLien());
    }
    @FXML
    void delete(ActionEvent event) {
        if (games!= null) {
            System.out.println("Deleting Game: " + games.toString());
            try {

                gamesS.delete(games.getId());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/showGame2.fxml"));
                Parent root = loader.load();
                GameShow gameShow = loader.getController();


                // Get the current stage and set the new scene
                Stage stage = (Stage) nameTF.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (SQLException e) {
                e.printStackTrace();

                // Handle any errors or exceptions
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("No Game selected.");
        }

    }
    private WebEngine engine;

    @FXML
    public void Play(ActionEvent event) throws IOException, SQLException {
        // Récupérer le lien du jeu depuis le champ de texte
        //String gameLink = games.getLien();

        // Charger la page PlayGame
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/playGame.fxml"));
        Parent root = loader.load();
        PlayGame playGameController = loader.getController();
        playGameController.setGame(games);

        // Charger le lien du jeu dans le WebView de PlayGame
       // playGameController.loadPage(gameLink);
        Scene scene = lienTF.getScene();

        // Afficher la scène PlayGame
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }



}
