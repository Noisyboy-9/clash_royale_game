package user;

import java.io.Serializable;
import java.util.Objects;

/**
 * The User model.
 */
public class User implements Serializable {
    private final String username;
    private final String password;

    private int currentXp;
    private UserLevelEnum level;

    /**
     * Instantiates a new User and sets currentXp to zero
     * and level to level_1
     *
     * @param username the email
     * @param password the password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;

//        when a user is created it's level is one and it's xp is 0
        this.currentXp = 0;
        this.level = UserLevelEnum.LEVEL_1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public UserLevelEnum getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(UserLevelEnum level) {
        this.level = level;
    }

    /**
     * Gets current xp.
     *
     * @return the current xp
     */
    public int getCurrentXp() {
        return currentXp;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Level up the user.
     * if the user is level 5 it will not level up it will
     * just reset the level to 5 again.
     */
    public void levelUp() {
        this.level = switch (this.level) {
            case LEVEL_1 -> UserLevelEnum.LEVEL_2;
            case LEVEL_2 -> UserLevelEnum.LEVEL_3;
            case LEVEL_3 -> UserLevelEnum.LEVEL_4;
            case LEVEL_4, LEVEL_5 -> UserLevelEnum.LEVEL_5;
        };
    }

    /**
     * Add xp the amount of current xp of the user.
     *
     * @param amount the amount
     */
    public void addXp(int amount) {
        this.currentXp += amount;
    }
}
