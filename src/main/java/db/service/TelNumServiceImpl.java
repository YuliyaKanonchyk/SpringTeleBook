package db.service;


import db.entity.TelNum;
import db.repository.TelNumStorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelNumServiceImpl implements TelNumService {
    private TelNumStorageRepository<TelNum> telNumStorageRepository;

    public TelNumServiceImpl(TelNumStorageRepository<TelNum> telNumStorageRepository) {
        this.telNumStorageRepository = telNumStorageRepository;
    }

    @Override
    public void addTelNum(TelNum telNum) {
        telNumStorageRepository.addTelNum(telNum);
    }

    @Override
    public boolean deleteTelNum(int id) {
        for (TelNum t : showAllTelNums()) {
            if (t.getId() == id) {
                telNumStorageRepository.deleteTelNum(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateTelNum(String startTelNum, String finalTelNum) {
        for (TelNum t : telNumStorageRepository.getAllTelNums()) {
            if (t.getTelNumber().equalsIgnoreCase(startTelNum))
                return telNumStorageRepository.updateTelNum(t.getId(),finalTelNum);
        }
        return false;
    }

    @Override
    public List<TelNum> showAllTelNums() {
        return telNumStorageRepository.getAllTelNums();
    }
}
