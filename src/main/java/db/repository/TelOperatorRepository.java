package db.repository;


import db.entity.TelOperator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class TelOperatorRepository implements TelOperatorStorageRepository<TelOperator> {

    private final String CREATE_TEL_OPERATOR = "create table tel_operator(id int auto_increment, tel_operator_name varchar(15) not null, constraint id_UNIQUE unique (id))";
    private final String DELETE_TEL_OPERATOR = "DELETE FROM tel_operator WHERE id = ?";
    private final String INSERT_TEL_OPERATOR = "INSERT INTO tel_operator (tel_operator_name) VALUES (?)";
    private final String UPDATE_TEL_OPERATOR = "UPDATE tel_operator SET tel_operator_name = ? WHERE tel_operator.id = ?";
    private final String FIND_TEL_OPERATOR_BY_ID = "SELECT * FROM tel_operator t WHERE t.id = ?";
    private final String FIND_ALL_TEL_OPERATORS = "SELECT * FROM tel_operator";

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
            PreparedStatement  ps = connection.prepareStatement(CREATE_TEL_OPERATOR);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addTelOperator(TelOperator telOperator) {
        try {
            PreparedStatement ps = connection.prepareStatement(INSERT_TEL_OPERATOR);
            ps.setString(1, telOperator.getName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteTelOperator(int id) {
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
    public boolean updateTelOperator(int id, String finalOperatorName) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(UPDATE_TEL_OPERATOR);
            ps.setInt(2, id);
            ps.setString(1, finalOperatorName);
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<TelOperator> getAllTelOperators() {
        List<TelOperator> telOperatorList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL_TEL_OPERATORS);
            ps.execute();
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                TelOperator telOperator = new TelOperator();
                telOperator.setId(resultSet.getInt("id"));
                telOperator.setName(resultSet.getString("2"));
                telOperatorList.add(telOperator);
            }
            return telOperatorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
