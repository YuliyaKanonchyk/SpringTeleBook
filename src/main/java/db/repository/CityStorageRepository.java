package db.repository;

import java.util.List;

public interface CityStorageRepository<City> {

    boolean addCity(City city);

    boolean deleteCity(int id);

    List<City> getAllCities();

}
