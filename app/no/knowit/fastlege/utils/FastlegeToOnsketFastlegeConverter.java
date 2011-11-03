package no.knowit.fastlege.utils;

import models.Fastlege;
import models.Kjonn;
import models.OnsketFastlege;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ch.lambdaj.function.convert.Converter;

public class FastlegeToOnsketFastlegeConverter implements
		Converter<Fastlege, OnsketFastlege> {

	@Override
	public OnsketFastlege convert(Fastlege fastlege) {
		return new OnsketFastlege(fastlege);
	}

}
