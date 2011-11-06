package no.knowit.fastlege.service;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.select;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import models.Fastlege;
import models.FastlegeOnske;
import models.Person;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import play.jobs.Job;
import play.jobs.On;
import play.libs.Mail;

@On("0 1/2 * * * ?")
public class SjekkLedigeFastlegerJobb extends Job {

	public void doJob() {
		System.out.println("Sjekker om leger er ledige..");
		List<Person> alleBrukere = Person.<Person> findAll();
		List<Fastlege> alleFastleger = Fastlege.<Fastlege> findAll();

		for (Fastlege fastlege : alleFastleger) {
			if (fastlege.antallLedig == 0) {
				continue;
			}
			for (Person bruker : alleBrukere) {
				List<FastlegeOnske> ledigeOnsker = select(
						bruker.fastlegeOnsker,
						having(on(FastlegeOnske.class).fastlege.id,
								equalTo(fastlege.id)));
				for (FastlegeOnske fastlegeOnske : ledigeOnsker) {
					varsleBruker(bruker, fastlegeOnske.fastlege);
				}
			}
		}
	}

	private void varsleBruker(Person bruker, Fastlege fastlege) {
		System.out.println(bruker.epost + " varsles om " + fastlege.navn);
		try {
			SimpleEmail email = new SimpleEmail();
			email.setFrom("tullebukk@mailinator.com");
			email.addTo(bruker.epost);
			email.setSubject("Din ønskelege er ledig!");
			email.setMsg(fastlege.navn + " er ledig - løp og kjøp!!");
			Mail.send(email);
		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

}
