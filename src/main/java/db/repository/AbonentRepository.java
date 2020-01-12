package db.repository;

import db.entity.Abonent;
import db.entity.City;
import db.entity.TelNum;
import db.entity.TelOperator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AbonentRepository implements AbonentStorageRepository<Abonent> {

    private final String CREATE_ABONENT_TABLE = "CREATE TABLE abonentss (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, abonent_name VARCHAR(15) NOT NULL, " +
            "abonent_Sur_name VARCHAR(20) NOT NULL, city_id INT NOT NULL, tel_num_id INT NOT NULL)";
    private final String INSERT_ABONENT = "INSERT INTO abonents (abonent_name, abonent_Sur_name, city_id, tel_num_id) VALUES (?,?,?, ?)";
    private final String DELETE_ABONENT = "DELETE FROM abonents WHERE id=?";
    private final String UPDATE_FIRST_NAME = "update abonents set abonent_name=? where id=?";
    private final String INSERT_TEL_NUM = "INSERT INTO telnum (telNumber, tel_op_id) VALUES (?, ?)";
    private final String FIND_ABONENT_BY_ID = "select * from abonents a where a.id = ?";
    private final String FIND_ALL_ABONENTS = "SELECT * FROM abonents " +
            "join city c on abonents.city_id = c.id join telnum on abonents.tel_num_id=telnum.id";
    private final String FIND_ID_OF_TEL_NUM = "SELECT t.id from telnum t WHERE telNumber=?";

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
            PreparedStatement ps = null;
            PreparedStatement check_table_abonents = connection.prepareStatement("CHECK TABLE abonents");
            ResultSet resultSet = ps.executeQuery();
            if (!check_table_abonents.equals("OK")) {
                ps = connection.prepareStatement(CREATE_ABONENT_TABLE);
                ps.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addAbonent(Abonent abonent) {
        try {
            connection.setAutoCommit(false);
            int telNumID = setTelNumToSQL_DB(abonent);
            setAbonentToSQL_DB(abonent, telNumID);
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setAbonentToSQL_DB(Abonent abonent, int telNumID) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_ABONENT);
        ps.setString(1, abonent.getAbonentName());
        ps.setString(2, abonent.getAbonentSurname());
        ps.setInt(3, abonent.getCity().getId());
        ps.setInt(4, telNumID);
        ps.execute();
    }

    private int setTelNumToSQL_DB(Abonent abonent) throws SQLException {
        //в таблицу телефонных номеров засетился номер с Id оператора
        PreparedStatement ps = connection.prepareStatement(INSERT_TEL_NUM);
        ps.setString(1, abonent.getTelNum().getTelNumber());
        ps.setInt(2, abonent.getTelNum().getTelOperator().getId());
        ps.execute();
        //узнаем Id под которым засетился в таблицу телефонных номеров номер
        ps = connection.prepareStatement(FIND_ID_OF_TEL_NUM);
        ps.setString(1, abonent.getTelNum().getTelNumber());
        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    @Override
    public boolean deleteAbonent(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_ABONENT);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Abonent findAbonentByID(int id) {
        Abonent abonent = new Abonent();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ABONENT_BY_ID);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            abonent.setId(resultSet.getInt(1));
            abonent.setAbonentName(resultSet.getString(2));
            abonent.setAbonentSurname(resultSet.getString(3));
            abonent.setTelNum(new TelNum(resultSet.getString(4), new TelOperator(resultSet.getString(5))));
            abonent.setCity(new City(resultSet.getString(6), resultSet.getString(7)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abonent;
    }

    @Override
    public boolean updateFirstName(int id, String firstName) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_FIRST_NAME);
            ps.setString(1, firstName);
            ps.setInt(2, id);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateSurName(int id, String sur) {
        try {
            connection.setAutoCommit(false);
            Abonent abonentByID = findAbonentByID(id);
            if (abonentByID != null) {
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Abonent> getAllAbonents() {
        List<Abonent> abonentList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL_ABONENTS);
            ps.execute();
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Abonent abonent = new Abonent();
                City city = new City();
                TelNum telNum = new TelNum();
                abonent.setId(resultSet.getInt("id"));
                abonent.setAbonentName(resultSet.getString(2));
                abonent.setAbonentSurname(resultSet.getString(3));
                city.setId(resultSet.getInt(6));
                city.setName(resultSet.getString(7));
                abonent.setCity(city);
                telNum.setId(resultSet.getInt(9));
                telNum.setTelNumber(resultSet.getString(10));
                abonent.setTelNum(telNum);
                abonentList.add(abonent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return abonentList;
    }

}
