package db.service;


import db.entity.TelOperator;

import java.util.List;

public interface TelOperatorService {
    void addTelOperator(TelOperator telOperator);

    void deleteTelOperator(TelOperator telOperator);

    boolean updateTelOperator(String startOperatorNAme, String endOperatorNAme);

    TelOperator getTelOperatorByName(String name);

    List<TelOperator> showAllTelOperators();
}
