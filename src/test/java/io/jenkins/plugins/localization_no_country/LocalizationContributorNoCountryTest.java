package io.jenkins.plugins.localization_no_country;

import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

import java.net.URL;
import java.util.Locale;

import static org.junit.Assert.assertTrue;

public class LocalizationContributorNoCountryTest {
    @Rule
    public final JenkinsRule j = new JenkinsRule();

    private final String ACCEPT_LANGUAGE_HEADER = "Accept-Language";
    private final String ACCEPT_LANGUAGE_HEADER_VALUE_IT = "it";

    @Test
    public void testGetResource() throws Exception {
        JenkinsRule.WebClient wc = j.createWebClient();
        WebRequest req = new WebRequest(new URL(j.getURL() + "about/"));

        req.setAdditionalHeader(ACCEPT_LANGUAGE_HEADER,
            ACCEPT_LANGUAGE_HEADER_VALUE_IT);
        HtmlPage p = wc.getPage(req);
        // This fails because apparently the title text is still shown in
        // English despite the Accept-Language header being set correctly!
        assertTrue("the title text is localized to Italian",
            p.getTitleText().startsWith("Informazioni su Jenkins"));
    }
}
