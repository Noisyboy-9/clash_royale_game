package controllers.modes.runnables;

import controllers.modes.BaseController;
import models.BotModeModel;
import models.GameModel;

public class HandleElixirsCountRunnable implements Runnable {
    private final GameModel model;
    private final BaseController controller;

    public HandleElixirsCountRunnable(GameModel model, BaseController controller) {
        this.model = model;
        this.controller = controller;
    }

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
