package db.repository;


import java.util.List;

public interface TelOperatorStorageRepository<TelOperator> {

    boolean updateTelOperator(int id, String paramName);

    boolean addTelOperator(TelOperator telOperator);

    boolean deleteTelOperator(int id);

    List<TelOperator> getAllTelOperators();
}
