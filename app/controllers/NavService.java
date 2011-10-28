package controllers;

import java.util.List;

import models.Fastlege;
import no.knowit.fastlege.service.FastlegeLeser;

public class NavService {

	private FastlegeLeser fastlegeLeser = new FastlegeLeser();

	public List<Fastlege> hentAlleFastleger() {
		return fastlegeLeser.hentAlleFastleger();
	}

}
