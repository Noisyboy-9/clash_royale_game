package controllers.modes.runnables;

import cards.utils.AttackAble;
import controllers.modes.BaseController;
import globals.GlobalData;
import javafx.geometry.Point2D;
import models.BotModeModel;
import models.GameModel;
import models.OnlineModeModel;
import towers.Tower;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;

/**
 * The type Handle towers runnable.
 */
public record HandleTowersRunnable(GameModel model, BaseController controller, Timer timer) implements Runnable {
    @Override
    public void run() {
        this.handleDeadTowers();
        this.calculateEachTowerTarget();
        if (controller.getFrameRemainingCount() % controller.getFRAME_PER_SECOND() == 0) {
            this.handleTowerAttacks();
        }
    }

    private void handleDeadTowers() {
        this.removeDeadTowers(this.model.getPlayerTowers());

        if (this.model instanceof BotModeModel) {
            this.removeDeadTowers(((BotModeModel) this.model).getBotTowers());
        }

        if (this.model instanceof OnlineModeModel) {
            this.removeDeadTowers(((OnlineModeModel) this.model).getOpponentTowers());
        }
    }

    private void removeDeadTowers(ArrayList<Tower> towers) {
        for (Tower tower : towers) {
            if (tower.isDead() && tower.isKingTower()) {
                tower.deActive();
                this.updateCrownCount(tower, 3);
                this.doFinishGameSteps();
            }

            if (tower.isDead() && tower.isQueenTower()) {
                tower.deActive();
                towers.remove(tower);
                this.updateCrownCount(tower, tower.getDemolitionBonusCount());
            }

        }
    }

    private void updateCrownCount(Tower destroyedTower, int demolitionBonusCount) {
        if (GlobalData.playerTeam.contains(destroyedTower.getOwner())) {
            if (this.model instanceof BotModeModel) {
                if (demolitionBonusCount != 3) {
                    ((BotModeModel) this.model).setBotCrownCount(
                            ((BotModeModel) this.model).getBotCrownCount() + demolitionBonusCount
                    );
                } else {
                    ((BotModeModel) this.model).setBotCrownCount(3);
                }
            }

            if (this.model instanceof OnlineModeModel) {
                if (demolitionBonusCount != 3) {
                    ((OnlineModeModel) this.model).setOpponentCrownCount(
                            ((OnlineModeModel) this.model).getOpponentCrownCount() + demolitionBonusCount
                    );
                } else {
                    ((OnlineModeModel) this.model).setOpponentCrownCount(3);
                }
            }
        }


        if (GlobalData.opponentTeam.contains(destroyedTower.getOwner())) {
            if (demolitionBonusCount != 3) {
                this.model.setPlayerCrownCount(
                        this.model.getPlayerCrownCount() + demolitionBonusCount
                );
            } else {
                this.model.setPlayerCrownCount(3);
            }
        }
    }

    private void doFinishGameSteps() {
        timer.cancel();
        controller.finishGame();
        this.model.getPlayerTowers().clear();

        if (this.model instanceof BotModeModel) {
            ((BotModeModel) this.model).getBotTowers().clear();
        }

        if (this.model instanceof OnlineModeModel) {
            ((OnlineModeModel) this.model).getOpponentTowers().clear();
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

        for (Tower tower : towers) {
            if (tower.getTarget() != null) {
                tower.shoot();

                if (tower.getTarget().isDead()) {
                    tower.setTarget(null);
                }
            }

        }
    }

    private void calculateEachTowerTarget() {
        if (model instanceof BotModeModel) {
            this.handleTowerTargetCalculation(this.model.getPlayerTowers(), ((BotModeModel) model).getBotInMapAttackAblesCards());
            this.handleTowerTargetCalculation(((BotModeModel) model).getBotTowers(), this.model.getPlayerInMapAttackAblesCards());
        }

        if (model instanceof OnlineModeModel) {
            this.handleTowerTargetCalculation(this.model.getPlayerTowers(), ((OnlineModeModel) model).getOpponentInMapAttackAblesCards());
            this.handleTowerTargetCalculation(((OnlineModeModel) model).getOpponentTowers(), this.model.getPlayerInMapAttackAblesCards());
        }
    }

    private void handleTowerTargetCalculation(ArrayList<Tower> towers, ArrayList<AttackAble> targets) {
        for (Tower tower : towers) {
//            if the tower already have an target don't change it's target.
            if (!Objects.isNull(tower.getTarget())) {
                Point2D towerPosition = tower.getPosition();

                AttackAble nearestTarget = this.findNearestTarget(towerPosition, targets);
                if (this.isInRage(nearestTarget, tower)) {
                    tower.activate();
                    tower.setTarget(nearestTarget);
                }
            }
        }
    }

    private boolean isInRage(AttackAble nearestTarget, Tower tower) {
        return nearestTarget.getPosition().distance(tower.getPosition()) < tower.getRange();
    }

    private AttackAble findNearestTarget(Point2D towerPosition, ArrayList<AttackAble> targets) {
        AttackAble nearestTarget = targets.get(0);

        for (AttackAble target : targets) {
            if (controller.transferPosition(target.getPosition()).distance(towerPosition) < controller.transferPosition(nearestTarget.getPosition()).distance(towerPosition)) {
                nearestTarget = target;
            }
        }

        return nearestTarget;
    }
}
