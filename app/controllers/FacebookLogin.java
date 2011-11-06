package controllers;

import play.mvc.Before;
import play.mvc.Controller;

public class FacebookLogin extends Controller {

	@Before
    public static void sjekkAutentisering() {
		System.out.println("Kommer jeg hit?");
		if(!session.contains("user")) {
			System.out.println("Ingen bruker!");
            renderTemplate("Application/login.html");
        }
		System.out.println("Bruker: " + session.get("user"));
    }
}
