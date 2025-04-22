


package test;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LandingPageController {



    @FXML
    private void onHelloButtonClick() {
        System.out.println("Hello button clicked!");
    }

    @FXML
    private void onPlayHangmanClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Hangman.fxml"));
            Scene scene = new Scene((Parent)loader.load(), (double)600.0F, (double)1000.0F);
            scene.getStylesheets().add(this.getClass().getResource("/styles.css").toExternalForm());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Hangman Game");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @FXML private Label welcomeText;
    @FXML private TextField nameField;
    @FXML private ImageView logoImage;

    @FXML
    public void initialize() {
        logoImage.setImage(new Image(getClass().getResourceAsStream("/images/logo.png"))); // Place your logo as logo.png
    }

    @FXML
    private void onSetNameClick() {
        String name = nameField.getText().trim();
        if (!name.isEmpty()) {
            welcomeText.setText("Welcome " + name + " to Swiss Army App!");
        }
    }


    @FXML
    private void onSportsClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Sports.fxml"));
            Scene scene = new Scene((Parent)loader.load(), (double)800.0F, (double)600.0F);
            scene.getStylesheets().add(this.getClass().getResource("/styles.css").toExternalForm());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Sports");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void onVerseClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VerseOfTheDay.fxml"));
            Scene scene = new Scene(loader.load(), 1200, 600); // adjust dimensions if needed
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Verse of the Day");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void onPlayTriviaGameClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TriviaGame.fxml"));
            Scene scene = new Scene((Parent)loader.load(), (double)1200.0F, (double)600.0F);
            scene.getStylesheets().add(this.getClass().getResource("/styles.css").toExternalForm());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("TriviaGame");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void openVerseOfTheDay(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("VerseOfTheDay.fxml"));
            Scene scene = new Scene((Parent)loader.load(), (double)600.0F, (double)1000.0F);
            scene.getStylesheets().add(this.getClass().getResource("/styles.css").toExternalForm());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Verse of the Day");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void onSettingsClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Settings.fxml"));
            Scene scene = new Scene((Parent)loader.load(), (double)800.0F, (double)600.0F);
            scene.getStylesheets().add(this.getClass().getResource("/styles.css").toExternalForm());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Settings");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void openTipCalculator(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("TipCalculator.fxml"));
            Scene scene = new Scene((Parent)loader.load(), (double)600.0F, (double)1000.0F);
            scene.getStylesheets().add(this.getClass().getResource("/styles.css").toExternalForm());
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Tip Calculator");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

