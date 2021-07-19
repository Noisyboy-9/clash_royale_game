package controllers;

import controllers.menus.SceneController;
import database.queryBuilders.CardQueryBuilder;
import database.queryBuilders.HistoryQueryBuilder;
import javafx.stage.Stage;

public interface Controller {
    String VIEW_PATH = Controller.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("target/classes/", "src/main/java/views/").substring(1);
    String RESOURCE_PATH = Controller.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("target/classes/", "src/main/resources/").substring(1);
    Stage STAGE = new Stage();
    SceneController SCENE_CONTROLLER = new SceneController();
    CardQueryBuilder CARD_QUERY_BUILDER = CardQueryBuilder.getSingletonInstance();
    HistoryQueryBuilder HISTORY_QUERY_BUILDER = HistoryQueryBuilder.getInstance();
}
