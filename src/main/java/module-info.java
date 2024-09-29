module com.tolmic.puzzle15 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.tolmic.puzzle15 to javafx.fxml;
    exports com.tolmic.puzzle15;
}