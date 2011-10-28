package no.knowit.fastlege.service;
import controllers.NavService;
import models.Fastlege;
import no.knowit.fastlege.service.FastlegeLeser;

import org.junit.Before;
import org.junit.Test;
import play.mvc.Http.Response;
import play.test.FunctionalTest;
import play.test.UnitTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FastlegeLeserTest extends UnitTest {

    private FastlegeLeser fastlegeLeser;

    @Before
    public void setUp() {
        fastlegeLeser = new FastlegeLeser();

    }

    @Test
    public void testNavService() throws IOException {
        List<Fastlege> fastleger = fastlegeLeser.parseHtml(readFile("test/nav.html"));
        assertNotNull(fastleger);
    }

    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        return stringBuilder.toString();
    }

}