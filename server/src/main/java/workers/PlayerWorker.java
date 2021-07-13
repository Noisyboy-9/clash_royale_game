package workers;

import user.User;

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
    private final User userData;

    /**
     * Instantiates a new Player worker.
     *
     * @param response the response
     * @param request  the request
     * @param socket   the socket
     * @param user     the user
     */
    public PlayerWorker(ObjectOutputStream response, ObjectInputStream request, Socket socket, User user) {
        this.response = response;
        this.request = request;
        this.socket = socket;
        this.userData = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerWorker that = (PlayerWorker) o;
        return userData.equals(that.userData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userData);
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
    public User getUserData() {
        return userData;
    }
}
