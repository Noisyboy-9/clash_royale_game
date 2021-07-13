package database;

import user.User;

import java.io.*;
import java.util.Objects;

/**
 * The type Query builder.
 */
public class QueryBuilder {
    private static QueryBuilder singletonInstance = null;
    private final File db;

    private QueryBuilder() {
        this.db = new File("../database/binaryFile");
    }

    /**
     * check if user already exist in database.
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public boolean userExist(String username, String password) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(this.db));

        while (true) {
            try {
                User user = (User) inputStream.readObject();

                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return true;
                }
            } catch (EOFException eofException) {
                break;
            }
        }

        return false;
    }

    /**
     * Insert user to database.
     *
     * @param user the user
     */
    public void insertUser(User user) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(this.db));
        outputStream.writeObject(user);
    }

    /**
     * Gets singleton instance.
     *
     * @return the singleton instance
     */
    public static QueryBuilder getSingletonInstance() {
        if (Objects.isNull(singletonInstance)) {
            singletonInstance = new QueryBuilder();
        }

        return singletonInstance;
    }
}
