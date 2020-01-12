package db.repository;


import java.util.List;

public interface TelNumStorageRepository<TelNum> {

    boolean updateTelNum(int id, String paramName);

    boolean addTelNum(TelNum telNum);

    boolean deleteTelNum(int id);

    List<TelNum> getAllTelNums();
}
