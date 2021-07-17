package Database.QueryBuilder;

import Models.GameResult;
import user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * The type History query builder.
 */
public class HistoryQueryBuilder {
    private static HistoryQueryBuilder instance = null;
    private final File historyDb;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    private HistoryQueryBuilder() {
        this.historyDb = new File("client/src/main/java/Database/files/history.database.binary");
        try {
            this.reader = new ObjectInputStream(new FileInputStream(this.historyDb));
            this.writer = new ObjectOutputStream(new FileOutputStream(this.historyDb));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    /**
     * Insert.
     *
     * @param user   the user
     * @param result the result
     * @throws IOException the io exception
     */
    public void insert(User user, GameResult result) throws IOException {
        HashMap<User, GameResult> userToResultMap = new HashMap<>();
        userToResultMap.put(user, result);

        this.writer.writeObject(userToResultMap);
    }

    /**
     * Read all array list.
     *
     * @param user the user
     * @return the array list
     * @throws IOException the io exception
     */
    public ArrayList<GameResult> readAll(User user) throws IOException {
        if (this.historyDb.length() == 0) {
            return null;
        }

        ArrayList<GameResult> results = new ArrayList<>();
        while (true) {
            try {
                HashMap<User, GameResult> userToResultMap = (HashMap<User, GameResult>) this.reader.readObject();
                for (User keyUser : userToResultMap.keySet()) {
                    if (user.equals(keyUser)) {
                        results.add(userToResultMap.get(keyUser));
                    }
                }
            } catch (ClassNotFoundException e) {
                break;
            }
        }
        if (results.size() == 0) {
            return null;
        }

        return results;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static HistoryQueryBuilder getInstance() {
        if (Objects.isNull(instance)) {
            instance = new HistoryQueryBuilder();
        }
        return instance;
    }
}
