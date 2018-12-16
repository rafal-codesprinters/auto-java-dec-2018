package selenium.wordpresspageobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import selenium.wordpresspageobjects.pages.WpAdminPage;
import selenium.wordpresspageobjects.pages.WpLoginPage;
import selenium.wordpresspageobjects.pages.WpMainPage;
import selenium.wordpresspageobjects.pages.WpNotePage;

import java.util.UUID;

public class WordPressTests extends BaseTest {

    @Test
    public void canAddCommentToFirstNote() {
        // open wordpress main page
        // open first note
        // add comment to the note
        // check that comment is added
        String comment = generateRandomText();
        String author = generateRandomText();
        String email = generateRandomEmail();

        WpMainPage mainPage = new WpMainPage(driver);
        WpNotePage latestNote = mainPage.openLatestNote();
        WpNotePage latestNoteWithComment = latestNote.addComment(comment, author, email);

        Assertions.assertTrue(latestNoteWithComment.commentExists(comment, author));
    }

    @Test
    public void canPublishNewNote() {
        String user = "automatyzacja";
        String password = "jesien2018";
        String title = generateRandomText();
        String content = generateRandomText();

        WpLoginPage loginPage = new WpLoginPage(driver);
        WpAdminPage adminPage = loginPage.login(user, password);

        String noteUrl = adminPage.createNote(title, content);
        WpLoginPage loggedOutPage = adminPage.logout();

        WpNotePage notePage = new WpNotePage(driver, noteUrl);
        Assertions.assertEquals(title, notePage.getTitle());
        Assertions.assertEquals(content, notePage.getContent());

    }

}
