package selenium.pagebjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import selenium.pagebjects.pages.GoogleMainPage;
import selenium.pagebjects.pages.GoogleResultPage;

public class GoogleTests extends BaseTest {

    @Test
    public void canFindScrumOrgOnGoogle() {
        String pageUrl = "https://www.scrum.org/";
        String pageTitle = "Scrum.org: Homepage";

        // Open Google Main Page
        GoogleMainPage googlePage = new GoogleMainPage(driver);

        // Search For Scrum.org
        GoogleResultPage resultPage = googlePage.search("Scrum.org");

        // Assert Scrum.org page is found
        Assertions.assertTrue(resultPage.contains(pageUrl));
        Assertions.assertTrue(resultPage.containsResultWithTitle(pageUrl, pageTitle),
                "Scrum.org page has correct title");
    }

    @Test
    public void canFindCodeSprinters(){
        String pageUrl = "http://agileszkolenia.pl/";
        String pageTitle = "Code Sprinters - Agile Experts -";

        GoogleMainPage googlePage = new GoogleMainPage(driver);
        GoogleResultPage resultPage = googlePage.search("Code Sprinters");
        Assertions.assertTrue(resultPage.contains(pageUrl));
        Assertions.assertTrue(resultPage.containsResultWithTitle(pageUrl, pageTitle));
    }

}
