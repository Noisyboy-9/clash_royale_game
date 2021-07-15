package Controller;

import javafx.stage.Stage;

public interface Controller {
    String PATH = Controller.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("target/classes/", "src/main/java/View/").substring(1);
    Stage STAGE = new Stage();
    SceneController SCENE_CONTROLLER = new SceneController();
}
