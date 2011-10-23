package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Person {

    @Id
    public long id;
    public String navn;
    public String epost;
    @OneToMany
    public List <FastlegeOnske> fastlegeOnsker;

}
