module com {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.sql.rowset;
    requires org.jetbrains.annotations;
    requires java.desktop;
    requires mysql.connector.java;


    opens com to javafx.fxml;

    exports com.model;
    opens com.model to javafx.fxml;
    exports com.util;
    opens com.util to javafx.fxml;
    exports com.controller;
    opens com.controller to javafx.fxml;
    exports com;
}