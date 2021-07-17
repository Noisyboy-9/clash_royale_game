package database;

import exceptions.EmptyDatabaseException;
import user.User;

import java.io.*;
import java.util.Objects;

/**
 * The type Query builder.
 */
public class QueryBuilder {
    private static QueryBuilder singletonInstance = null;
    private final File db;
    private ObjectOutputStream writer;

    private QueryBuilder() {
        this.db = new File("server/src/main/java/database/file/users.database.binary");
        try {
            this.writer = new ObjectOutputStream(new FileOutputStream(this.db, true));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * check if user already exist in database.     *
     *
     * @param username the username
     * @param password the password
     * @return the boolean
     * @throws IOException            the io exception
     * @throws ClassNotFoundException the class not found exception
     */
    public boolean userExist(String username, String password) throws IOException, ClassNotFoundException {
        if (this.db.length() == 0) {
//            if the file is empty, no user has been written to the database.
            return false;
        }

        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(this.db))) {
            while (true) {
                try {
                    User user = (User) reader.readObject();

                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        return true;
                    }
                } catch (EOFException eofException) {
                    break;
                }
            }
            return false;
        }
    }

    /**
     * Insert user to database.
     *
     * @param user the user
     * @throws IOException the io exception
     */
    public void insertUser(User user) throws IOException {
        this.writer.writeObject(user);
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

    public User selectUserByUsername(String username) throws EmptyDatabaseException, IOException {
        if (this.db.length() == 0) {
            throw new EmptyDatabaseException("The database is empty, no such user");
        }

        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(this.db))) {
            while (true) {
                try {
                    User user = (User) reader.readObject();

                    if (user.getUsername().equals(username)) {
                        return user;
                    }
                } catch (EOFException eofException) {
                    break;
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
            return null;
        }
    }
}
