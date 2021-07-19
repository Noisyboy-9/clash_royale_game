package controllers.modes.botControllers;

import controllers.modes.BaseController;
import models.BotModeModel;

public abstract class BotController extends BaseController {
    protected final BotModeModel model;

    public BotController(BotModeModel model) {
        super(model);
        this.model = model;
    }
}
