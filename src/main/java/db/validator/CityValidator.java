package db.validator;

public class CityValidator {

    public static boolean cityValid(String cityInput) {
        return cityInput.length() > 2;
    }
    public static boolean countryValid (String countryInput){
       return countryInput.length() > 2;
    }
}