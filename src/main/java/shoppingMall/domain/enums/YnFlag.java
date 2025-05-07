package shoppingMall.domain.enums;

public enum YnFlag {
    YES("Y"),
    NO("N");

    private final String dbValue;

    YnFlag(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static YnFlag fromDbValue(String value) {
        if ("Y".equalsIgnoreCase(value)) {
            return YES;
        } else {
            return NO;
        }
    }
}
