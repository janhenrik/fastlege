package controllers;

import models.Person;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope;

public class FacebookLogin extends Controller {

	@Before
	public static void login() {
		System.out.println("Sjekker autentisering");
		if (!session.contains("user")) {
			System.out.println("Ingen bruker!");
			renderTemplate("Application/login.html");
		} else {
			String epost = Scope.Session.current().get("user");
			Person bruker = Person.find("byEpost", epost).first();
			if (bruker == null) {
				System.out.println("Bruker i session, men ikke i db");
				// Dette er bare nødvendig fordi vi bruker inmemory database..
				session.remove("user");
				renderTemplate("Application/login.html");
			}
			System.out.println("Alt ok med autentisering");
		}
	}
}
