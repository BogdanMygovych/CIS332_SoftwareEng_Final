package test;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.*;
import java.util.*;

public class VerseOfTheDayController {

    @FXML private Label verseText;
    @FXML private Label verseReference;
    @FXML private ImageView verseImage;

    private final List<String> imagePaths = List.of(
            "/images/verse1.png", // Psalm 119:105
            "/images/verse2.png", // Jeremiah 29:11
            "/images/verse3.png", // Psalm 46:10
            "/images/verse4.png"  // Proverbs 3:5
    );

    private final List<String> verses = List.of(
            "Your word is a lamp to my feet, and a light to my path.|Psalm 119:105",
            "For I know the plans I have for you, declares the Lord.|Jeremiah 29:11",
            "Be still, and know that I am God.|Psalm 46:10",
            "Trust in the Lord with all your heart.|Proverbs 3:5"
    );

    @FXML
    public void initialize() {
        loadRandomVerseAndImage();
    }

    private void loadRandomVerseAndImage() {
        int index = new Random().nextInt(verses.size());

        String[] parts = verses.get(index).split("\\|");
        verseText.setText("\"" + parts[0].trim() + "\"");
        verseReference.setText(parts.length > 1 ? parts[1].trim() : "");

        InputStream imgStream = getClass().getResourceAsStream(imagePaths.get(index));
        if (imgStream != null) {
            verseImage.setImage(new Image(imgStream));
        } else {
            System.err.println("Image not found at: " + imagePaths.get(index));
        }
    }
    @FXML
    private void onNextVerseClick() {
        loadRandomVerseAndImage();
    }

    @FXML
    private void onHomeClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Landing-Page.fxml"));
            Scene scene = new Scene(loader.load(), 600, 1000);
            scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Swiss Army App");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
