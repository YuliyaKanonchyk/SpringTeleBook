package db.action;


import db.entity.Abonent;
import db.entity.City;
import db.entity.TelNum;
import db.service.AbonentService;
import db.service.CityService;
import db.service.TelNumService;
import db.service.TelOperatorService;
import db.util.Reader;
import db.util.Writer;
import db.validator.AbonentValidator;
import org.springframework.stereotype.Component;

@Component
public class AbonentAction implements AbonAct {
    private final String EXIT = "Введите exit - для выхода";
    private final String EMPTY_DB = "Abonents Data Base is empty.";
    private final AbonentService abonentService;
    private final Reader consoleReader;
    private final Writer consoleWriter;
    private final CityService cityService;
    private final TelNumService telNumService;
    private final TelOperatorService telOperatorService;

    public AbonentAction(AbonentService abonentService,
                         Reader consoleReader, Writer consoleWriter,
                         CityService cityService, TelNumService telNumService,
                         TelOperatorService telOperatorService) {
        this.abonentService = abonentService;
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
        this.cityService = cityService;
        this.telNumService = telNumService;
        this.telOperatorService = telOperatorService;
    }

    @Override
    public void deleteAbonent() {
        consoleWriter.showMessage("Введите Фамилию Абонента.");
        String readAbonNameDelete = consoleReader.read();
        abonentService.showAbonentBySurName(readAbonNameDelete);
    }

    @Override
    public void showAllAbonents() {
        if (!abonentServiceNullChecker()) {
            for (Abonent showAllAbonent : abonentService.showAllAbonents()) {
                consoleWriter.showMessage(showAllAbonent.getAbonentName());
            }
        } else consoleWriter.showMessage(EMPTY_DB);
    }

    private boolean abonentServiceNullChecker() {
        if (!abonentService.showAllAbonents().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Abonent showAbonentSurName() {
        consoleWriter.showMessage("Введите Фамилию Абонента или EXIT для выхода в меню");
        while (true) {
            String readAbonentSurName = consoleReader.read();
            if (readAbonentSurName.equalsIgnoreCase("exit")) break;
            if (AbonentValidator.validSurName(readAbonentSurName)) {
                abonentService.showAbonentBySurName(readAbonentSurName);
            } else {
                consoleWriter.showMessage("Неверная Фамилия Абонента");
                consoleWriter.showMessage(EXIT);
            }
        }
        return null;
    }

    @Override
    public void updateAbonent() {
        consoleWriter.showMessage("Фамилия Абонента, которую нужно изменить");
        String readAbonSurName = getAbonentSurName();
        consoleWriter.showMessage("Фамилия Абонента, на которую нужно изменить");
        String abonentSurNameEnd = getAbonentNewSurName();
        if (!abonentService.updateAbonent(readAbonSurName, abonentSurNameEnd)) {
            consoleWriter.showMessage("Абонента с фамилией " + readAbonSurName + " нет в Базе данных!");
        }
        consoleWriter.showMessage("Фамилия Абонента - " + readAbonSurName + "изменена на фамилию - " + abonentSurNameEnd);
    }

    private String getAbonentNewSurName() {
        consoleWriter.showMessage("Введите Фамилию Абонента или EXIT для выхода в меню");
        while (true) {
            String abonentSurNameEnd = consoleReader.read();
            if (abonentSurNameEnd.equalsIgnoreCase("exit")) break;
            if (AbonentValidator.validSurName(abonentSurNameEnd)) {
                return abonentSurNameEnd;
            } else {
                consoleWriter.showMessage(EXIT);
            }
        }
        return "";
    }

    @Override
    public void addAbonent() throws NoSuchFieldException, IllegalAccessException {
        String abonentName = getAbonentName();
        if (abonentName.isEmpty()) return;
        String abonentSurName = getAbonentSurName();
        if (abonentSurName.isEmpty()) return;
        City abonentCity = getAbonentCity();
        if (abonentCity == null) return;
        TelNum abonentTelNum = getAbonentTelNum();
        if (abonentTelNum == null) return;
        Abonent abonent = new Abonent(abonentName, abonentSurName, abonentTelNum, abonentCity);
        abonentService.addAbonent(abonent);
        telNumService.addTelNum(abonentTelNum);
    }

    private String getAbonentName() {
        consoleWriter.showMessage("Enter the Abonent name.");
        while (true) {
            String readAbonName = consoleReader.read();
            if (readAbonName.equalsIgnoreCase("exit")) break;
            if (AbonentValidator.validFirstName(readAbonName)) {
                return readAbonName;
            } else {
                consoleWriter.showMessage("A wrong Abonent name.");
                consoleWriter.showMessage(EXIT);
            }
        }
        return "";
    }

    private String getAbonentSurName() {
        consoleWriter.showMessage("Enter the Surname of the Abonent or EXIT to 'exit' the menu.");
        while (true) {
            String readAbonSurName = consoleReader.read();
            if (readAbonSurName.equalsIgnoreCase("exit")) break;
            if (AbonentValidator.validSurName(readAbonSurName)) {
                return readAbonSurName;
            } else {
                consoleWriter.showMessage("A wrong Surname of the Abonent.");
                consoleWriter.showMessage(EXIT);
            }
        }
        return "";
    }

    private City getAbonentCity() {
        boolean fl = true;
        while (fl) {
            consoleWriter.showMessage("Выберите город Абонента.");
            String cityFirstChar = consoleReader.read();
            if (!cityEnteredNull(cityFirstChar)) {
                return (City) cityService.getCityFirstChar(cityFirstChar);


//            Map<Integer, City> integerCityMap;
//            if ((integerCityMap = showCitiList(cityByNameReader)) != null) {
//                if (integerCityMap.size() != 0) {
//                    for (Map.Entry<Integer, City> a :
//                            integerCityMap.entrySet()) {
//                        consoleWriter.showMessage(a.getKey() + " - " + a.getValue().getName());
//                    }
//                    consoleWriter.showMessage("Введите номер города");
//                    Integer integer = consoleReader.readCommand();
//                    if (integer < integerCityMap.size()) {
//                        for (Map.Entry<Integer, City> a :
//                                integerCityMap.entrySet()) {
//                            if (a.getKey().equals(integer)) return a.getValue();
//                        }
//                    } else {
//                        consoleWriter.showMessage("Неправильный номер");
//                    }
//                } else {
//                    consoleWriter.showMessage("Нет такого города. Введите снова или exit для выхода");
//                }
//            }
            }
        }
        return null;
    }

    private boolean cityEnteredNull(String cityByNameReader) {
        if (cityByNameReader.isEmpty()) {
            return true;
        }
        return false;
    }

    private TelNum getAbonentTelNum() {
        consoleWriter.showMessage("Enter the Abonent number.");
        while (true) {
            String readAbonTelNum = consoleReader.read();
            if (readAbonTelNum.equalsIgnoreCase("exit")) break;
            if (AbonentValidator.validTelNum(readAbonTelNum)) {
                return new TelNum(readAbonTelNum, null);
            } else {
                consoleWriter.showMessage("A wrong Abonent number.");
                consoleWriter.showMessage(EXIT);
            }
        }
        return null;
    }


}
