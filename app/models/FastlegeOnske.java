package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class FastlegeOnske extends Model {
    @ManyToOne
    public Fastlege fastlege;
}
