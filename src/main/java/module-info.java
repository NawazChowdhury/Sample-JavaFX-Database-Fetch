module com.example.csd214w24dbclass2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.csd214w24dbclass2 to javafx.fxml;
    exports com.example.csd214w24dbclass2;
}