package db.util;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleReader implements Reader {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public Integer readCommand() {
        return scanner.nextInt();
    }

    @Override
    public String read() {
        return scanner.next();
    }
}
