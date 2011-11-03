package no.knowit.fastlege.utils;

import models.Fastlege;
import models.Kjonn;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ch.lambdaj.function.convert.Converter;

public class ElementToFastlegeConverter implements Converter<Element, Fastlege> {

	public Fastlege convert(Element tr) {
		Fastlege fastlege = new Fastlege();

		Elements tds = tr.select("td");
		fastlege.navn = tds.get(0).text();
		fastlege.kjonn = (tds.get(1).text().equals("M") ? Kjonn.MANN
				: Kjonn.KVINNE);
		fastlege.legekontor = tds.get(2).text();
		fastlege.tilgjengelig = (tds.get(3).text().equals("Ja") ? true : false);
		fastlege.adresse = tds.get(4).text();
		fastlege.postnummerOgSted = tds.get(5).text();
		fastlege.gruppepraksis = (tds.get(6).text().equals("Ja") ? true : false);
		fastlege.fellesliste = (tds.get(7).text().equals("Ja") ? true : false);
		fastlege.antallPaaListen = Integer.parseInt(tds.get(8).text());
		fastlege.antallLedig = Integer.parseInt(tds.get(9).text());
		return fastlege;
	}
}
