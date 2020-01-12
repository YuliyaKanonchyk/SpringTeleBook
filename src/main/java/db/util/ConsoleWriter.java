package db.util;

import org.springframework.stereotype.Component;

@Component
public class ConsoleWriter implements Writer {

    private void welcomeStart() {
        System.out.println("Welcome to TeleBook Application!\n");
        System.out.println("Please, make your choice:\n");
        System.out.println("Press: 1 - to User menu");
        System.out.println("Press: 2 - to Moderator menu");
        System.out.println("Press: 3 - to Admin menu");
        System.out.println("Press: 0 - to QUITE the program\n");
    }

    @Override
    public void byEnd() {
        System.out.println("See you soon!\n");
    }

    @Override
    public void showUserMenu() {
        System.out.println("Please, make your choice:\n");
        System.out.println("Press: 1 - Search By Name");
        System.out.println("Press: 2 - Search By Number");
        System.out.println("Press: 3 - Search By Surname");
        System.out.println("Press: 0 - to RETURN to Start Menu\n");
    }

    @Override
    public void showModeratorMenu() {
        System.out.println("Please, make your choice:\n");
        System.out.println("Press: 1 - to ADD an Abonent");
        System.out.println("Press: 2 - to DELETE an Abonent");
        System.out.println("Press: 3 - to SHOW All the Abonents");
        System.out.println("Press: 4 - to UPDATE an Abonent");
        System.out.println("Press: 0 - to RETURN to Start Menu\n");
    }

    @Override
    public void showGeneralAdminMenu() {
        System.out.println("Please, make your choice:\n");
        System.out.println("Press: 1 - Work with Abonent-menu");
        System.out.println("Press: 2 - Work with TeleBook-menu");
        System.out.println("Press: 3 - Work with City-menu");
        System.out.println("Press: 4 - Work with TelOperator-menu");
        System.out.println("Press: 5 - Work with TelNum-menu");
        System.out.println("Press: 0 - to RETURN to Start Menu\n");
    }

    @Override
    public void showAdminAbonentMenu() {
        System.out.println("Please, make your choice:\n");
        System.out.println("Press: 1 - to ADD an Abonent");
        System.out.println("Press: 2 - to SHOW All the Abonents");
        System.out.println("Press: 3 - to DELETE an Abonent");
        System.out.println("Press: 4 - to UPDATE an Abonent");
        System.out.println("Press: 0 - to RETURN to General Admin Menu\n");
    }

    @Override
    public void showAdminTeleBookMenu() {
        System.out.println("Please, make your choice:\n");
        System.out.println("Press: 1 - to ADD a TeleBook");
        System.out.println("Press: 2 - to DELETE a TeleBook");
        System.out.println("Press: 3 - to SHOW ALL TeleBook");
        System.out.println("Press: 0 - to RETURN to General Admin Menu\n");
    }

    @Override
    public void showAdminCityMenu() {
        System.out.println("Please, make your choice:\n");
        System.out.println("Press: 1 - to ADD a City");
        System.out.println("Press: 2 - to DELETE a City");
        System.out.println("Press: 3 - to SHOW the City by name");
        System.out.println("Press: 4 - to SHOW ALL City");
        System.out.println("Press: 0 - to QUITE the program\n");
    }

    @Override
    public void showAdminTelOperatorMenu() {
        System.out.println("Please, make your choice:\n");
        System.out.println("Press: 1 - to ADD a TelOperator");
        System.out.println("Press: 2 - to DELETE a TelOperator");
        System.out.println("Press: 3 - to UPDATE a TelOperator");
        System.out.println("Press: 4 - to SHOW ALL the TelOperators");
        System.out.println("Press: 0 - to RETURN to General Admin Menu\n");
    }

    @Override
    public void showAdminTelNumMenu() {
        System.out.println("Please, make your choice:\n");
        System.out.println("Press: 1 - to ADD a TelNum");
        System.out.println("Press: 2 - to DELETE a TelNum");
        System.out.println("Press: 3 - to UPDATE a TelNum");
        System.out.println("Press: 4 - to SHOW ALL the TelNums");
        System.out.println("Press: 0 - to RETURN to General Admin Menu\n");
    }

    @Override
    public void showMenu() {
        welcomeStart();
    }

    @Override
    public void showMessage(String m) {
        System.out.println(m);
    }
}
