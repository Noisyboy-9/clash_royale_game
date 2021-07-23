package controllers.modes.botControllers;

import controllers.modes.runnables.*;
import globals.GlobalData;
import javafx.application.Platform;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The type Normal bot mode controller.
 */
public class NormalBotModeController extends BotController {
    /**
     * Instantiates a new Bot controller.
     */
    public NormalBotModeController() {
        super();
        GlobalData.gameController = this;
        this.setTimer();
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
                                    new HandleTowersRunnable(NormalBotModeController.super.model, NormalBotModeController.this, timer)
                            );

                            service.execute(
                                    new HandleTroopsRunnable(NormalBotModeController.super.model, NormalBotModeController.this)
                            );

                            service.execute(
                                    new HandleSpellsRunnable(NormalBotModeController.super.model, NormalBotModeController.this)
                            );

                            service.execute(
                                    new HandleBuildingRunnable(NormalBotModeController.super.model, NormalBotModeController.this)
                            );

                            service.execute(
                                    new HandleElixirsCountRunnable(NormalBotModeController.super.model, NormalBotModeController.this)
                            );

                            service.execute(
                                    new HandleNormalBotMoveRunnable(NormalBotModeController.super.model, NormalBotModeController.this)
                            );

//                after all these threads are finished: render screen again.
                            service.shutdown();

                            try {
                                service.awaitTermination(NormalBotModeController.super.eachFrameDuration, TimeUnit.MILLISECONDS);
                                NormalBotModeController.super.render();
                                NormalBotModeController.this.reduceFrameRemainingCount();
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
