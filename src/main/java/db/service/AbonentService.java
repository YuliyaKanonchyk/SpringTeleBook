package db.service;


import db.entity.Abonent;

import java.util.List;

public interface AbonentService {
    boolean updateAbonent(String startSurName, String surName);

    void addAbonent(Abonent a) throws NoSuchFieldException, IllegalAccessException;

    void deleteAbonent(Abonent abonent);

    Abonent showAbonentById(int id);

    List<Abonent> showAllAbonents();

    void showAbonentBySurName(String readAbonNameDelete);
}
