package Database.QueryBuilder;

import Models.GameResult;
import user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The type History query builder.
 */
public class HistoryQueryBuilder {
    private final File historyDb;

    private HistoryQueryBuilder() {
        this.historyDb = new File("client/src/main/java/Database/files/history.database.binary");
    }

    /**
     * Insert.
     *
     * @param user   the user
     * @param result the result
     * @throws IOException the io exception
     */
    public void insert(User user, GameResult result) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(this.historyDb, true));

        HashMap<User, GameResult> userToResultMap = new HashMap<>();
        userToResultMap.put(user, result);

        outputStream.writeObject(userToResultMap);
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

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(this.historyDb));
        ArrayList<GameResult> results = new ArrayList<>();
        while (true) {
            try {
                HashMap<User, GameResult> userToResultMap = (HashMap<User, GameResult>) inputStream.readObject();
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
}
