package controllers.modes.runnables;

import cards.Card;
import cards.CardStatusEnum;
import cards.troops.Troop;
import cards.utils.AttackAble;
import cards.utils.TypeEnum;
import controllers.modes.BaseController;
import exceptions.InvalidAttackTargetException;
import globals.GlobalData;
import javafx.geometry.Point2D;
import models.BotModeModel;
import models.GameModel;
import models.OnlineModeModel;
import towers.KingTower;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Handle troops runnable.
 */
public record HandleTroopsRunnable(GameModel model, BaseController controller) implements Runnable {
    @Override
    public void run() {
        this.handleDeadTroops();
        this.handleEachTroopTargetSelection();
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
            if (!Objects.isNull(troop.getTarget()) && this.isTimeForAttack(troop)) {

                troop.setStatus(CardStatusEnum.FIGHT);
                troop.attack();

                if (troop.getTarget().isDead()) {
                    troop.setStatus(CardStatusEnum.WALK);
                    troop.clearTarget();
                }
            }
        }
    }

    private boolean isTimeForAttack(Troop troop) {
        return controller.getFrameRemainingCount() % (troop.getHitSpeed() * controller.getFRAME_PER_SECOND()) == 0;
    }

    private void handleTroopsMove() {
        this.moveTroops(this.model.getPlayerInMapTroops());

        if (this.model instanceof BotModeModel) {
            this.moveTroops(((BotModeModel) this.model).getBotInMapTroops());
        }

        if (this.model instanceof OnlineModeModel) {
            this.moveTroops(((OnlineModeModel) this.model).getOpponentInMapTroops());
        }
    }

    private void moveTroops(ArrayList<Troop> enemyTroops) {
        for (Troop troop : enemyTroops) {
            if (!Objects.isNull(troop.getTarget())) continue;

            if (this.isTimeForMove(troop)) {
                if (this.isOnMainPaths(troop.getPosition())) {
                    if (troop.getPosition().getY() == 6 && troop.getPosition().getX() >= 6 && troop.getPosition().getX() <= 11) {
                        this.moveTo(troop, (int) troop.getPosition().getX() + 1, (int) troop.getPosition().getY());
                        return;
                    }

                    if (troop.getPosition().getY() == 6 && troop.getPosition().getX() >= 12 && troop.getPosition().getX() <= 17) {
                        this.moveTo(troop, (int) troop.getPosition().getX() - 1, (int) troop.getPosition().getY());
                        return;
                    }

                    this.moveTo(troop, (int) troop.getPosition().getX(), (int) troop.getPosition().getY() - 1);
                } else {
                    double leftPathDistance = this.getDistanceFromVerticalLine(troop.getPosition(), 6);
                    double rightPathDistance = this.getDistanceFromVerticalLine(troop.getPosition(), 17);

                    if (leftPathDistance < rightPathDistance) {
                        this.moveTo(troop,
                                (int) troop.getPosition().getX() - 1,
                                (int) troop.getPosition().getY());
                    } else {
                        this.moveTo(troop,
                                (int) troop.getPosition().getX() + 1,
                                (int) troop.getPosition().getY());
                    }
                }
            }
        }
    }

    private void moveTo(Troop troop, int x, int y) {
        troop.setStatus(CardStatusEnum.WALK);
        troop.setPosition(new Point2D(x, y));
    }

    private double getDistanceFromVerticalLine(Point2D position, int lineX) {
        return position.distance(lineX, position.getY());
    }

    private boolean isOnMainPaths(Point2D troopPosition) {
        return troopPosition.getX() == 6 ||
                troopPosition.getX() == 17 ||
                troopPosition.getY() == 6;
    }

    private boolean isTimeForMove(Troop troop) {
        return switch (troop.getMovementSpeed()) {
            case FAST -> this.controller.getFrameRemainingCount() % 10 == 0;
            case SLOW -> this.controller.getFrameRemainingCount() % 15 == 0;
            case MEDIUM -> this.controller.getFrameRemainingCount() % 20 == 0;
        };
    }

    private void handleEachTroopTargetSelection() {
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
            if (Objects.isNull(troop.getTarget()) || troop.getTarget().isDead()) {
                AttackAble nearestTarget = this.findNearestTarget(troop.getPosition(), possibleTargets);

                if (troop.getPosition().getY() == 6 && troop.getPosition().getX() == 9) {
                    if (this.isFriendly(troop)) {
                        KingTower tower = ((BotModeModel) this.model).getBotKingTower();
                        try {
                            troop.setTarget(tower);
                        } catch (InvalidAttackTargetException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }

                    KingTower tower = this.model.getPlayerKingTower();
                    try {
                        troop.setTarget(tower);
                    } catch (InvalidAttackTargetException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                if (this.isInRange(troop, nearestTarget) && this.haveCompatibleTypes(troop, nearestTarget)) {
                    try {
                        troop.setTarget(nearestTarget);
                    } catch (InvalidAttackTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean isFriendly(Troop troop) {
        return troop.getOwner().equals(GlobalData.user);
    }

    private boolean haveCompatibleTypes(Troop troop, AttackAble nearestTarget) {
        return switch (troop.getAttackType()) {
            case AIR -> nearestTarget.getSelfType().equals(TypeEnum.AIR);
            case GROUND -> nearestTarget.getSelfType().equals(TypeEnum.GROUND);
            case AIR_GROUND -> true;
        };
    }

    private boolean isInRange(Troop troop, AttackAble nearestTarget) {
        return troop.getPosition().distance(controller.transferPosition(nearestTarget.getPosition())) <= troop.getRange();
    }

    private void handleDeadTroops() {
        for (AttackAble attackAble : this.model.getPlayerInMapAttackAblesCards()) {
            if (attackAble.isDead()) {
                this.model.getPlayerInMapCards().remove((Card) attackAble);
            }
        }

        ArrayList<AttackAble> opponentAttackAbleCards = new ArrayList<>();
        ArrayList<Card> opponentInMapCards = new ArrayList<>();

        if (this.model instanceof BotModeModel) {
            opponentAttackAbleCards = ((BotModeModel) this.model).getBotInMapAttackAblesCards();
            opponentInMapCards = ((BotModeModel) this.model).getBotInMapCards();
            ((BotModeModel) this.model).getBotInMapAttackAblesCards().removeIf(AttackAble::isDead);
        }

        if (this.model instanceof OnlineModeModel) {
            opponentAttackAbleCards = ((OnlineModeModel) this.model).getOpponentInMapAttackAblesCards();
            opponentInMapCards = ((OnlineModeModel) this.model).getOpponentInMapCards();
            ((OnlineModeModel) this.model).getOpponentInMapAttackAblesCards().removeIf(AttackAble::isDead);
        }

        for (AttackAble attackAble : opponentAttackAbleCards) {
            if (attackAble.isDead()) {
                opponentInMapCards.remove((Card) attackAble);
            }
        }


    }

    private AttackAble findNearestTarget(Point2D troopPosition, ArrayList<AttackAble> targets) {
        AttackAble nearestTarget = targets.get(0);

        for (AttackAble target : targets) {
            if (troopPosition.distance(controller.transferPosition(target.getPosition())) <
                    troopPosition.distance(controller.transferPosition(nearestTarget.getPosition()))
            ) {
                nearestTarget = target;
            }
        }

        return nearestTarget;
    }
}
