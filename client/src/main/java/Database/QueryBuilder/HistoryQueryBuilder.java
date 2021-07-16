package Database.QueryBuilder;

import Models.GameResult;
import user.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class HistoryQueryBuilder {
    private final File historyDb;

    private HistoryQueryBuilder() {
        this.historyDb = new File("client/src/main/java/Database/files/history.database.binary");
    }

    public void insert(User user, GameResult result) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(this.historyDb, true));

        HashMap<User, GameResult> userToResultMap = new HashMap<>();
        userToResultMap.put(user, result);

        outputStream.writeObject(userToResultMap);
    }

    public ArrayList<GameResult> readAll(User user) throws IOException {
        if (this.historyDb.length() == 0) {
            return null;
        }

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(this.historyDb));
        while (true) {
            try {
                HashMap<User, GameResult> result = inputStream.readObject();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
