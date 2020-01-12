package db.service;

import db.entity.TelOperator;
import db.repository.TelOperatorStorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelOperatorServiceImpl implements TelOperatorService {

    private TelOperatorStorageRepository<TelOperator> telOperatorStorageRepository;

    public TelOperatorServiceImpl(TelOperatorStorageRepository<TelOperator> telOperatorStorageRepository) {
        this.telOperatorStorageRepository = telOperatorStorageRepository;
    }

    @Override
    public void addTelOperator(TelOperator telOperator) {
        telOperatorStorageRepository.addTelOperator(telOperator);
    }

    @Override
    public void deleteTelOperator(TelOperator telOperator) {
        for (TelOperator t : showAllTelOperators()) {
            if (t.getName().equals(telOperator.getName())) {
                telOperatorStorageRepository.deleteTelOperator(telOperator.getId());
            }
        }

    }

    @Override
    public boolean updateTelOperator(String startOperatorNAme, String finalOperatorName) {
        for (TelOperator allTelOperator : telOperatorStorageRepository.getAllTelOperators()) {
            if (allTelOperator.getName().equals(startOperatorNAme)) {
                return telOperatorStorageRepository.updateTelOperator(allTelOperator.getId(), finalOperatorName);
            }
        }
        // FIXME: 11.01.2020 Возвращает false в любом случае?
        return false;
    }


    @Override
    public TelOperator getTelOperatorByName(String name) {
        for (TelOperator allTelOperator : telOperatorStorageRepository.getAllTelOperators()) {
            if (allTelOperator.getName().equals(name)) {
                return allTelOperator;
            }
        }
        // FIXME: 11.01.2020 Как не возвращать null?
        return null;
    }

    @Override
    public List<TelOperator> showAllTelOperators() {
        return telOperatorStorageRepository.getAllTelOperators();
    }
}
