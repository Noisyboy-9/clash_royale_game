module client {
    requires javafx.fxml;
    requires javafx.controls;
    requires core;

    opens controllers;
    opens controllers.menus;
    opens controllers.modes;
    opens controllers.authentication;
    opens controllers.modes.botControllers;
    opens controllers.modes.onlineControllers;
    opens controllers.modes.runnables;

    exports globals;
}