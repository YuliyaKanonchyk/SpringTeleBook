package db.action;


import db.entity.TelOperator;
import db.service.TelOperatorService;
import db.util.Reader;
import db.util.Writer;
import db.validator.TelOperatorValidator;
import org.springframework.stereotype.Component;

@Component
public class TelOperatorActionImpl implements TelOperatorAction{
   private TelOperatorService telOperatorService;
   private Reader consoleReader;
   private Writer consoleWriter;

    TelOperatorActionImpl(TelOperatorService telOperatorService,
                          Reader consoleReader, Writer consoleWriter) {
       this.telOperatorService = telOperatorService;
       this.consoleReader = consoleReader;
       this.consoleWriter = consoleWriter;
   }
@Override
   public String addTelOperator() {
       consoleWriter.showMessage("Введите название TelOperator'a");
       while (true) {
           String readTelOperator = consoleReader.read();
           if (readTelOperator.equalsIgnoreCase("exit")) break;
           if (TelOperatorValidator.telOperatorNameValid(readTelOperator)) {
               return readTelOperator;
           } else {
               consoleWriter.showMessage("Неправилно ввели TelOperator'a");
               consoleWriter.showMessage("Введите TelOperator или exit - для выхода");
           }
       }
       return "";
   }
    @Override
    public void deleteTelOperator() {
       consoleWriter.showMessage("Введите TelOperator'a, которого хотите удалить.");
       String readTelOperatorDelete = consoleReader.read();
       if (!TelOperatorNullChecker()) {
           for (TelOperator t : telOperatorService.showAllTelOperators()) {
               if (t.getName().equalsIgnoreCase(readTelOperatorDelete)) {
                   consoleWriter.showMessage("TelOperator - " + t + " успешно удален");
                   telOperatorService.deleteTelOperator(t);
               }
               consoleWriter.showMessage("Нет такого TelOperator'a в базе данных");
           }
           consoleWriter.showMessage("Хотите удалить еще один TelOperator? - y/n ");
           if (consoleReader.read().equalsIgnoreCase("y")) {
               deleteTelOperator();
           } else {
               consoleWriter.showMenu();
           }
       }
       consoleWriter.showMenu();
   }
    @Override
    public void updateTelOperator() {
       consoleWriter.showMessage("МЕТОД НЕ РЕАЛИЗОВАН!");
   }

    @Override
    public void showAllTelOperators() {
       TelOperatorNullChecker();
       consoleWriter.showMessage("TelOperator: \n");
       for (TelOperator t : telOperatorService.showAllTelOperators()) {
           consoleWriter.showMessage("- " + t);
       }
   }

   private boolean TelOperatorNullChecker() {
       if (telOperatorService.showAllTelOperators() != null) {
           return true;
       } else {
           consoleWriter.showMessage("База данных TelOperator пуста.");
       }
       return false;
   }
}
