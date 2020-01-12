package db.repository;

import java.util.List;

public interface AbonentStorageRepository<Abonent> {

    boolean updateFirstName(int id, String s);
    boolean updateSurName(int id, String sur);
    boolean addAbonent(Abonent abonent);
    boolean deleteAbonent(int id);
    Abonent findAbonentByID(int id);
    List<Abonent> getAllAbonents();
}
