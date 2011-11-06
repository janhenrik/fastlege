package controllers;

import java.util.List;

import models.Fastlege;
import models.FastlegeOnske;
import models.Person;
import play.db.jpa.GenericModel.JPAQuery;
import play.db.jpa.JPABase;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope;
import play.mvc.With;

@With(FacebookLogin.class)
public class Application extends Controller {

	public static void index() {
		Person bruker = hentBruker();

		List<Fastlege> fastleger = Fastlege.find("gyldig = true").fetch();
		System.out.println("Har hentet opp " + fastleger.size()
				+ " leger fra db");
		renderArgs.put("fastleger", fastleger);

		renderArgs.put("bruker", bruker);

		render();
	}

	public static void velgLeger(List<Long> fastlegeId) {
		Person bruker = hentBruker();

		for (Long id : fastlegeId) {
			System.out.println("Mottok: " + id);
			// TODO: Dette er kanskje litt anemisk...? Skriv om til DDD.
			Fastlege fastlege = Fastlege.<Fastlege> findById(id);
			FastlegeOnske onske = new FastlegeOnske();
			onske.fastlege = fastlege;
			onske.save();
			bruker.fastlegeOnsker.add(onske);
			bruker.save();

			index();
		}
	}

	private static Person hentBruker() {
		String epost = Scope.Session.current().get("user");
		Person bruker = Person.find("byEpost", epost).first();
		return bruker;
	}

	public static void velgLege(Long id, String navn) {

	}

}