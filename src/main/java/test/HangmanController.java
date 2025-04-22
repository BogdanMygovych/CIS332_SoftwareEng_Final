package test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class HangmanController {

    private final String[] wordList = {"EXAMPLE", "COMPUTER", "JAVAFX", "PROGRAM", "SCIENCE"};
    private String wordToGuess;
    private final Set<Character> guessedLetters = new HashSet<>();
    private final Set<Character> wrongGuesses = new HashSet<>();

    @FXML private Label wordDisplay;
    @FXML private Label incorrectGuesses;
    @FXML private Label hangmanGraphic;
    @FXML private TilePane letterButtons;

    private final String[] HANGMAN_STAGES = {
            "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
    };

    @FXML
    public void initialize() {
        resetGame();
    }

    private void resetGame() {
        guessedLetters.clear();
        wrongGuesses.clear();
        wordToGuess = wordList[new Random().nextInt(wordList.length)];

        letterButtons.getChildren().clear();
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            final char currentLetter = letter;
            Button btn = new Button(String.valueOf(currentLetter));
            btn.setOnAction(e -> handleGuess(currentLetter, btn));
            btn.getStyleClass().add("letter-button");
            letterButtons.getChildren().add(btn);
        }

        updateDisplay();
    }

    private void handleGuess(char letter, Button btn) {
        btn.setDisable(true);
        if (wordToGuess.indexOf(letter) >= 0) {
            guessedLetters.add(letter);
        } else {
            wrongGuesses.add(letter);
        }
        updateDisplay();
    }

    private void updateDisplay() {
        StringBuilder display = new StringBuilder();
        for (char c : wordToGuess.toCharArray()) {
            if (guessedLetters.contains(c)) {
                display.append(c).append(" ");
            } else {
                display.append("_ ");
            }
        }
        wordDisplay.setText(display.toString().trim());

        incorrectGuesses.setText(wrongGuesses.toString());
        hangmanGraphic.setText(HANGMAN_STAGES[Math.min(wrongGuesses.size(), HANGMAN_STAGES.length - 1)]);
    }

    @FXML
    private void onNewWordClick() {
        resetGame();
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
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
