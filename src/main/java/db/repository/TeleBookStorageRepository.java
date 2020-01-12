package db.repository;

import java.util.List;

public interface TeleBookStorageRepository<TeleBook> {

    boolean addTeleBook(TeleBook teleBook);

    boolean deleteTeleBook(int id);

    List<TeleBook> getAllTeleBooks();
}
