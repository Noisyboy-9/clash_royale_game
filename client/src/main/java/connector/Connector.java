package connector;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connector {
    private final Socket socket;
    private final ObjectInputStream response;
    private final ObjectOutputStream request;

    public Connector(Socket socket, ObjectInputStream response, ObjectOutputStream request) {
        this.socket = socket;
        this.response = response;
        this.request = request;
    }

    public Socket getSocket() {
        return socket;
    }

    public ObjectInputStream getResponse() {
        return response;
    }

    public ObjectOutputStream getRequest() {
        return request;
    }
}
