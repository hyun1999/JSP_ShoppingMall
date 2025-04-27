package shoppingMall.domain.enums;

public enum UserType
{
    User(10),
    Admin(20);

    private final int value;

    UserType(int value) {
        this.value = value;
    }
    public int getValue(){
        return value;
    }
}
