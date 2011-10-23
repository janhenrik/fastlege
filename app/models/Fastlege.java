package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Fastlege extends Model {
    public String navn;
    public Kjonn kjonn;
    public String legekontor;
    public String adresse;
    public String postnr;
    public String sted;
}
