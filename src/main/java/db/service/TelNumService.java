package db.service;

import db.entity.TelNum;

import java.util.List;

public interface TelNumService {

    void addTelNum(TelNum telNum);

    boolean deleteTelNum(int id);

    boolean updateTelNum(String startTelNum, String finalTelNum);

    List<TelNum> showAllTelNums();
}
