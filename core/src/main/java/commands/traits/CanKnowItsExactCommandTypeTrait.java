package commands.traits;

/**
 * The interface Can know its exact command type trait.
 */
public interface CanKnowItsExactCommandTypeTrait extends CanKnowItsExactAuthenticationCommandTypeTrait,
        CanKnowItsExactGameStateCommandTrait,
        CanKnowItsExactMatchRequestCommandTrait,
        CanKnowItsExactPlayerStateCommandTrait {
}
