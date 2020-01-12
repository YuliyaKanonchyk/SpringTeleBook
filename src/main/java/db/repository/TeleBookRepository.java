package db.repository;

import db.entity.City;
import db.entity.TeleBook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class TeleBookRepository implements TeleBookStorageRepository<TeleBook> {

    private final String CREATE_TELE_BOOK_TABLE = "create table tele_books(id int auto_increment telebook_name varchar(45) not null, city_id int not null, " +
            "constraint id_UNIQUE unique (id),constraint city_id_fk foreign key (city_id) references city (id) on update cascade on delete cascade)";
    private final String INSERT_TELE_BOOK = "INSERT INTO tele_book (telebook_name) VALUES (?)";
    private final String DELETE_TELE_BOOK = "DELETE FROM tele_book WHERE id=?";
    private final String INSERT_CITY = "INSERT INTO city (city_name, country) VALUES (?,?)";
    private final String FIND_ALL_TELE_BOOKS = "SELECT * FROM tele_book JOIN city c on tele_book.city_id = c.id";
    private final String FIND_CITY_ID_BY_NAME = "SELECT c.id FROM city c WHERE city_name = ?";

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
    }

    {
        try {
            PreparedStatement ps = connection.prepareStatement(CREATE_TELE_BOOK_TABLE);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addTeleBook(TeleBook teleBook) {
        try {
            connection.setAutoCommit(false);
            int city_id = setID_ToCity(teleBook);
            setTeleBookToSQL_DB(teleBook, city_id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setTeleBookToSQL_DB(TeleBook teleBook, int cityId) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_TELE_BOOK);
            ps.setString(1, teleBook.getCity().getName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int setID_ToCity(TeleBook teleBook) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_CITY);
            ps.setString(1, teleBook.getCity().getName());
            ps.setString(2, teleBook.getCity().getCountry());
            ps.execute();

            ps = connection.prepareStatement(FIND_CITY_ID_BY_NAME);
            ps.setString(1, teleBook.getCity().getName());
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean deleteTeleBook(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_TELE_BOOK);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<TeleBook> getAllTeleBooks() {
        List<TeleBook> teleBookList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL_TELE_BOOKS);
            ps.execute();
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                TeleBook teleBook = new TeleBook();
                teleBook.setId(resultSet.getInt("id"));
                teleBook.setCity(new City(resultSet.getString(2)));
                City city = new City();
                city.setId(resultSet.getInt("id"));
                city.setName(resultSet.getString(4));
                city.setName(resultSet.getString(5));
                teleBook.setCity(city);
                teleBookList.add(teleBook);
            }
            return teleBookList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
