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
public class TelOperator implements Serializable {
    private static final long serialVersionUID = -6569006936815626388L;
    private int id;
    private String name;

    public TelOperator(String name) {
        this.name = name;
    }
}
