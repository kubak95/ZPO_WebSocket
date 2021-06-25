package pl.kolaczynskijakub.websocketclient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class DataEntity {
    @Id
    @GeneratedValue
    public int id;
    public String name;
    public String surname;
    public String email;
}
