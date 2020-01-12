package db.service;


import db.entity.City;
import db.repository.CityStorageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private CityStorageRepository<City> cityRepository;

    public CityServiceImpl(CityStorageRepository<City> cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void addCity(City city) {
        cityRepository.addCity(city);
    }

    @Override
    public boolean deleteCity(City city) {
        for (City c : getAllCities()) {
            if (c.getName().equals(city.getName())) {
                cityRepository.deleteCity(c.getId());
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean getCityByName(String cityByNameReader) {
        for (City c : getAllCities()) {
            if (c.getName().equalsIgnoreCase(cityByNameReader)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.getAllCities();
    }

    @Override
    public List<City> getCityFirstChar(String cityFirstChar) {
        List<City> cityList = new ArrayList<>();
        for (City c : getAllCities()) {
            if (c.getName().equalsIgnoreCase(cityFirstChar)) {
                cityList.add(c);
            }
        }
        return cityList;
    }

}

