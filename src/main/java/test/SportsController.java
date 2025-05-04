package test;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SportsController {

    @FXML private ComboBox<String> teamComboBox;
    @FXML private ComboBox<String> statComboBox;
    @FXML private LineChart<String, Number> statChart;

    private static final String API_URL = "https://www.thesportsdb.com/api/v1/json/3/eventsseason.php?id=4328&s=2024-2025";
    private static final String[] STATS = {"Home Score", "Away Score", "Attendance"};

    @FXML
    public void initialize() {
        statComboBox.getItems().addAll(STATS);
        teamComboBox.getItems().addAll(
                "Arsenal", "Chelsea", "Manchester United", "Manchester City", "Liverpool"
        );

        teamComboBox.setOnAction(e -> fetchAndRender());
        statComboBox.setOnAction(e -> fetchAndRender());
    }

    private void fetchAndRender() {
        String team = teamComboBox.getValue();
        String selectedStat = statComboBox.getValue();

        if (team == null || selectedStat == null) return;

        String statKey = switch (selectedStat) {
            case "Home Score" -> "intHomeScore";
            case "Away Score" -> "intAwayScore";
            case "Attendance" -> "intAttendance";
            default -> "";
        };

        new Thread(() -> {
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(API_URL).openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();

                JSONArray events = new JSONObject(result.toString()).getJSONArray("events");
                XYChart.Series<String, Number> series = new XYChart.Series<>();

                for (int i = 0; i < events.length(); i++) {
                    JSONObject event = events.getJSONObject(i);
                    String homeTeam = event.optString("strHomeTeam");
                    String awayTeam = event.optString("strAwayTeam");

                    if (team.equalsIgnoreCase(homeTeam) || team.equalsIgnoreCase(awayTeam)) {
                        String eventName = event.optString("strEvent");
                        String value = event.optString(statKey);

                        if (!value.isEmpty() && value.matches("\\d+")) {
                            series.getData().add(new XYChart.Data<>(eventName, Integer.parseInt(value)));
                        }
                    }
                }

                Platform.runLater(() -> {
                    statChart.getData().clear();
                    statChart.getData().add(series);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
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
