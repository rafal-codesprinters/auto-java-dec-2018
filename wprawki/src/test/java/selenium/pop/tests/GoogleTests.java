package selenium.pop.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import selenium.pop.data.ScrumOrg;
import selenium.pop.pages.GoogleMainPage;
import selenium.pop.pages.GoogleResultsPage;

public class GoogleTests extends BaseTest {

	@Test
	public void canFindScrumOrg() {

		GoogleMainPage mainPage = new GoogleMainPage(driver);

		GoogleResultsPage resultsPage = mainPage.search(ScrumOrg.NAME);

		assertTrue(resultsPage.oneResultFound(ScrumOrg.URL));
		assertTrue(resultsPage.validUrlDisplayed(ScrumOrg.URL, ScrumOrg.DISPLAYED_URL));

	}

}
