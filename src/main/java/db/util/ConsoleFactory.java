package db.util;

public class ConsoleFactory {

    public static Reader getReader() {
        return new ConsoleReader();
    }

    public static Writer getWriter() {
        return new ConsoleWriter();
    }
}
