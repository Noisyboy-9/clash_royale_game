package authentication;

import authentication.runnables.AuthenticationRunnable;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * The type Authentication handler.
 */
public class AuthenticationHandler {
    /**
     * Instantiates a new Authentication handler.
     *
     * @param port the port
     */
    public AuthenticationHandler(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                new Thread(new AuthenticationRunnable(serverSocket.accept())).start();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
