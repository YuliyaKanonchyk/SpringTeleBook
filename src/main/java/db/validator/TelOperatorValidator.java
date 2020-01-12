package db.validator;

public class TelOperatorValidator {
    public static boolean telOperatorNameValid(String telOperatorName) {
        return telOperatorName.length() > 2;
    }
}
