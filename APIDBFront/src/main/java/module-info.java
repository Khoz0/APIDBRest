module com.example.apidbfront {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.apidbfront to javafx.fxml;
    exports com.example.apidbfront;
    exports com.example.apidbfront.controller;
    opens com.example.apidbfront.controller to javafx.fxml;
}