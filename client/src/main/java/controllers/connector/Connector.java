package controllers.connector;

import errors.AlreadyConnectedToServerException;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

/**
 * The type controllers.connector, who handles the connection between server
 * and client.
 * it will give the client access to the server through it's socket, and
 * response , request streams.
 */
public class Connector {
    private static Connector instance = null;
    private final Socket socket;
    private final ObjectInputStream response;
    private final ObjectOutputStream request;

    private Connector(Socket socket, ObjectInputStream response, ObjectOutputStream request) {
        this.socket = socket;
        this.response = response;
        this.request = request;
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
     * Gets response.
     *
     * @return the response
     */
    public ObjectInputStream getResponse() {
        return response;
    }

    /**
     * Gets request.
     *
     * @return the request
     */
    public ObjectOutputStream getRequest() {
        return request;
    }

    /**
     * Connect controllers.connector.
     *
     * @param socket   the socket
     * @param response the response
     * @param request  the request
     * @return the controllers.connector
     * @throws AlreadyConnectedToServerException the already connected to server exception
     * @throws IOException                       the io exception
     */
    public static Connector connect(Socket socket, InputStream response, OutputStream request) throws AlreadyConnectedToServerException, IOException {
//        check if have already connected to server
        if (!Objects.isNull(instance)) {
            throw new AlreadyConnectedToServerException("A connection to server has already been established, can't connect again");
        }

//        this is the first time connecting to server so create a new connection
        instance = new Connector(
                socket,
                new ObjectInputStream(response),
                new ObjectOutputStream(request)
        );

//        return the connection
        return instance;
    }

    /**
     * Gets instance of the server connection.
     *
     * @return the instance
     */
    public static Connector getInstance() {
        return instance;
    }
}
