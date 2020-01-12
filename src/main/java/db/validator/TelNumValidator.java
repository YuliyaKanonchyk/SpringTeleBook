package db.validator;

public class TelNumValidator {
    public static boolean telNumValidator (String telNum) {
        return telNum.length()>=7;
    }
}
