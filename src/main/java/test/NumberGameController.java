package test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Random;

public class NumberGameController {

    @FXML private TextField guessInput;
    @FXML private Label feedbackLabel;
    @FXML private Label instructionLabel;

    private int targetNumber;

    @FXML
    public void initialize() {
        Random rand = new Random();
        targetNumber = rand.nextInt(20) + 1;

        javafx.application.Platform.runLater(() -> {
            Scene scene = guessInput.getScene();
            if (scene != null) {
                UserSession.applySettings(scene);
            }
        });
    }

    @FXML
    private void onSubmitGuess() {
        try {
            int guess = Integer.parseInt(guessInput.getText().trim());
            if (guess < 1 || guess > 20) {
                feedbackLabel.setText("Please enter a number between 1 and 20.");
            } else if (guess < targetNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else if (guess > targetNumber) {
                feedbackLabel.setText("Too high! Try again.");
            } else {
                feedbackLabel.setText("Correct! You guessed it!");
            }
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Invalid input. Enter a valid number.");
        }
    }

    @FXML
    private void onHomeClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Landing-Page.fxml"));
            Scene scene = new Scene(loader.load(), 600, 1000);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Swiss Army App");
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
