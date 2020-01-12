package db.repository;

import db.entity.Abonent;
import db.entity.TelNum;
import db.entity.TelOperator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class TelNumRepository implements TelNumStorageRepository<TelNum> {

    private final String INSERT_TEL_NUM = "INSERT INTO telnum (telNumber, tel_op_id) VALUES (?, ?)";
    private final String UPDATE_TEL_NUM = "UPDATE telnum SET telNumber =? WHERE telnum.id = ?";
    private final String FIND_ALL_TEL_NUMS = "SELECT * FROM telnum JOIN abonents ON telnum.id = abonents.tel_num_id";
    private final String FIND_ID_TEL_OPERATOR = "SELECT t.id FROM tel_operator t WHERE tel_operator_name = ?";
    private final String INSERT_TEL_OPERATOR = "INSERT INTO tel_operator (tel_operator_name) VALUES (?)";
    private final String DELETE_TEL_OPERATOR = "DELETE FROM telnum WHERE id = ?";
    private final String CREATE_TEL_NUM_TABLE = "CREATE TABLE telnums (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, telNumber VARCHAR(9) NOT NULL, tel_op_id INT NOT NULL)";

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
            Statement ps = connection.prepareStatement(CREATE_TEL_NUM_TABLE);
//            PreparedStatement ps = connection.prepareStatement(CREATE_TEL_NUM_TABLE);
//            ps.execute();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addTelNum(TelNum telNum) {
        try {
            connection.setAutoCommit(false);
            int telOperatorId = setTelOperator(telNum);
            if (telOperatorId >= 0) {
                setTelNum(telNum, telOperatorId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int setTelOperator(TelNum telNum) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_TEL_OPERATOR);
            ps.setString(1, telNum.getTelOperator().getName());
            ps.execute();
            ps = connection.prepareStatement(FIND_ID_TEL_OPERATOR);
            ps.setString(1, telNum.getTelOperator().getName());
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void setTelNum(TelNum telNum, int telOperatorId) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_TEL_NUM);
            ps.setString(1, telNum.getTelNumber());
            ps.setInt(2, telOperatorId);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteTelNum(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement(DELETE_TEL_OPERATOR);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateTelNum(int id, String finalTelNum) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_TEL_NUM);
            ps.setInt(2, id);
            ps.setString(1, finalTelNum);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public List<TelNum> getAllTelNums() {
        List<TelNum> telNumList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL_TEL_NUMS);
            ps.execute();
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                TelNum telNum = new TelNum();
                telNum.setId(resultSet.getInt("id"));
                telNum.setTelNumber(resultSet.getString(2));
                telNum.setTelOperator(new TelOperator(resultSet.getString(3)));
                telNumList.add(telNum);
                Abonent abonent = new Abonent();
                abonent.setId(resultSet.getInt("id"));
                abonent.setAbonentName(resultSet.getString(5));
                abonent.setAbonentSurname(resultSet.getString(6));
            }
            return telNumList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
