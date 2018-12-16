package selenium.pageobjects.concept;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import selenium.pageobjects.concept.pages.GoogleMainPage;
import selenium.pageobjects.concept.pages.GoogleResultPage;

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

    /*
        This is how parameterized test is created using CSV format of test data.
        See pom.xml file (Maven configuration) to see how to add project
        dependency 'junit-jupiter-params' and read more at:
        https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests
    */
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
