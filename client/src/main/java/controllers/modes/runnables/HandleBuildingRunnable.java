package controllers.modes.runnables;

import cards.buildings.Building;
import cards.utils.AttackAble;
import controllers.modes.BaseController;
import javafx.geometry.Point2D;
import models.BotModeModel;
import models.GameModel;
import models.OnlineModeModel;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Handle building runnable.
 */
public record HandleBuildingRunnable(GameModel model, BaseController controller) implements Runnable {
    @Override
    public void run() {
        this.handleDeadBuildings();
        this.handleEachBuildingTargetCalculation();
        this.handleAttacks();
        this.reduceEveryBuildingRemainingFrameCount();
    }

    private void reduceEveryBuildingRemainingFrameCount() {
        for (Building building : this.model.getPlayerInMapBuildings()) {
            building.reduceRemainingFramesBy(1);
        }

        if (this.model instanceof BotModeModel) {
            for (Building building : ((BotModeModel) this.model).getBotInMapBuildings()) {
                building.reduceRemainingFramesBy(1);
            }
        }

        if (this.model instanceof OnlineModeModel) {
            for (Building building : ((OnlineModeModel) this.model).getPlayerInMapBuildings()) {
                building.reduceRemainingFramesBy(1);
            }
        }
    }

    private void handleAttacks() {
        if (this.model instanceof BotModeModel) {
            this.doBuildingAttack(this.model.getPlayerInMapBuildings());
            this.doBuildingAttack(((BotModeModel) this.model).getBotInMapBuildings());
        }

        if (this.model instanceof OnlineModeModel) {
            this.doBuildingAttack(this.model.getPlayerInMapBuildings());
            this.doBuildingAttack(((OnlineModeModel) this.model).getOpponentInMapBuildings());
        }
    }

    private void doBuildingAttack(ArrayList<Building> buildings) {
        for (Building building : buildings) {
            if (Objects.isNull(building.getTarget()) && this.isTimeForAttack(building)) {
                building.attack();
            }
        }
    }

    private boolean isTimeForAttack(Building building) {
        return (controller.getFrameRemainingCount() % (building.getHitSpeed() * controller.getFRAME_PER_SECOND())) == 0;
    }

    private void handleEachBuildingTargetCalculation() {
        if (this.model instanceof BotModeModel) {
            this.handleBuildingsTargetCalculation(
                    this.model.getPlayerInMapBuildings(),
                    ((BotModeModel) this.model).getBotInMapAttackAbles()
            );

            this.handleBuildingsTargetCalculation(
                    ((BotModeModel) this.model).getBotInMapBuildings(),
                    this.model.getPlayerInMapAttackAbles()
            );
        }

        if (this.model instanceof OnlineModeModel) {
            this.handleBuildingsTargetCalculation(
                    this.model.getPlayerInMapBuildings(),
                    ((OnlineModeModel) this.model).getOpponentInMapAttackAbles()
            );
            this.handleBuildingsTargetCalculation(
                    ((OnlineModeModel) this.model).getOpponentInMapBuildings(),
                    this.model.getPlayerInMapAttackAbles()
            );
        }
    }

    private void handleBuildingsTargetCalculation(ArrayList<Building> buildings, ArrayList<AttackAble> targets) {
        for (Building building : buildings) {
            if (Objects.isNull(building.getTarget())) {
                AttackAble nearestTarget = this.findNearestTarget(building.getPosition(), targets);
                building.setTarget(nearestTarget);
            }
        }
    }

    private void handleDeadBuildings() {
        this.model.getPlayerInMapBuildings().removeIf(Building::isDead);

        if (this.model instanceof BotModeModel) {
            ((BotModeModel) this.model).getBotInMapBuildings().removeIf(Building::isDead);
        }

        if (this.model instanceof OnlineModeModel) {
            ((OnlineModeModel) this.model).getOpponentInMapBuildings().removeIf(Building::isDead);
        }
    }

    private AttackAble findNearestTarget(Point2D buildingPosition, ArrayList<AttackAble> targets) {
        AttackAble nearestTarget = targets.get(0);

        for (AttackAble target : targets) {
            if (controller.transferPosition(target.getPosition()).distance(buildingPosition) < controller.transferPosition(nearestTarget.getPosition()).distance(buildingPosition)) {
                nearestTarget = target;
            }
        }

        return nearestTarget;
    }
}
