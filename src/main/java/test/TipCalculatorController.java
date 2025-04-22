package test;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class TipCalculatorController {

    @FXML private TextField billAmountField;
    @FXML private TextField tipPercentageField;
    @FXML private Label resultLabel;

    @FXML
    private void onCalculateClick() {
        try {
            double bill = Double.parseDouble(billAmountField.getText());
            double tipPercent = Double.parseDouble(tipPercentageField.getText());
            double tipAmount = bill * tipPercent / 100;
            double total = bill + tipAmount;

            resultLabel.setText(String.format("Tip: $%.2f | Total: $%.2f", tipAmount, total));
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input. Please enter valid numbers.");
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
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}