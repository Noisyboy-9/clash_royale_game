package authentication.runnables;

import commands.authenicationCommands.AuthenticationCommand;
import commands.authenicationCommands.login.LoginCommand;
import commands.authenicationCommands.login.LoginResponseCommand;
import commands.authenicationCommands.register.RegisterCommand;
import commands.authenicationCommands.register.RegisterResponseCommand;
import database.QueryBuilder;
import exceptions.DuplicateGameRequestException;
import exceptions.EmptyDatabaseException;
import exceptions.invalidPlayerArgumentException;
import newsCaster.NewsCaster;
import user.User;
import workers.PlayerWorker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * The type Authentication runnable.
 */
public class AuthenticationRunnable implements Runnable {
    private final Socket socket;
    private final ObjectOutputStream response;
    private final ObjectInputStream request;

    /**
     * Instantiates a new Authentication runnable.
     *
     * @param socket the socket
     * @throws IOException the io exception
     */
    public AuthenticationRunnable(Socket socket) throws IOException {
        this.socket = socket;
        this.response = new ObjectOutputStream(this.socket.getOutputStream());
        this.request = new ObjectInputStream(this.socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            AuthenticationCommand command = (AuthenticationCommand) this.request.readObject();

            if (command.isRegisterCommand()) {
                this.handleRegister((RegisterCommand) command);
            }

            if (command.isLoginCommand()) {
                this.handleLogin((LoginCommand) command);
            }
        } catch (IOException |
                ClassNotFoundException |
                invalidPlayerArgumentException |
                DuplicateGameRequestException |
                EmptyDatabaseException ioException) {
            ioException.printStackTrace();
        }
    }

    private void handleLogin(LoginCommand command) throws IOException, ClassNotFoundException, invalidPlayerArgumentException, DuplicateGameRequestException, EmptyDatabaseException {
        String username = command.getUsername();
        String password = command.getPassword();

        if (QueryBuilder.getSingletonInstance().userExist(username, password)) {
            User user = QueryBuilder.getSingletonInstance().selectUserByUsername(username);
            this.response.writeObject(new LoginResponseCommand(user, "Login Successful!"));


            PlayerWorker worker = new PlayerWorker(this.response,
                    this.request,
                    this.socket,
                    user
            );

            NewsCaster.getSingletonInstance().addOnlinePlayer(worker);
        } else {
            this.response.writeObject(new LoginResponseCommand(username, password, "Invalid credentials."));
        }
    }

    private void handleRegister(RegisterCommand command) throws IOException, ClassNotFoundException, EmptyDatabaseException {
        String username = command.getUsername();
        String password = command.getPassword();

        if (!QueryBuilder.getSingletonInstance().userExist(username, password)) {
            QueryBuilder.getSingletonInstance().insertUser(new User(username, password));


            User user = QueryBuilder.getSingletonInstance().selectUserByUsername(username);

            this.response.writeObject(new RegisterResponseCommand(
                    user,
                    "Register successful"
            ));

            PlayerWorker worker = new PlayerWorker(this.response,
                    this.request,
                    this.socket,
                    user
            );

            NewsCaster.getSingletonInstance().addOnlinePlayer(worker);
        } else {
            this.response.writeObject(new RegisterResponseCommand(username, password, "User with email already exist!"));
        }
    }
}
