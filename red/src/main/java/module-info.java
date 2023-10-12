module com.example.red {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.red to javafx.fxml;
    exports com.example.red;
}