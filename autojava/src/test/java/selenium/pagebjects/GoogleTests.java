package selenium.pagebjects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import selenium.pagebjects.pages.GoogleMainPage;
import selenium.pagebjects.pages.GoogleResultPage;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class GoogleTests extends BaseTest {

    @Test
    public void canFindScrumOrgOnGoogle() {
        String scrumOrgUrl = "https://www.scrum.org/";
        String scrumOrgPageTitle = "Scrum.org: Homepage";

        GoogleMainPage googlePage = new GoogleMainPage(driver);
        GoogleResultPage resultPage = googlePage.search("Scrum.org");

        assertTrue(resultPage.containsResults(scrumOrgUrl));
        assertTrue(resultPage.containsResultsWithTitle(scrumOrgUrl, scrumOrgPageTitle),
                "Scrum.org page has correct title");
    }

    @Test
    public void canFindCodeSprinters() {
        String csUrl = "http://agileszkolenia.pl/";
        String csPageTitle = "Code Sprinters - Agile Experts -";


        GoogleMainPage googlePage = new GoogleMainPage(driver);
        GoogleResultPage resultPage = googlePage.search("Code Sprinters");

        assertTrue(resultPage.containsResults(csUrl));
        assertTrue(resultPage.containsResultsWithTitle(csUrl, csPageTitle));

    }

    @ParameterizedTest
    @CsvSource({
            "Scrum.org, https://www.scrum.org/, Scrum.org: Homepage",
            "Code Sprinters, http://agileszkolenia.pl/, Code Sprinters - Agile Experts -",
            "Rafał Markowicz, http://markowicz.pro/, Rafał Markowicz – Kolejny piękny dzień"
    })
    public void canFindPagesOnGoogle(String query, String url, String title) {
        GoogleMainPage googlePage = new GoogleMainPage(driver);
        GoogleResultPage resultPage = googlePage.search(query);

        assertTrue(resultPage.containsResults(url));
        assertTrue(resultPage.containsResultsWithTitle(url, title));
    }

}
