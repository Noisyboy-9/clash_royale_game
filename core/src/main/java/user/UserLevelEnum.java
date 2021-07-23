package user;

/**
 * The enum User level enum.
 */
public enum UserLevelEnum {
    /**
     * Level 1 user level enum.
     */
    LEVEL_1("1"),
    /**
     * Level 2 user level enum.
     */
    LEVEL_2("2"),
    /**
     * Level 3 user level enum.
     */
    LEVEL_3("3"),
    /**
     * Level 4 user level enum.
     */
    LEVEL_4("4"),
    /**
     * Level 5 user level enum.
     */
    LEVEL_5("5");

    private String value;

    UserLevelEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}