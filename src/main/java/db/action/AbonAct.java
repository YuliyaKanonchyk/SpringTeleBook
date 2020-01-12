package db.action;

import db.entity.Abonent;

public interface AbonAct {
    void deleteAbonent();
    void showAllAbonents();
    Abonent showAbonentSurName();
    void updateAbonent();
    void addAbonent() throws NoSuchFieldException, IllegalAccessException;
}
