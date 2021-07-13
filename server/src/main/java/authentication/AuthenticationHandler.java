package authentication;

import authentication.runnables.AuthenticationRunnable;

import java.io.IOException;
import java.net.ServerSocket;

public class AuthenticationHandler {
    public AuthenticationHandler(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            new Thread(new AuthenticationRunnable(serverSocket.accept(), this)).start();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
