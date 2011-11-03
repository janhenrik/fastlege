package no.knowit.fastlege.service;

import java.util.Date;
import java.util.List;

import models.Fastlege;
import models.Fylke;
import play.jobs.Job;
import play.jobs.On;
import play.jobs.OnApplicationStart;

@OnApplicationStart
@On("0 * * * * ?")
public class FastlegeImportererJobb extends Job {

	public void doJob() {
		System.out.println("Henter fastleger på nytt");

		List<Fastlege> nyeFastleger = hentNyeFastleger();
		System.out.println("Antall nye leger: " + nyeFastleger.size());

		if (!nyeFastleger.isEmpty()) {
			List<Fastlege> eksisterendeFastleger = hentEksisterendeFastleger();
			System.out.println("Antall eksisterende leger: "
					+ eksisterendeFastleger.size());

			ugyldiggjorLegerSomErBorte(eksisterendeFastleger, nyeFastleger);
			leggTilNyeLeger(eksisterendeFastleger, nyeFastleger);
		}
	}

	private List<Fastlege> hentEksisterendeFastleger() {
		return Fastlege.findAll();
	}

	private List<Fastlege> hentNyeFastleger() {
		FastlegeLeser fastlegeLeser = new FastlegeLeser();
		return fastlegeLeser.hentFastleger(Fylke.OSLO);
	}

	private void leggTilNyeLeger(List<Fastlege> eksisterendeLeger,
			List<Fastlege> nyeLeger) {
		for (Fastlege nyLege : nyeLeger) {
			boolean nyLegeFinnesFraFor = false;
			int teller = 0;
			for (Fastlege eksisterendeLege : eksisterendeLeger) {
				teller++;
				if (nyLege.erSammeLege(eksisterendeLege)) {
					nyLegeFinnesFraFor = true;
					break;
				}
			}
			if (!nyLegeFinnesFraFor) {
				nyLege.save();
			}
		}
	}

	private void ugyldiggjorLegerSomErBorte(List<Fastlege> eksisterendeLeger,
			List<Fastlege> nyeLeger) {
		for (Fastlege eksisterendeLege : eksisterendeLeger) {
			boolean eksisterendeLegeFunnet = false;
			for (Fastlege nyLege : nyeLeger) {
				if (eksisterendeLege.erSammeLege(nyLege)) {
					eksisterendeLegeFunnet = true;
					break;
				}
			}
			if (!eksisterendeLegeFunnet) {
				eksisterendeLege.gyldig = false;
				eksisterendeLege.bleUgyldigDato = new Date();
				// eksisterendeLege.save();
				// Dette er ikke nødvendig i vanlig Hibernate..
			} else {
				// Innebærer at en ugyldig lege kan bli gyldig igjen.. Vet ikke
				// om det er ønskelig..
				eksisterendeLege.gyldig = true;
				eksisterendeLege.bleUgyldigDato = null;
				// eksisterendeLege.save();
				// Dette er ikke nødvendig i vanlig Hibernate..
			}
		}
	}
}