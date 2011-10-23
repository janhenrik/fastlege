package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Person extends Model {

    public String navn;
    public String epost;
    @OneToMany
    public List <FastlegeOnske> fastlegeOnsker;

}
