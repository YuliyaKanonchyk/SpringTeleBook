package db.repository;

import db.entity.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class CityRepository implements CityStorageRepository<City> {

    private final String CREATE_CITY_TABLE = "CREATE TABLE citys (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, city_name VARCHAR(15) NOT NULL, country VARCHAR(20) NOT NULL)";
    private final String INSERT_ABONENT = "INSERT INTO abonents (abonent_name, abonent_Sur_name, city_id, tel_num_id) VALUES (?,?,?, ?)";
    private final String INSERT_CITY = "INSERT INTO city (city_name, country) VALUES (?,?)";
    private final String DELETE_CITY = "DELETE FROM city WHERE city.id = ?";
    private final String FIND_ALL_CITIES = "SELECT * FROM city";

    @Value("${login}")
    private String ROOT;
    @Value("${password}")
    private String PASSWORD;
    @Value("${URL}")
    private String URL;

    private Connection connection;

    {
        try {
            connection = DriverManager.getConnection(URL, ROOT, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("create table tel_operator\n" +
                    "(\n" +
                    "    id                int PRIMARY KEY ,\n" +
                    "    tel_operator_name varchar(15) not null,\n" +
                    "    constraint id_UNIQUE\n" +
                    "        unique (id)\n" +
                    ");\n" +
                    "\n" +
                    "alter table tel_operator\n" +
                    "    add primary key (id);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addCity(City city) {
        try {
            connection.setAutoCommit(false);
            setCity(city);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setCity(City city) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_CITY);
        ps.setString(1, city.getName());
        ps.setString(2, city.getCountry());
        ps.execute();
    }

    @Override
    public boolean deleteCity(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_CITY);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<City> getAllCities() {
        List<City> cityList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL_CITIES);
            ps.execute();
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                City city = new City();
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString(2));
                city.setCountry(resultSet.getString(3));
                cityList.add(city);
            }
            return cityList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
