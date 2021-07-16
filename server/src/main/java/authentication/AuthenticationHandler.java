package authentication;

import authentication.runnables.AuthenticationRunnable;

import java.io.IOException;
import java.net.ServerSocket;

public class AuthenticationHandler {
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
