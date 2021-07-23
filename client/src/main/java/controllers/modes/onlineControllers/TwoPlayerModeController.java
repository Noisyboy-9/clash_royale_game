package controllers.modes.onlineControllers;

import controllers.modes.botControllers.CrazyBotModeController;
import controllers.modes.runnables.*;
import globals.GlobalData;
import javafx.application.Platform;
import models.OnlineModeModel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The type Two player mode controller.
 */
public class TwoPlayerModeController extends OnlineController {
    /**
     * Instantiates a new Bot controller.
     */
    public TwoPlayerModeController() {
        super();
    }

    @Override
    protected void setTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        if (GlobalData.gameStarted) {
                            ExecutorService service = Executors.newCachedThreadPool();
                            service.execute(
                                    new HandleTowersRunnable(TwoPlayerModeController.super.model, TwoPlayerModeController.this, timer)
                            );

                            service.execute(
                                    new HandleTroopsRunnable(TwoPlayerModeController.super.model, TwoPlayerModeController.this)
                            );

                            service.execute(
                                    new HandleSpellsRunnable(TwoPlayerModeController.super.model, TwoPlayerModeController.this)
                            );

                            service.execute(
                                    new HandleBuildingRunnable(TwoPlayerModeController.super.model, TwoPlayerModeController.this)
                            );

                            service.execute(
                                    new HandleElixirsCountRunnable(TwoPlayerModeController.super.model, TwoPlayerModeController.this)
                            );

//                after all these threads are finished: render screen again.
                            service.shutdown();

                            try {
                                service.awaitTermination(TwoPlayerModeController.super.eachFrameDuration, TimeUnit.MILLISECONDS);
                                TwoPlayerModeController.super.render();
                                TwoPlayerModeController.this.reduceFrameRemainingCount();
                            } catch (InterruptedException e) {
                                System.out.println("The time of logical threads has exceeded the frame each frame duration");
                                System.out.println("maybe it is better to use a lower frame per second");
                            }

                        }
                    };
                });
            }
        };

        timer.schedule(timerTask, 0, this.eachFrameDuration);
    }
}
