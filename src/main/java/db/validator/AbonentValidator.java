package db.validator;

public class AbonentValidator {
    public static boolean validFirstName(String firstName) {
        return firstName.length() > 2;
    }

    public static boolean validSurName(String surName) {
        return surName.length() > 2;
    }

    public static boolean validTelNum(String telNum) {
        return telNum.length() == 8;
    }
}
