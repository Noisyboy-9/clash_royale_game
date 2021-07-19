module client {
    requires javafx.fxml;
    requires javafx.controls;
    requires core;
    
    opens controllers;
    opens controllers.menus;
    opens controllers.modes;
    opens controllers.authentication;
}