module traffic {
    requires javafx.controls;
    requires javafx.fxml;

    opens traffic to javafx.fxml;
    exports traffic;
}
