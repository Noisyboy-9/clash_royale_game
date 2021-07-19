package commands;

import commands.traits.CanKnowItsExactCommandTypeTrait;

import java.io.Serializable;

public abstract class Command implements Serializable, CanKnowItsExactCommandTypeTrait {

}
