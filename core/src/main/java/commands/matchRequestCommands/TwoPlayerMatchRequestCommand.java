package commands.matchRequestCommands;

import user.User;

/**
 * The type Two player match request command.
 */
public class TwoPlayerMatchRequestCommand extends MatchRequestCommand {
    private final User requester;

    /**
     * Instantiates a new Two player match request command.
     *
     * @param requester the requester
     */
    public TwoPlayerMatchRequestCommand(User requester) {
        this.requester = requester;
    }

    /**
     * Gets requester.
     *
     * @return the requester
     */
    public User getRequester() {
        return requester;
    }
}
