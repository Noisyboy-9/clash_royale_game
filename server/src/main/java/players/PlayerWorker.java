package players;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;

/**
 * The type Player worker that gives client access to the server.
 * by this class the server can communicate data with client.
 */
public class PlayerWorker {
    private final ObjectOutputStream response;
    private final ObjectInputStream request;
    private final Socket socket;
    private final Player player;

    /**
     * Instantiates a new Player worker.
     *
     * @param response the response
     * @param request  the request
     * @param socket   the socket
     * @param player   the player
     */
    public PlayerWorker(ObjectOutputStream response, ObjectInputStream request, Socket socket, Player player) {
        this.response = response;
        this.request = request;
        this.socket = socket;
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerWorker that = (PlayerWorker) o;
        return player.equals(that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }

    /**
     * Gets response.
     *
     * @return the response
     */
    public ObjectOutputStream getResponse() {
        return response;
    }

    /**
     * Gets request.
     *
     * @return the request
     */
    public ObjectInputStream getRequest() {
        return request;
    }

    /**
     * Gets socket.
     *
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
}
