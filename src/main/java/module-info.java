module java.test.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens java.test to javafx.fxml;
    exports java.test;
}
