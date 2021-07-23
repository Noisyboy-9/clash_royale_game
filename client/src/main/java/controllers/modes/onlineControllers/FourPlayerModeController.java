package controllers.modes.onlineControllers;

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
 * The type Four player mode controller.
 */
public class FourPlayerModeController extends OnlineController {
    /**
     * Instantiates a new Bot controller.
     */
    public FourPlayerModeController() {
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
                                    new HandleTowersRunnable(FourPlayerModeController.super.model, FourPlayerModeController.this, timer)
                            );

                            service.execute(
                                    new HandleTroopsRunnable(FourPlayerModeController.super.model, FourPlayerModeController.this)
                            );

                            service.execute(
                                    new HandleSpellsRunnable(FourPlayerModeController.super.model, FourPlayerModeController.this)
                            );

                            service.execute(
                                    new HandleBuildingRunnable(FourPlayerModeController.super.model, FourPlayerModeController.this)
                            );

                            service.execute(
                                    new HandleElixirsCountRunnable(FourPlayerModeController.super.model, FourPlayerModeController.this)
                            );

//                after all these threads are finished: render screen again.
                            service.shutdown();

                            try {
                                service.awaitTermination(FourPlayerModeController.super.eachFrameDuration, TimeUnit.MILLISECONDS);
                                FourPlayerModeController.super.render();
                                FourPlayerModeController.this.reduceFrameRemainingCount();
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
