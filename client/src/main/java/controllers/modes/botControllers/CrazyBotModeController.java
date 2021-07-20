package controllers.modes.botControllers;

import models.BotModeModel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Crazy bot mode controller.
 */
public class CrazyBotModeController extends BotController  {

    /**
     * Instantiates a new Crazy bot mode controller.
     *
     * @param model the model
     */
    public CrazyBotModeController(BotModeModel model) {
        super(model);
        this.setTimer();
    }

    private void setTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
//                handle towers thread
//                handle troops thread.
//                handle spells thread.
//                handle buildings thread.
//                handle exir count
//                handle bot move.
//                handle time thread.
//                after all these threads are finished: render screen again.
            }
        };

        timer.schedule(timerTask, 0, this.eachFrameDuration);
    }
}
