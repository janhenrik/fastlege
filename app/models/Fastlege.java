package models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fastlege {
    @Id
    public long id;
    public String navn;
    public Kjonn kjonn;
    public String legekontor;
    public String adresse;
    public String postnr;
    public String sted;
}
