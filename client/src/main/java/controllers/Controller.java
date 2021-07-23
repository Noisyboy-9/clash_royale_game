package controllers;

import controllers.menus.SceneController;
import database.queryBuilders.CardQueryBuilder;
import database.queryBuilders.HistoryQueryBuilder;
import javafx.stage.Stage;

/**
 * The interface Controller.
 */
public interface Controller {
    /**
     * The constant VIEW_PATH.
     */
    String VIEW_PATH = Controller.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("target/classes/", "src/main/java/views/").substring(1);
    /**
     * The constant RESOURCE_PATH.
     */
    String RESOURCE_PATH = Controller.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("target/classes/", "src/main/resources/").substring(1);
    /**
     * The constant STAGE.
     */
    Stage STAGE = new Stage();
    /**
     * The constant SCENE_CONTROLLER.
     */
    SceneController SCENE_CONTROLLER = new SceneController();
    /**
     * The constant CARD_QUERY_BUILDER.
     */
    CardQueryBuilder CARD_QUERY_BUILDER = CardQueryBuilder.getSingletonInstance();
    /**
     * The constant HISTORY_QUERY_BUILDER.
     */
    HistoryQueryBuilder HISTORY_QUERY_BUILDER = HistoryQueryBuilder.getInstance();
}
