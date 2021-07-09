package user;

/**
 * The User model.
 */
public class User {
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;

    private int currentXp;
    private UserLevelEnum level;

    /**
     * Instantiates a new User and sets currentXp to zero
     * and level to level_1
     *
     * @param firstname the firstname
     * @param lastname  the lastname
     * @param email     the email
     * @param password  the password
     */
    public User(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;

//        when a user is created it's level is one and it's xp is 0
        this.currentXp = 0;
        this.level = UserLevelEnum.LEVEL_1;
    }

    /**
     * Gets firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
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
