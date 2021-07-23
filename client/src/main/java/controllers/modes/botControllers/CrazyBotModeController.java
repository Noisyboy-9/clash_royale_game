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
 * The type Crazy bot mode controller.
 */
public class CrazyBotModeController extends BotController  {

    /**
     * Instantiates a new Crazy bot mode controller.
     */
    public CrazyBotModeController() {
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
                                    new HandleTowersRunnable(CrazyBotModeController.super.model, CrazyBotModeController.this, timer)
                            );

                            service.execute(
                                    new HandleTroopsRunnable(CrazyBotModeController.super.model, CrazyBotModeController.this)
                            );

                            service.execute(
                                    new HandleSpellsRunnable(CrazyBotModeController.super.model, CrazyBotModeController.this)
                            );

                            service.execute(
                                    new HandleBuildingRunnable(CrazyBotModeController.super.model, CrazyBotModeController.this)
                            );

                            service.execute(
                                    new HandleElixirsCountRunnable(CrazyBotModeController.super.model, CrazyBotModeController.this)
                            );

                            service.execute(
                                    new HandleCrazyBotMoveRunnable(CrazyBotModeController.super.model, CrazyBotModeController.this)
                            );

//                after all these threads are finished: render screen again.
                            service.shutdown();

                            try {
                                service.awaitTermination(CrazyBotModeController.super.eachFrameDuration, TimeUnit.MILLISECONDS);
                                CrazyBotModeController.super.render();
                                CrazyBotModeController.this.reduceFrameRemainingCount();
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
