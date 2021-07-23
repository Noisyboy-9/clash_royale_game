package commands.matchRequestCommands;

import user.User;

/**
 * The type Four player match requester command.
 */
public class FourPlayerMatchRequesterCommand extends MatchRequestCommand {
    private final User requester;

    /**
     * Instantiates a new Two player match request command.
     *
     * @param requester the requester
     */
    public FourPlayerMatchRequesterCommand(User requester) {
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
