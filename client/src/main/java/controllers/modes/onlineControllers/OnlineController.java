package controllers.modes.onlineControllers;

import controllers.modes.BaseController;
import models.BotModeModel;

public abstract class OnlineController extends BaseController {
    public OnlineController(BotModeModel model) {
        super(model);
    }
}
