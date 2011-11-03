package models;

import javax.persistence.Entity;

@Entity
public class OnsketFastlege extends Fastlege {
	
	public OnsketFastlege(Fastlege lege) {
		this.navn =  lege.navn;
		this.kjonn =  lege.kjonn;
		this.legekontor =  lege.legekontor;
		this.tilgjengelig = lege.tilgjengelig;
		this.adresse = lege.adresse;
		this.postnummerOgSted = lege.postnummerOgSted;
		this.gruppepraksis = lege.gruppepraksis;
		this.fellesliste = lege.fellesliste;
		this.antallPaaListen = lege.antallPaaListen;
		this.antallLedig = lege.antallLedig;
	}
}
