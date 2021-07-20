package controllers.modes.runnables;

import cards.spells.Spell;
import cards.spells.rages.Rage;
import cards.troops.Troop;
import cards.utils.AttackAble;
import models.BotModeModel;
import models.GameModel;
import models.OnlineModeModel;
import towers.Tower;

import java.util.ArrayList;

/**
 * The type Handle spells runnable.
 */
public record HandleSpellsRunnable(GameModel model) implements Runnable {
    @Override
    public void run() {
        if (this.model instanceof BotModeModel) {
            this.handleSpells(this.model.getPlayerInMapSpells(), ((BotModeModel) model).getBotInMapAttackAbles());
            this.handleSpells(((BotModeModel) this.model).getBotInMapSpells(), this.model.getPlayerInMapAttackAbles());
        }

        if (this.model instanceof OnlineModeModel) {
            this.handleSpells(this.model.getPlayerInMapSpells(), ((OnlineModeModel) model).getOpponentInMapAttackAbles());
            this.handleSpells(((OnlineModeModel) this.model).getOpponentInMapSpells(), this.model.getPlayerInMapAttackAbles());
        }
    }

    private void handleSpells(ArrayList<Spell> spells, ArrayList<AttackAble> possibleTargets) {
        for (Spell spell : spells) {
            this.setSpellInRangeTowerTargets(spell, possibleTargets);
            this.setSpellInRangeTroopTargets(spell, possibleTargets);

            spell.chant();

            if (spell.isRage()) {
                ((Rage) spell).decreaseRemainingFrameCountBy(1);

//                if the spell is rage and it's remaining frame count is zero it must be deleted
                if (((Rage) spell).getRemainingFrameCount() == 0) {
                    spells.remove(spell);
                }
                continue;
            }

//            after a fireball or arrows have done it's work it must be removed
            spells.remove(spell);
        }
    }

    private void setSpellInRangeTroopTargets(Spell spell, ArrayList<AttackAble> possibleTargets) {
        for (AttackAble target : possibleTargets) {
            if (target instanceof Troop && this.isInRange(spell, target)) {
                spell.addTroopTarget((Troop) target);
            }
        }
    }

    private boolean isInRange(Spell spell, AttackAble target) {
        return target.getPosition().distance(spell.getPosition()) <= spell.getRadius();
    }

    private void setSpellInRangeTowerTargets(Spell spell, ArrayList<AttackAble> possibleTargets) {
        for (AttackAble target : possibleTargets) {
            if (target instanceof Tower && this.isInRange(spell, target)) {
                spell.addTowerTarget((Tower) target);
            }
        }
    }
}
