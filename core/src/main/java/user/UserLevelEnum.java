package user;

/**
 * The enum User level enum.
 */
public enum UserLevelEnum {
    LEVEL_1("1"),
    LEVEL_2("2"),
    LEVEL_3("3"),
    LEVEL_4("4"),
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