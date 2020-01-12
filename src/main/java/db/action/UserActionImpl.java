package db.action;


import db.entity.Abonent;
import db.service.AbonentService;
import db.util.Reader;
import db.util.Writer;
import org.springframework.stereotype.Component;

@Component
public class UserActionImpl implements UserAction {

    private AbonentService abonentService;
    private Reader consoleReader;
    private Writer consoleWriter;

    public UserActionImpl(AbonentService abonentService, Reader consoleReader, Writer consoleWriter) {
        this.abonentService = abonentService;
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
    }

    @Override
    public void searchBySurname() {
        consoleWriter.showMessage("Введите Фамилию абонента, которого хотите найти.");
        String userBySurnameReader = consoleReader.read();
        if (valueIsEmpty(userBySurnameReader)) return;
        for (Abonent a : abonentService.showAllAbonents()) {
            if (a.getAbonentSurname().equalsIgnoreCase(userBySurnameReader)) {
                consoleWriter.showMessage("Абонент, которого вы искали - \n" + a);
                return;
            }
        }
        consoleWriter.showMessage("Абонента с Фамилией " +
                userBySurnameReader + " не существует в данной телефонной книге");
    }

    private boolean valueIsEmpty(String userBySurnameReader) {
        if (userBySurnameReader.isEmpty()) {
            consoleWriter.showMessage("Вы не ввели значение в строку.");
            return true;
        }
        return false;
    }

    @Override
    public void searchByNumber() {
        consoleWriter.showMessage("Введите Номер абонента, которого хотите найти.");
        String userByNumberReader = consoleReader.read();
        if (valueIsEmpty(userByNumberReader)) return;
        for (Abonent a : abonentService.showAllAbonents()) {
//            for (TelNum t : a.getTelNums()) {
//                if (t.getTelNumber().equalsIgnoreCase(userByNumberReader)) {
//                    consoleWriter.showMessage("Абонент, которого вы искали - \n" + a);
//                }
//            }
        }
        consoleWriter.showMessage("Абонента с номером " + userByNumberReader + " не существует в данной телефонной книге");
    }

    @Override
    public void searchByName() {
        consoleWriter.showMessage("Введите Имя абонента, которого хотите найти.");
        String userByNameReader = consoleReader.read();
        if (valueIsEmpty(userByNameReader)) return;
        for (Abonent a : abonentService.showAllAbonents()) {
            if (valueIsEmpty(a.getAbonentName())) {
                consoleWriter.showMessage("Список абонентов пуст.");
                break;
            }
            if (a.getAbonentName().equalsIgnoreCase(userByNameReader)) {
                consoleWriter.showMessage("Абонент, которого вы искали - \n" + a);
            }
            consoleWriter.showMessage("Абонента с именем " + userByNameReader + " не существует в данной телефонной книге");

        }
    }
}
