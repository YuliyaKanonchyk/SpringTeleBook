package db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeleBook implements Serializable {
    private static final long serialVersionUID = -7522833786531308309L;
    private int id;
    private City city;
    private ArrayList<Abonent> abonentList;

    public TeleBook(City city) {
        this.city = city;
    }
}
