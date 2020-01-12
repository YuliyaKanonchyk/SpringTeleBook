package db.service;

import db.entity.TeleBook;

import java.util.List;

public interface TeleBookService {
    boolean addTeleBook(TeleBook t);

    boolean delete(int id);

    List<TeleBook> getAllTeleBooks();

}
