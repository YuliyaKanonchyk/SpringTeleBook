package db.service;

import db.entity.TeleBook;
import db.repository.TeleBookStorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeleBookServiceImpl implements TeleBookService {
    private TeleBookStorageRepository<TeleBook> teleBookRepository;


    public TeleBookServiceImpl(TeleBookStorageRepository<TeleBook> teleBookRepository) {
        this.teleBookRepository = teleBookRepository;
    }

    public TeleBookServiceImpl() {
        this.teleBookRepository = teleBookRepository;
    }

    @Override
    public boolean addTeleBook(TeleBook teleBook) {
        return teleBookRepository.addTeleBook(teleBook);
    }

    @Override
    public boolean delete(int id) {
        for (TeleBook t : getAllTeleBooks()) {
            if (t.getId() == id) {
                teleBookRepository.deleteTeleBook(id);
            }
            return true;
        }
        return false;
    }

    @Override
    public List<TeleBook> getAllTeleBooks() {
        return teleBookRepository.getAllTeleBooks();
    }

}
