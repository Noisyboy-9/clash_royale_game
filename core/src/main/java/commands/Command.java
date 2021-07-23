package commands;

import commands.traits.CanKnowItsExactCommandTypeTrait;

import java.io.Serializable;

/**
 * The type Command.
 */
public abstract class Command implements Serializable, CanKnowItsExactCommandTypeTrait {
}
