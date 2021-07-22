package controllers.modes.runnables;

import cards.troops.Troop;
import cards.utils.AttackAble;
import controllers.modes.BaseController;
import exceptions.InvalidAttackTargetException;
import javafx.geometry.Point2D;
import models.BotModeModel;
import models.GameModel;
import models.OnlineModeModel;

import java.util.ArrayList;
import java.util.Objects;

public record HandleTroopsRunnable(GameModel model, BaseController controller) implements Runnable {
    @Override
    public void run() {
        this.handleDeadTroops();
        this.handleEachTroopTargets();
        this.handleTroopAttacks();
        this.handleTroopsMove();
    }

    private void handleTroopAttacks() {
        if (this.model instanceof BotModeModel) {
            this.doTroopsAttack(this.model.getPlayerInMapTroops());
            this.doTroopsAttack(((BotModeModel) this.model).getBotInMapTroops());
        }

        if (this.model instanceof OnlineModeModel) {
            this.doTroopsAttack(this.model.getPlayerInMapTroops());
            this.doTroopsAttack(((OnlineModeModel) this.model).getOpponentInMapTroops());
        }
    }

    private void doTroopsAttack(ArrayList<Troop> troops) {
        for (Troop troop : troops) {
            if (!Objects.isNull(troop.getTarget())) {
                troop.attack();
            }
        }
    }

    private void handleTroopsMove() {
        this.moveFriendlyTroops(this.model.getPlayerInMapTroops());

        if (this.model instanceof BotModeModel) {
            this.moveEnemyTroops(((BotModeModel) this.model).getBotInMapTroops());
        }

        if (this.model instanceof OnlineModeModel) {
            this.moveEnemyTroops(((OnlineModeModel) this.model).getOpponentInMapTroops());
        }
    }

    private void moveEnemyTroops(ArrayList<Troop> enemyTroops) {
        for (Troop troop : enemyTroops) {
            if (!Objects.isNull(troop.getTarget())) continue;

            if (this.isTimeForMove(troop) && this.isOnMainPaths(controller.transferPosition(troop.getPosition()))) {
                this.moveTo(troop, (int) troop.getPosition().getX(), (int) troop.getPosition().getY() - 1);
            }

            if (this.isTimeForMove(troop) && !this.isOnMainPaths(troop.getPosition())) {
                double leftPathDistance = this.getDistanceFromVerticalLine(controller.transferPosition(troop.getPosition()), 6);
                double rightPathDistance = this.getDistanceFromVerticalLine(controller.transferPosition(troop.getPosition()), 17);

                if (leftPathDistance < rightPathDistance) {
                    this.moveTo(troop,
                            (int) controller.transferPosition(troop.getPosition()).getX() + 1,
                            (int) controller.transferPosition(troop.getPosition()).getY());
                }

                if (leftPathDistance < rightPathDistance) {
                    this.moveTo(troop,
                            (int) controller.transferPosition(troop.getPosition()).getX() - 1,
                            (int) controller.transferPosition(troop.getPosition()).getY());
                }
            }
        }
    }

    private void moveFriendlyTroops(ArrayList<Troop> friendlyTroops) {
        for (Troop troop : friendlyTroops) {
            if (!Objects.isNull(troop.getTarget())) continue;

            if (this.isTimeForMove(troop) && this.isOnMainPaths(troop.getPosition())) {
                this.moveTo(troop, (int) troop.getPosition().getX(), (int) troop.getPosition().getY() + 1);

            }

            if (this.isTimeForMove(troop) && !this.isOnMainPaths(troop.getPosition())) {
                double leftPathDistance = this.getDistanceFromVerticalLine(troop.getPosition(), 6);
                double rightPathDistance = this.getDistanceFromVerticalLine(troop.getPosition(), 17);

                if (leftPathDistance < rightPathDistance) {
                    this.moveTo(troop, (int) troop.getPosition().getX() + 1, (int) troop.getPosition().getY());
                } else {
                    this.moveTo(troop, (int) troop.getPosition().getX() - 1, (int) troop.getPosition().getY());
                }
            }
        }
    }

    private void moveTo(Troop troop, int x, int y) {
        troop.setPosition(new Point2D(x, y));
    }

    private double getDistanceFromVerticalLine(Point2D position, int lineX) {
        return position.distance(lineX, position.getY());
    }

    private boolean isOnMainPaths(Point2D troopPosition) {
        return troopPosition.getX() == 6 ||
                troopPosition.getX() == 17;
    }

    private boolean isTimeForMove(Troop troop) {
        return switch (troop.getMovementSpeed()) {
            case FAST -> this.controller.getFrameRemainingCount() % 10 == 0;
            case SLOW -> this.controller.getFrameRemainingCount() % 15 == 0;
            case MEDIUM -> this.controller.getFrameRemainingCount() % 20 == 0;
        };
    }

    private void handleEachTroopTargets() {
        if (this.model instanceof BotModeModel) {
            this.handleTargetSelection(this.model.getPlayerInMapTroops(), ((BotModeModel) this.model).getBotInMapAttackAbles());
            this.handleTargetSelection(((BotModeModel) this.model).getBotInMapTroops(), this.model.getPlayerInMapAttackAbles());
        }

        if (this.model instanceof OnlineModeModel) {
            this.handleTargetSelection(this.model.getPlayerInMapTroops(), ((OnlineModeModel) this.model).getOpponentInMapAttackAbles());
            this.handleTargetSelection(((OnlineModeModel) this.model).getOpponentInMapTroops(), this.model.getPlayerInMapAttackAbles());
        }
    }

    private void handleTargetSelection(ArrayList<Troop> troops, ArrayList<AttackAble> possibleTargets) {
        for (Troop troop : troops) {
            if (Objects.isNull(troop.getTarget())) {
                AttackAble nearestTarget = this.findNearestTarget(troop.getPosition(), possibleTargets);

                if (this.isInRange(troop, nearestTarget) && this.haveSameTypes(troop, nearestTarget)) {
                    try {
                        troop.setTarget(nearestTarget);
                    } catch (InvalidAttackTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean haveSameTypes(Troop troop, AttackAble nearestTarget) {
        return troop.getAttackType().equals(nearestTarget.getSelfType());
    }

    private boolean isInRange(Troop troop, AttackAble nearestTarget) {
        return controller.transferPosition(nearestTarget.getPosition()).distance(troop.getPosition()) < troop.getRange();
    }

    private void handleDeadTroops() {
        this.model.getPlayerInMapTroops().removeIf(Troop::isDead);

        if (this.model instanceof BotModeModel) {
            ((BotModeModel) this.model).getBotInMapTroops().removeIf(Troop::isDead);
        }

        if (this.model instanceof OnlineModeModel) {
            this.model.getPlayerInMapTroops().removeIf(Troop::isDead);
        }
    }

    private AttackAble findNearestTarget(Point2D troopPosition, ArrayList<AttackAble> targets) {
        AttackAble nearestTarget = targets.get(0);

        for (AttackAble target : targets) {
            if (controller.transferPosition(target.getPosition()).distance(troopPosition) < controller.transferPosition(nearestTarget.getPosition()).distance(troopPosition)) {
                nearestTarget = target;
            }
        }

        return nearestTarget;
    }
}
