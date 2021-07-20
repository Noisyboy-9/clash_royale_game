package controllers.modes.runnables;

import cards.utils.AttackAble;
import controllers.modes.BaseController;
import javafx.geometry.Point2D;
import models.BotModeModel;
import models.GameModel;
import models.OnlineModeModel;
import towers.Tower;

import java.util.ArrayList;
import java.util.Objects;

public class HandleTowersRunnable implements Runnable {
    private final GameModel model;
    private final BaseController controller;

    public HandleTowersRunnable(GameModel model, BaseController controller) {
        this.model = model;
        this.controller = controller;
    }

    @Override
    public void run() {
        this.calculateEachTowerTarget();
        if (controller.getFrameRemainingCount() % controller.getFRAME_PER_SECOND() == 0) {
            this.handleTowerAttacks();
        }
    }

    private void handleTowerAttacks() {
        ArrayList<Tower> towers = new ArrayList<>(model.getPlayerTowers());

        if (model instanceof OnlineModeModel) {
            towers.addAll(((OnlineModeModel) model).getOpponentTowers());
        }

        if (model instanceof BotModeModel) {
            towers.addAll(((BotModeModel) model).getBotTowers());
        }

        for (Tower tower : towers) tower.shoot();
    }

    private void calculateEachTowerTarget() {
        if (model instanceof BotModeModel) {
            this.handleTowerTargetCalculation(this.model.getPlayerTowers(), ((BotModeModel) model).getBotInMapAttackAblesCards());
            this.handleTowerTargetCalculation(((BotModeModel) model).getBotTowers(), this.model.getPlayerAttackAblesCards());
        }

        if (model instanceof OnlineModeModel) {
            this.handleTowerTargetCalculation(this.model.getPlayerTowers(), ((OnlineModeModel) model).getOpponentInMapAttackAblesCards());
            this.handleTowerTargetCalculation(((OnlineModeModel) model).getOpponentTowers(), this.model.getPlayerAttackAblesCards());
        }
    }

    private void handleTowerTargetCalculation(ArrayList<Tower> towers, ArrayList<AttackAble> targets) {
        for (Tower tower : towers) {
//            if the tower already have an target don't change it's target.
            if (!Objects.isNull(tower.getTarget())) {
                Point2D towerPosition = tower.getPosition();

                AttackAble nearestTarget = this.findNearestTarget(towerPosition, targets);
                tower.setTarget(nearestTarget);
            }
        }
    }

    private AttackAble findNearestTarget(Point2D towerPosition, ArrayList<AttackAble> targets) {
        AttackAble nearestTarget = targets.get(0);

        for (AttackAble target : targets) {
            if (target.getPosition().distance(towerPosition) < nearestTarget.getPosition().distance(towerPosition)) {
                nearestTarget = target;
            }
        }

        return nearestTarget;
    }
}
