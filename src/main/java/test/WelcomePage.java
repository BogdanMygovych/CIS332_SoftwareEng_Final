package test;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;

public class WelcomePage {

    @FXML
    private TextField nicknameField;

    @FXML
    private ImageView logoImageView;

    @FXML
    public void initialize() {
        Image logo = new Image(getClass().getResourceAsStream("/images/logo.png"));
        logoImageView.setImage(logo);
    }

    @FXML
    private void handleSetName(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Landing-Page.fxml"));
            Parent root = loader.load();

            LandingPageController controller = loader.getController();
            controller.setWelcomeName(nicknameField.getText());

            Scene landingScene = new Scene(root, 600, 1000);
            landingScene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(landingScene);
            stage.setTitle("Swiss Army App - Home");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}