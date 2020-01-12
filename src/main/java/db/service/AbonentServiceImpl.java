package db.service;

import db.entity.Abonent;
import db.entity.TelNum;
import db.repository.AbonentStorageRepository;
import db.repository.TelNumStorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbonentServiceImpl implements AbonentService {
    private final TelNumStorageRepository<TelNum> telNumRepository;
    private final AbonentStorageRepository<Abonent> abonentRepository;

    public AbonentServiceImpl(AbonentStorageRepository<Abonent> abonentRepository, TelNumStorageRepository<TelNum> telNumRepository) {
        this.abonentRepository = abonentRepository;
        this.telNumRepository = telNumRepository;
    }

    @Override
    public void addAbonent(Abonent a) {
        abonentRepository.addAbonent(a);
        telNumRepository.addTelNum(a.getTelNum());
    }

    @Override
    public void deleteAbonent(Abonent abonent) {
        for (Abonent a : abonentRepository.getAllAbonents()) {
            if (a.getAbonentName().equals(abonent.getAbonentName())) {
                abonentRepository.deleteAbonent(a.getId());
            }
        }
    }

    @Override
    public Abonent showAbonentById (int id) {
        return abonentRepository.findAbonentByID(id);
    }

    @Override
    public boolean updateAbonent(String startSurName, String secondSurName) {
        for (Abonent a : abonentRepository.getAllAbonents()) {
            if (a.getAbonentSurname().equalsIgnoreCase(startSurName)) {
                a.setAbonentSurname(secondSurName);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Abonent> showAllAbonents() {
        return abonentRepository.getAllAbonents();
    }

    @Override
    public void showAbonentBySurName(String readAbonNameDelete) {

    }

}
