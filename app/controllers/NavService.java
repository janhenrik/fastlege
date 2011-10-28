package controllers;

import models.Fastlege;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import play.libs.WS;

import java.util.List;

public class NavService {

    private List<Fastlege> fastleger;
    public final String NAVUrl = "https://tjenester.nav.no/minfastlege/innbygger/fastlegesokikkepalogget.do?sok=0";

    public List<Fastlege> hentAlleFastleger() {
        WS.HttpResponse res = WS.url(NAVUrl).get();
        return parseHtml(res.getString("UTF-8"));
    }

    public List<Fastlege> parseHtml(String html) {
        Document doc = Jsoup.parse(html);
        Elements pngs = doc.getElementsByAttribute("tr class=odd"); // TODO this is not right
        for (int i = 0; i < pngs.size(); i++) {
            System.out.println(pngs.get(i).data());
        }
        return null;
    }

}
