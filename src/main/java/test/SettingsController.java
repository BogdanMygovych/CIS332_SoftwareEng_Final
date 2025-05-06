package test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.List;

public class SettingsController {

    @FXML private TextField nameField;
    @FXML private ColorPicker fontColorPicker;
    @FXML private Slider fontSizeSlider;

    @FXML
    private void onUpdateNameClick() {
        String newName = nameField.getText().trim();
        if (!newName.isEmpty()) {
            UserSession.setNickname(newName);
        }
    }

    @FXML
    private void onLightModeClick() {
        UserSession.setDarkMode(false);
        applyToAllScenes();
    }

    @FXML
    private void onDarkModeClick() {
        UserSession.setDarkMode(true);
        applyToAllScenes();
    }

    @FXML
    private void onFontColorChange() {
        UserSession.setFontColor(fontColorPicker.getValue());
        applyToAllScenes();
    }

    @FXML
    private void onFontSizeChange() {
        UserSession.setFontSize(fontSizeSlider.getValue());
        applyToAllScenes();
    }

    @FXML private ColorPicker backgroundColorPicker;

    @FXML
    private void onBackgroundColorChange() {
        UserSession.setBackgroundColor(backgroundColorPicker.getValue());
        applyToAllScenes();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void applyToAllScenes() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        Scene scene = stage.getScene();
        UserSession.applySettings(scene);

        applyFontColorToAllNodes(scene.getRoot(), UserSession.getFontColor());
    }

    private void applyFontColorToAllNodes(Node node, Color color) {
        String hexColor = String.format("#%02x%02x%02x",
                (int)(color.getRed() * 255),
                (int)(color.getGreen() * 255),
                (int)(color.getBlue() * 255)
        );


        if (node instanceof Labeled labeled) {
            labeled.setStyle("-fx-text-fill: " + hexColor + ";");
        } else if (node instanceof TextInputControl textInput) {
            textInput.setStyle("-fx-text-fill: " + hexColor + ";");
        }

        if (node instanceof Parent parent) {
            for (Node child : parent.getChildrenUnmodifiable()) {
                applyFontColorToAllNodes(child, color);
            }
        }
    }
}
