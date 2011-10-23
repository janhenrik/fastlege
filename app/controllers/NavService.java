package controllers;

import models.Fastlege;
import play.libs.WS;

import java.util.List;

public class NavService {

    private List<Fastlege> fastleger;
    public final String NAVUrl = "https://tjenester.nav.no/minfastlege/innbygger/fastlegesokikkepalogget.do?sok=0";

    public List<Fastlege> hentAlleFastleger() {

        WS.HttpResponse res = WS.url(NAVUrl).get();
        //Document xml = res.getXml();
        return null;
    }

}
