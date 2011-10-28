package controllers;

import java.util.List;

import models.Fastlege;
import models.Fylke;
import no.knowit.fastlege.service.FastlegeLeser;
import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
    	FastlegeLeser fastlegeLeser = new FastlegeLeser();
        List <Fastlege> fastleger = fastlegeLeser.hentAlleFastleger();
        render(fastleger);
    }



}