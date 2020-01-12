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
public class TelNum implements Serializable {
    private static final long serialVersionUID = -3999915962838494263L;
    private int id;
    private String telNumber;
    private TelOperator telOperator;

    public TelNum(String telNumber, TelOperator telOperator) {
        this.telNumber = telNumber;
        this.telOperator = telOperator;
    }

    public TelNum(String telNumber) {
        this.telNumber = telNumber;
    }
}
