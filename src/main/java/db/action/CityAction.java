package db.action;


import db.service.CityService;
import db.util.Reader;
import db.util.Writer;
import db.validator.CityValidator;
import org.springframework.stereotype.Component;

@Component
public class CityAction implements CityAct {


    private final String EXIT = "Введите exit - для выхода";
    private final String EMPTY_DB = "Cities data base is empty. ";
    private CityService cityService;
    private Reader consoleReader;
    private Writer consoleWriter;

    public CityAction(CityService cityService, Reader consoleReader, Writer consoleWriter) {
        this.cityService = cityService;
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
    }

    @Override
    public void deleteCity() {
        if (!cityServiceNullChecker()) {
            consoleWriter.showMessage("Введите город, который хотите удалить.");
            String readCityDelete = consoleReader.read();
            if (cityService.deleteCity(null)) {
                consoleWriter.showMessage("Город - " + readCityDelete + " успешно удален");
            }
            consoleWriter.showMessage("Нет такого города в базе данных");
        }
        consoleWriter.showMessage(EMPTY_DB);
        if (!cityServiceNullChecker()) {
            consoleWriter.showMessage("Хотите удалить еще один город? - y/n ");
            if (consoleReader.read().equalsIgnoreCase("y")) {
                deleteCity();
            } else {
                consoleWriter.showMenu();
            }
        }
        consoleWriter.showMessage(EMPTY_DB);
        consoleWriter.showMenu();
    }

    private boolean cityServiceNullChecker() {
        if (!cityService.getAllCities().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public void showAllCities() {
        if (cityServiceNullChecker()) {
            consoleWriter.showMessage("Города: \n");
            consoleWriter.showMessage(cityService.getAllCities().toString());
        } else consoleWriter.showMessage(EMPTY_DB);
    }

    @Override
    public void showCityByName() {
        if (!cityServiceNullChecker()) {
            consoleWriter.showMessage("Введите город, который хотите найти:\n");
            String cityByNameReader = consoleReader.read();
            if (cityService.getCityByName(cityByNameReader)) {
                consoleWriter.showMessage("Город '" + cityByNameReader + "' есть в базе данных.");
            }
            consoleWriter.showMessage("Город '" + cityByNameReader + "' - отсутствует в базе данных.");
            if (!cityServiceNullChecker()) {
                consoleWriter.showMessage("Хотите найти еще один город? - y/n ");
                if (consoleReader.read().equalsIgnoreCase("y")) {
                    showCityByName();
                } else {
                    consoleWriter.showMenu();
                }
            }
            consoleWriter.showMenu();
        }
    }

    @Override
    public void addCity() {
        String nameCity = getCityName();
        if (nameCity.isEmpty()) return;
        String nameCountry = getCityCountry();
        if (nameCountry.isEmpty()) return;
//        cityService.addCity(new City(nameCity, nameCountry));
    }

    private String getCityCountry() {
        consoleWriter.showMessage("Введите страну");
        while (true) {
            String readCountry = consoleReader.read();
            if (readCountry.equalsIgnoreCase("exit")) break;
            if (CityValidator.countryValid(readCountry)) {
                return readCountry;
            } else {
                consoleWriter.showMessage("Неправильно ввели страну");
                consoleWriter.showMessage(EXIT);
            }
        }
        return "";
    }

    private String getCityName() {
        consoleWriter.showMessage("Введите город");
        while (true) {
            String readName = consoleReader.read();
            if (readName.equalsIgnoreCase("exit")) break;
            if (CityValidator.cityValid(readName)) {
                return readName;
            } else {
                consoleWriter.showMessage("Неправилно ввели город");
                consoleWriter.showMessage("Введите город или exit - для выхода");
            }
        }
        return "";
    }
}
