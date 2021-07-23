package controllers.modes.runnables;

import controllers.modes.BaseController;
import models.BotModeModel;
import models.GameModel;

/**
 * The type Handle elixirs count runnable.
 */
public record HandleElixirsCountRunnable(GameModel model, BaseController controller) implements Runnable {
    @Override
    public void run() {
        if (controller.getFrameRemainingCount() % (2L * controller.getFRAME_PER_SECOND()) == 0) {
//            every two second add an elixir
            model.increasePlayerElixirsCountBy(1);

            if (model instanceof BotModeModel) {
                ((BotModeModel) model).increaseBotElixirCountBy(1);
            }
        }
    }
}
