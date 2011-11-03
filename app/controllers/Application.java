package controllers;

import java.util.List;

import models.Fastlege;
import play.mvc.Controller;

public class Application extends Controller {

	public static void index() {
		List<Fastlege> fastleger = Fastlege.find("gyldig = true").fetch();
		System.out.println("Har hentet opp " + fastleger.size()
				+ " leger fra db");
		render(fastleger);
	}

	public static void velgLege(Long id, String navn) {

	}

}