package db.action;


import db.config.AppConfig;
import db.service.*;
import db.util.Reader;
import db.util.Writer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

// TODO: 07.01.2020 Подключить проперти файл к репозиториям
@Component
public class ConsoleApplication implements Application {

    final String NO_OPERATION = "Нет такой операции!";
    private Reader consoleReader;
    private Writer consoleWriter;
    private TelNumService telNumService;
    private TeleBookService teleBookService;
    private AbonentService abonentService;
    private CityService cityService;
    private TelOperatorService telOperatorService;
    private AbonentAction abonentAction;
    private CityAct cityAction;
    private TeleBookAction teleBookAction;
    private UserAction userAction;
    private TelOperatorAction telOperatorAction;
    private TelNumAction telNumAction;

    public ConsoleApplication(Reader consoleReader, Writer consoleWriter, TelNumService telNumService, TeleBookService teleBookService, AbonentService abonentService, CityService cityService, TelOperatorService telOperatorService, AbonentAction abonentAction, CityAct cityAction, TeleBookAction teleBookAction, UserAction userAction, TelOperatorAction telOperatorAction, TelNumAction telNumAction) {
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
        this.telNumService = telNumService;
        this.teleBookService = teleBookService;
        this.abonentService = abonentService;
        this.cityService = cityService;
        this.telOperatorService = telOperatorService;
        this.abonentAction = abonentAction;
        this.cityAction = cityAction;
        this.teleBookAction = teleBookAction;
        this.userAction = userAction;
        this.telOperatorAction = telOperatorAction;
        this.telNumAction = telNumAction;
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        applicationContext.refresh();
        ConsoleApplication consoleApplication = applicationContext.getBean("consoleApplication", ConsoleApplication.class);
        consoleApplication.start();
    }

    @Override
    public void start() throws NoSuchFieldException, IllegalAccessException {
        makeChoiceGeneral();
        consoleWriter.byEnd();
    }

    private void makeChoiceGeneral() throws NoSuchFieldException, IllegalAccessException {
        boolean fl = true;
        while (fl) {
            consoleWriter.showMenu();
            switch (consoleReader.readCommand()) {
                case 1:
                    userOperations();
                    fl = ifExit();
                    break;
                case 2:
                    moderatorOperations();
                    fl = ifExit();
                    break;
                case 3:
                    adminGeneralOperations();
                    fl = ifExit();
                    break;
                case 0:
                    fl = false;
                    return;
                default:
                    consoleWriter.showMessage(NO_OPERATION);
            }
        }
    }


    private void userOperations() throws NoSuchFieldException, IllegalAccessException {
        boolean fl = true;
        while (fl) {
            consoleWriter.showUserMenu();
            switch (consoleReader.readCommand()) {
                case 1:
                    userAction.searchByName();
                    fl = ifExit();
                    consoleWriter.showMenu();
                    break;
                case 2:
                    userAction.searchByNumber();
                    fl = ifExit();
                    consoleWriter.showMenu();
                    break;
                case 3:
                    userAction.searchBySurname();
                    fl = ifExit();
                    consoleWriter.showMenu();
                    break;
                case 0:
//                    fl = false;
                    return;
                default:
                    consoleWriter.showMessage(NO_OPERATION);
                    start();
            }
        }
    }

    private void moderatorOperations() throws NoSuchFieldException, IllegalAccessException {
        boolean fl = true;
        while (fl) {
            switch (consoleReader.readCommand()) {
                case 1:
                    abonentAction.addAbonent();
                    fl = ifExit();
                    consoleWriter.showMenu();
                    break;
                case 2:
                    abonentAction.deleteAbonent();
                    fl = ifExit();
                    consoleWriter.showMenu();
                    break;
                case 3:
                    abonentAction.showAllAbonents();
                    fl = ifExit();
                    consoleWriter.showMenu();
                    break;
                case 4:
                    abonentAction.updateAbonent();
                    fl = ifExit();
                    consoleWriter.showMenu();
                    break;
                case 0:
//                    fl = false;
//                    consoleWriter.showMenu();
                    return;
                default:
                    consoleWriter.showMessage(NO_OPERATION);
                    start();
            }
        }
    }

    private void adminGeneralOperations() throws NoSuchFieldException, IllegalAccessException {
        boolean fl = true;
        while (fl) {
            consoleWriter.showGeneralAdminMenu();
            switch (consoleReader.readCommand()) {
                case 1:
                    adminAbonentMenu();
                    fl = ifExit();
                    consoleWriter.showMenu();
                    break;
                case 2:
                    adminTeleBookMenu();
                    fl = ifExit();
                    consoleWriter.showMenu();
                    break;
                case 3:
                    adminCityMenu();
                    fl = ifExit();
                    consoleWriter.showMenu();
                    break;
                case 4:
                    adminTelOperatorMenu();
                    fl = ifExit();
                    consoleWriter.showMenu();
                    break;
                case 5:
                    adminTelNumMenu();
                    fl = ifExit();
                    consoleWriter.showMenu();
                    break;
                case 0:
                    fl = false;
                    consoleWriter.showMenu();
                    return;
                default:
                    consoleWriter.showMessage(NO_OPERATION);
            }
        }
    }


    private void adminTelNumMenu() {
        boolean fl = true;
        while (fl) {
            consoleWriter.showAdminTelNumMenu();
            switch (consoleReader.readCommand()) {
                case 1:
                    telNumAction.addTelNum();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 2:
                    telNumAction.deleteTelNum();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 3:
                    telNumAction.updateTelNum();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 4:
                    telNumAction.showAllTelNums();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 0:
//                    fl = false;
//                    consoleWriter.showGeneralAdminMenu();
                    return;
                default:
                    consoleWriter.showMessage(NO_OPERATION);
            }
        }
    }

    private void adminTelOperatorMenu() {
        boolean fl = true;
        while (fl) {
            consoleWriter.showAdminTelOperatorMenu();
            switch (consoleReader.readCommand()) {
                case 1:
                    telOperatorAction.addTelOperator();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 2:
                    telOperatorAction.deleteTelOperator();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 3:
                    telOperatorAction.updateTelOperator();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 4:
                    telOperatorAction.showAllTelOperators();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 0:
//                    fl = false;
//                    consoleWriter.showGeneralAdminMenu();
                    return;
                default:
                    consoleWriter.showMessage(NO_OPERATION);
            }
        }
    }

    private void adminCityMenu() {
        boolean fl = true;
        while (fl) {
            consoleWriter.showAdminCityMenu();
            switch (consoleReader.readCommand()) {
                case 1:
                    cityAction.addCity();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 2:
                    cityAction.deleteCity();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 3:
                    cityAction.showCityByName();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 4:
                    cityAction.showAllCities();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 0:
                    fl = false;
                    consoleWriter.showGeneralAdminMenu();
                    return;
                default:
                    consoleWriter.showMessage(NO_OPERATION);
            }
        }
    }


    private void adminTeleBookMenu() {
        boolean fl = true;
        while (fl) {
            consoleWriter.showAdminTeleBookMenu();
            switch (consoleReader.readCommand()) {
                case 1:
                    teleBookAction.addTeleBook();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 2:
                    teleBookAction.deleteTeleBook();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 3:
                    teleBookAction.showAllTeleBooks();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 0:
//                    fl = false;
                    return;
                default:
                    consoleWriter.showMessage(NO_OPERATION);
            }
        }
    }

    private void adminAbonentMenu() throws NoSuchFieldException, IllegalAccessException {
        boolean fl = true;
        while (fl) {
            consoleWriter.showAdminAbonentMenu();
            switch (consoleReader.readCommand()) {
                case 1:
                    abonentAction.addAbonent();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 2:
                    abonentAction.showAllAbonents();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 3:
                    abonentAction.deleteAbonent();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 4:
                    abonentAction.updateAbonent();
                    fl = ifExit();
                    consoleWriter.showGeneralAdminMenu();
                    break;
                case 0:
//                    fl = false;
//                    consoleWriter.showGeneralAdminMenu();
                    return;
                default:
                    consoleWriter.showMessage(NO_OPERATION);
            }
        }
    }

    private boolean ifExit() {
        consoleWriter.showMessage("Выйти из программы - Y, Выйти в предыдущее меню меню - N");
        String readChoice = consoleReader.read();
        if (readChoice.equalsIgnoreCase("Y")) {
            return false;
        }
        if (readChoice.equalsIgnoreCase("N")) {
            return false;
        } else {
            consoleWriter.showMessage(NO_OPERATION);
        }
        return true;
    }
}