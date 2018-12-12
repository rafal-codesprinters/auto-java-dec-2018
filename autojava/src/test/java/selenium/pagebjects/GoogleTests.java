package selenium.pagebjects;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import selenium.pagebjects.pages.GoogleMainPage;
import selenium.pagebjects.pages.GoogleResultPage;

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

}
