package no.knowit.fastlege.utils;

import models.Fastlege;
import models.Kjonn;
import models.OnsketFastlege;
import models.TilgjengeligFastlege;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ch.lambdaj.function.convert.Converter;

public class FastlegeToTilgjengeligFastlegeConverter implements
		Converter<Fastlege, TilgjengeligFastlege> {

	@Override
	public TilgjengeligFastlege convert(Fastlege fastlege) {
		return new TilgjengeligFastlege(fastlege);
	}

}
