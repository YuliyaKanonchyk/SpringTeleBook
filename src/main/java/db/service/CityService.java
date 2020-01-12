package db.service;


import db.entity.City;

import java.util.List;

public interface CityService {
    void addCity(City city);

    boolean deleteCity(City city);

    boolean getCityByName(String name);

    List<City> getCityFirstChar(String cityFirstChar);

    List<City> getAllCities();
}
