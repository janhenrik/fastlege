package no.knowit.fastlege.service;

import static ch.lambdaj.Lambda.convert;
import static ch.lambdaj.Lambda.forEach;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import models.Fastlege;
import models.Fylke;
import models.Kjonn;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import play.libs.WS;

import ch.lambdaj.function.convert.Converter;

public class FastlegeLeser {

	private static final String POSTURL = "https://tjenester.nav.no/minfastlege/innbygger/fastlegesokikkepalogget.do";
	private static final String GETURL = "https://tjenester.nav.no/minfastlege/innbygger/fastlegesokikkepalogget.do?sok=0";

	public static void main(String[] args) {
		FastlegeLeser leser = new FastlegeLeser();
		List<Fastlege> fastleger = leser.hentFastleger(Fylke.OSLO);
		forEach(fastleger).toString();
	}

	public List<Fastlege> hentFastleger(Fylke fylke) {
		List<Fastlege> fastleger = new ArrayList<Fastlege>();
		try {

			String html = hentLegeHtmlsidenForFylke(fylke);
			fastleger = parseHtml(html);

			return fastleger;

		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Fastlege> hentAlleFastleger() {
		String html = hentLegeHtmlsidenForHeleLandet();
		List<Fastlege> fastleger = parseHtml(html);

		return fastleger;
	}

	List<Fastlege> parseHtml(String html) {
		Document document = Jsoup.parse(html, POSTURL);
		Elements table = document.select("table#fastlege");
		Elements tbody = table.select("tbody");
		Elements trs = tbody.select("tr");
		return convert(trs, new ElementToFastlegeConverter());
	}

	private String hentLegeHtmlsidenForHeleLandet() {
		WS.HttpResponse res = WS.url(GETURL).get();
		return res.getString("UTF-8");
	}

	private String hentLegeHtmlsidenForFylke(Fylke fylke)
			throws UnsupportedEncodingException, IOException,
			ClientProtocolException {
		HttpClient httpClient = new DefaultHttpClient();

		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("fylke", fylke.getNummer()));
		formparams.add(new BasicNameValuePair("kommune", ""));
		formparams.add(new BasicNameValuePair("sok", "Søk etter fastlege"));

		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams,
				"UTF-8");

		HttpPost httpPost = new HttpPost(POSTURL);
		httpPost.setEntity(entity);

		ResponseHandler<String> handler = new ResponseHandler<String>() {
			public String handleResponse(HttpResponse response)
					throws ClientProtocolException, IOException {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					return EntityUtils.toString(entity, "UTF-8");
				} else {
					return null;
				}
			}
		};

		String html = httpClient.execute(httpPost, handler);
		return html;
	}

	class ElementToFastlegeConverter implements Converter<Element, Fastlege> {

		public Fastlege convert(Element tr) {
			Fastlege fastlege = new Fastlege();

			Elements tds = tr.select("td");
			fastlege.navn = tds.get(0).text();
			fastlege.kjonn = (tds.get(1).text().equals("M") ? Kjonn.MANN
					: Kjonn.KVINNE);
			fastlege.legekontor = tds.get(2).text();
			fastlege.tilgjengelig = (tds.get(3).text().equals("Ja") ? true
					: false);
			fastlege.adresse = tds.get(4).text();
			fastlege.postnummerOgSted = tds.get(5).text();
			fastlege.gruppepraksis = (tds.get(6).text().equals("Ja") ? true
					: false);
			fastlege.fellesliste = (tds.get(7).text().equals("Ja") ? true
					: false);
			fastlege.antallPaaListen = Integer.parseInt(tds.get(8).text());
			fastlege.antallLedig = Integer.parseInt(tds.get(9).text());
			return fastlege;
		}
	}

}
