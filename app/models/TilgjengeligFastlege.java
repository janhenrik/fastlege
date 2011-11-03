package models;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class TilgjengeligFastlege extends Fastlege {
	
	public TilgjengeligFastlege( Fastlege lege ) {
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
