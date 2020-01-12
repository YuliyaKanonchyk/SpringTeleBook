package db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Abonent implements Serializable {

    private static final long serialVersionUID = -5049045426434969592L;
    private int id;
    private String abonentName;
    private String abonentSurname;
    private City city;
    private TelNum telNum;
    private TeleBook teleBook;
    private boolean isEmpty;

    public Abonent(String abonentName, TelNum telNum) {
        this.abonentName = abonentName;
        this.telNum = telNum;
    }

    public Abonent(String abonentName, String abonentSurname, TelNum telNum, City city) {
        this.abonentName = abonentName;
        this.abonentSurname = abonentSurname;
        this.telNum = telNum;
        this.city = city;
    }
}
