package authentication.runnables;

import commands.authenicationCommands.AuthenticationCommand;
import commands.authenicationCommands.login.LoginCommand;
import commands.authenicationCommands.login.LoginResponseCommand;
import commands.authenicationCommands.register.RegisterCommand;
import commands.authenicationCommands.register.RegisterResponseCommand;
import database.QueryBuilder;
import exceptions.invalidPlayerArgumentException;
import newsCaster.NewsCaster;
import user.User;
import workers.PlayerWorker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class AuthenticationRunnable implements Runnable {
    private final Socket socket;
    private final ObjectOutputStream response;
    private final ObjectInputStream request;

    public AuthenticationRunnable(Socket socket) throws IOException {
        this.socket = socket;
        this.response = new ObjectOutputStream(this.socket.getOutputStream());
        this.request = new ObjectInputStream(this.socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            AuthenticationCommand command = (AuthenticationCommand) this.request.readObject();

            if (command instanceof RegisterCommand) {
                this.handleRegister((RegisterCommand) command);
            }

            if (command instanceof LoginCommand) {
                this.handleLogin((LoginCommand) command);
            }
        } catch (IOException | ClassNotFoundException | invalidPlayerArgumentException ioException) {
            ioException.printStackTrace();
        }
    }

    private void handleLogin(LoginCommand command) throws IOException, ClassNotFoundException, invalidPlayerArgumentException {
        String username = command.getUsername();
        String password = command.getPassword();

        if (QueryBuilder.getSingletonInstance().userExist(username, password)) {
            this.response.writeObject(new LoginResponseCommand(username, password, true, "Login Successful!"));

            PlayerWorker worker = new PlayerWorker(this.response,
                    this.request,
                    this.socket,
                    new User(username, password)
            );

            NewsCaster.getSingletonInstance().addOnlinePlayer(worker);
            NewsCaster.getSingletonInstance().makePlayerReady(worker);
        } else {
            this.response.writeObject(new LoginResponseCommand(username, password, false, "Invalid credentials."));
        }
    }

    private void handleRegister(RegisterCommand command) throws IOException, ClassNotFoundException {
        String username = command.getUsername();
        String password = command.getPassword();

        if (!QueryBuilder.getSingletonInstance().userExist(username, password)) {
            QueryBuilder.getSingletonInstance().insertUser(new User(username, password));
            this.response.writeObject(new RegisterResponseCommand(username, password, true, "Register successful"));
        } else {
            this.response.writeObject(new LoginResponseCommand(username, password, false, "User with email already exist!"));
        }
    }
}
