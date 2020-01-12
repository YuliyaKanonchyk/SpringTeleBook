package db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City implements Cloneable, Serializable {

    private static final long serialVersionUID = 7476551008466206804L;
    private int id;
    private String name;
    private String country;
    private TeleBook teleBook;

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public City(String name) {
        this.name = name;
    }
}
