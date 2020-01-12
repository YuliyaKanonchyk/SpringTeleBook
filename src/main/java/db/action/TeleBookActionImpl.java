package db.action;


import db.entity.City;
import db.entity.TeleBook;
import db.service.CityService;
import db.service.TeleBookService;
import db.util.Reader;
import db.util.Writer;
import org.springframework.stereotype.Component;

@Component
class TeleBookActionImpl implements TeleBookAction {

    private TeleBookService teleBookService;
    private CityService cityService;
    private Reader consoleReader;
    private Writer consoleWriter;

    TeleBookActionImpl(TeleBookService teleBookService, CityService cityService, Reader consoleReader, Writer consoleWriter) {
        this.teleBookService = teleBookService;
        this.cityService = cityService;
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
    }

    @Override
    public void showAllTeleBooks() {
        if (teleBookNullChecker()) {
            consoleWriter.showMessage("Телефонные книги: \n");
            for (TeleBook t : teleBookService.getAllTeleBooks()) {
                consoleWriter.showMessage("" + t);
            }
        }
    }

    private boolean teleBookNullChecker() {
        if (teleBookService.getAllTeleBooks() != null) {
            return true;
        } else {
            consoleWriter.showMessage("База данных Телефонных книг пуста.");
        }
        return false;
    }

    @Override
    public void addTeleBook() {
        consoleWriter.showMessage("Хотите создать все Телебуки для городов? - y/n\n");
        if (ifCitiesNotNull()) {
            if (consoleReader.read().equalsIgnoreCase("y"))
                teleBooksCreator();
        }
        consoleWriter.showMessage("База данных Городов пуста. Невозможно создать Телефоннуе книги.");
    }

    private boolean ifCitiesNotNull() {
        if (cityService.getAllCities() != null) {
            return true;
        }
        return false;
    }

    private void teleBooksCreator() {
        for (City showAllCity : cityService.getAllCities()) {
            if (showAllCity.getTeleBook() == null) {
                TeleBook teleBook = new TeleBook(showAllCity);
                showAllCity.setTeleBook(teleBook);
                teleBookService.addTeleBook(teleBook);
                consoleWriter.showMessage("Телефонная книга - " + teleBook + " успешно создана");
            }
        }
    }

    @Override
    public void deleteTeleBook() {
        consoleWriter.showMessage("Введите название Телебука для удаления.");
        String readTelebookDelete = consoleReader.read();
        if (teleBookNullChecker()) {
            if (teleBookService.delete(0)) {
                consoleWriter.showMessage("Телебук" + readTelebookDelete + " удален");
            }
            consoleWriter.showMessage("Телебук не удален");
        }
    }
}
