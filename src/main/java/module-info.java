module com.example.snakeladderr {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snakeladderr to javafx.fxml;
    exports com.example.snakeladderr;
}