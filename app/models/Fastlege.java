package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Fastlege extends Model {
	public String navn;
	public Kjonn kjonn;
	public String legekontor;
	public boolean tilgjengelig;
	public String adresse;
	public String postnummerOgSted;
	public boolean gruppepraksis;
	public boolean fellesliste;
	public int antallPaaListen;
	public int antallLedig;
}
