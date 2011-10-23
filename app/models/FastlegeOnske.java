package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FastlegeOnske {
    @Id
    public long id;
    @ManyToOne
    public Fastlege fastlege;
}
