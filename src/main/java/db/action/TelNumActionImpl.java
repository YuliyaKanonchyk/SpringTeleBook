package db.action;

import db.entity.TelNum;
import db.service.TelNumService;
import db.service.TelOperatorService;
import db.util.Reader;
import db.util.Writer;
import db.validator.TelNumValidator;
import org.springframework.stereotype.Component;

@Component
public class TelNumActionImpl implements TelNumAction {
    private final String EXIT = "Введите exit - для выхода";
    private final String EMPTY_DB = "TelNum data base is empty. ";
    private Reader consoleReader;
    private Writer consoleWriter;
    private TelOperatorService telOperatorService;
    private TelNumService telNumService;

    public TelNumActionImpl(Reader consoleReader, Writer consoleWriter, TelOperatorService telOperatorService, TelNumService telNumService) {
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
        this.telOperatorService = telOperatorService;
        this.telNumService = telNumService;
    }

    @Override
    public String addTelNum() {
        consoleWriter.showMessage("Введите номер телефона");
        while (true) {
            String readTelNum = consoleReader.read();
            if (readTelNum.equalsIgnoreCase("exit")) break;
            if (TelNumValidator.telNumValidator(readTelNum)) {
                return readTelNum;
            } else {
                consoleWriter.showMessage("Неправилно ввели номер телефона");
                consoleWriter.showMessage("Введите номер телефона или exit - для выхода");
            }
        }
        return "";
    }

    @Override
    public void deleteTelNum() {
        if (!TelNumNullChecker()) {
            consoleWriter.showMessage("Введите номер телефона, который хотите удалить.");
            String readTelNumDelete = consoleReader.read();
            if (telNumService.deleteTelNum(0)) {
                consoleWriter.showMessage("Телефонный номер - " + readTelNumDelete + " успешно удален");
            }
            consoleWriter.showMessage("Нет такого телефонного номера в базе данных");
        }
        consoleWriter.showMessage(EMPTY_DB);
        if (!TelNumNullChecker()) {
            consoleWriter.showMessage("Хотите удалить еще один номер телефона? - y/n ");
            if (consoleReader.read().equalsIgnoreCase("y")) {
                deleteTelNum();
            } else {
                consoleWriter.showMenu();
            }
        }
        consoleWriter.showMessage(EMPTY_DB);
        consoleWriter.showMenu();
    }

    @Override
    public void updateTelNum() {
        consoleWriter.showMessage("Телефонный номер Абонента, который нужно изменить");
        String readTelNumStart = getStartTelNum();
        consoleWriter.showMessage("Телефонный номер Абонента, на который нужно изменить");
        String readTelNumNew = getNewTelNum();
        if (!telNumService.updateTelNum(readTelNumStart, readTelNumNew)) {
            consoleWriter.showMessage("Абонента с фамилией " + readTelNumStart + " нет в Базе данных!");
        }
        consoleWriter.showMessage("Фамилия Абонента - " + readTelNumStart + "изменена на фамилию - " + readTelNumNew);
    }

    private String getNewTelNum() {
        consoleWriter.showMessage("Enter the TelNum of the Abonent or EXIT to 'exit' the menu.");
        while (true) {
            String readTelNumNew = consoleReader.read();
            if (readTelNumNew.equalsIgnoreCase("exit")) break;
            if (TelNumValidator.telNumValidator(readTelNumNew)) {
                return readTelNumNew;
            } else {
                consoleWriter.showMessage("A wrong TelNum format.");
                consoleWriter.showMessage(EXIT);
            }
        }
        return "";
    }

    private String getStartTelNum() {
        consoleWriter.showMessage("Enter the TelNum of the Abonent or EXIT to 'exit' the menu.");
        while (true) {
            String readTelNumStart = consoleReader.read();
            if (readTelNumStart.equalsIgnoreCase("exit")) break;
            if (TelNumValidator.telNumValidator(readTelNumStart)) {
                return readTelNumStart;
            } else {
                consoleWriter.showMessage("A wrong TelNum of the Abonent.");
                consoleWriter.showMessage(EXIT);
            }
        }
        return "";
    }

    @Override
    public void showAllTelNums() {
        TelNumNullChecker();
        consoleWriter.showMessage("Номера телефонов: \n");
        for (TelNum t : telNumService.showAllTelNums()) {
            consoleWriter.showMessage("- " + t);
        }
    }

    private boolean TelNumNullChecker() {
        if (telNumService.showAllTelNums() != null) {
            return true;
        } else {
            consoleWriter.showMessage("База данных телефонных номеров пуста.");
        }
        return false;
    }
}
