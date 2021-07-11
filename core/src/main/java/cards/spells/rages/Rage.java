package cards.spells.rages;

import cards.spells.Spell;
import cards.utils.Position;
import user.User;

import java.util.UUID;

public class Rage extends Spell {
    public Rage(UUID id, User owner, Position position) {
        super(id, 3, owner, position, 5);
    }

    @Override
    public void chant() {
        
    }
}
