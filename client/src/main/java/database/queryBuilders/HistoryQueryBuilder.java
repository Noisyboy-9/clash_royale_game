package database.queryBuilders;

import database.models.GameResult;
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
    private final File historyDb = new File("client/src/main/java/Database/files/history.database.binary");
    private ObjectOutputStream writer;

    private HistoryQueryBuilder() {
        try {
            this.writer = new ObjectOutputStream(new FileOutputStream(this.historyDb, true));
        } catch (IOException e) {
            e.printStackTrace();
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

        ObjectInputStream reader = new ObjectInputStream(new FileInputStream(this.historyDb));
        ArrayList<GameResult> results = new ArrayList<>();
        while (true) {
            try {
                HashMap<User, GameResult> userToResultMap = (HashMap<User, GameResult>) reader.readObject();
                for (User keyUser : userToResultMap.keySet()) {
                    if (user.equals(keyUser)) {
                        results.add(userToResultMap.get(keyUser));
                    }
                }
            } catch (EOFException e) {
                break;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                return results;
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
