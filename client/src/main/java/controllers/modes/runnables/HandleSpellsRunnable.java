package controllers.modes.runnables;

import cards.Card;
import cards.spells.Spell;
import cards.spells.rages.Rage;
import cards.troops.Troop;
import cards.utils.AttackAble;
import controllers.modes.BaseController;
import globals.GlobalData;
import models.BotModeModel;
import models.GameModel;
import models.OnlineModeModel;
import towers.Tower;

import java.util.ArrayList;

/**
 * The type Handle spells runnable.
 */
public record HandleSpellsRunnable(GameModel model, BaseController controller) implements Runnable {
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
                if (((Rage) spell).getRemainingFrameCount() <= 0) {
                    ((Rage) spell).unChant();


                    if (spell.getOwner().equals(GlobalData.user)) {
                        this.model.getPlayerInMapCards().remove(spell);
                    } else {
                        if (this.model instanceof BotModeModel) {
                            ((BotModeModel) this.model).getBotInMapCards().remove(spell);
                        }

                        if (this.model instanceof OnlineModeModel) {
                            ((OnlineModeModel) this.model).getOpponentInMapCards().remove(spell);
                        }
                    }
                }

                continue;
            }

            this.model.getPlayerInMapCards().removeIf(card -> card.isSpell() && !card.isRage());

            ArrayList<AttackAble> opponentAttackAbleCards = new ArrayList<>();
            ArrayList<Card> opponentInMapCards = new ArrayList<>();

            if (this.model instanceof BotModeModel) {
                ((BotModeModel) this.model).getBotInMapCards().removeIf(card -> card.isSpell() && !card.isRage());
            }

            if (this.model instanceof OnlineModeModel) {
                ((OnlineModeModel) this.model).getOpponentInMapCards().removeIf(card -> card.isSpell() && !card.isRage());
            }
        }
    }

    private void setSpellInRangeTroopTargets(Spell spell, ArrayList<AttackAble> possibleTargets) {
        for (AttackAble target : possibleTargets) {
            if (target instanceof Troop && this.isInRange(spell, target)) {
                spell.addTroopTarget((Troop) target);
            }
        }
    }

    private boolean isInRange(Spell spell, AttackAble nearestTarget) {
        return spell.getPosition().distance(controller.transferPosition(nearestTarget.getPosition())) <= spell.getRadius();
    }


    private void setSpellInRangeTowerTargets(Spell spell, ArrayList<AttackAble> possibleTargets) {
        for (AttackAble target : possibleTargets) {
            if (target instanceof Tower && this.isInRange(spell, target)) {
                spell.addTowerTarget((Tower) target);
            }
        }
    }
}
