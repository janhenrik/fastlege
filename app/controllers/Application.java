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
		List<Fastlege> fastleger = Fastlege.find("gyldig = true").fetch();
		System.out.println("Har hentet opp " + fastleger.size()
				+ " leger fra db");
		renderArgs.put("fastleger", fastleger);
		
		String epost = Scope.Session.current().get("user");
		Person bruker = hentEllerLagBruker(epost);
		bruker.navn = "Hågen P";
		renderArgs.put("bruker", bruker);
		
		render();
	}

	private static Person hentEllerLagBruker(String epost) {
		Person person = Person.find("byEpost", epost).first();
		if (person == null) {
			person = new Person();
			person.epost = epost;
			person.save();
		}
		return person;
	}

	public static void velgLeger(List<Long> fastlegeId) {
		String epost = Scope.Session.current().get("user");
		Person bruker = hentEllerLagBruker(epost);
		
		for (Long id : fastlegeId) {
			System.out.println("Mottok: " + id);
			//TODO: Dette er kanskje litt anemisk... Skriv om til DDD.
			Fastlege fastlege = Fastlege.<Fastlege>findById(id);
			FastlegeOnske onske = new FastlegeOnske();
			onske.fastlege = fastlege;
			onske.save();
			bruker.fastlegeOnsker.add(onske);
			bruker.save();
			
			index();
		}
	}

	public static void velgLege(Long id, String navn) {

	}

}