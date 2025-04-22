module test {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    opens test to javafx.fxml;
    exports test;
}
