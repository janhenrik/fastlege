package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

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

	public boolean gyldig = true;
	public Date bleUgyldigDato;

	public boolean erSammeLege(Fastlege annenLege) {
		if (this == annenLege) {
			return true;
		}
		if (adresse == null) {
			if (annenLege.adresse != null) {
				return false;
			}
		} else if (!adresse.equals(annenLege.adresse)) {
			return false;
		}
		if (kjonn != annenLege.kjonn) {
			return false;
		}
		if (legekontor == null) {
			if (annenLege.legekontor != null) {
				return false;
			}
		} else if (!legekontor.equals(annenLege.legekontor)) {
			return false;
		}
		if (navn == null) {
			if (annenLege.navn != null) {
				return false;
			}
		} else if (!navn.equals(annenLege.navn)) {
			return false;
		}
		if (postnummerOgSted == null) {
			if (annenLege.postnummerOgSted != null) {
				return false;
			}
		} else if (!postnummerOgSted.equals(annenLege.postnummerOgSted)) {
			return false;
		}
		return true;
	}

}
