package selenium.pageobjects.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import selenium.pageobjects.example.pages.*;

public class WordPressTests extends BaseTest {

    @Test
    public void canAddCommentToFirstNote() {
        String comment = generateRandomText();
        String author = generateRandomText();
        String email = generateRandomEmail();

        WpMainPage mainPage = WpMainPage.open(driver);
        WpNotePage latestNote = mainPage.openLatestNote();
        WpNotePage latestNoteWithComment = latestNote.addComment(comment, author, email);

        Assertions.assertTrue(latestNoteWithComment.commentExists(comment, author));
    }

    @Test
    public void canPublishNewNote() {
        String user = WpPage.adminUser();
        String password = WpPage.adminPassword();
        String noteTitle = generateRandomText();
        String noteContent = generateRandomText();

        WpLoginPage loginPage = WpLoginPage.open(driver);
        WpAdminPage adminPage = loginPage.login(user, password);
        WpAdminPage newNoteEditor = adminPage.openNewNoteEditor();
        newNoteEditor.createNote(noteTitle, noteContent);
        String noteUrl = newNoteEditor.publishNote();

        WpLoginPage loggedOutPage = adminPage.logout();
        Assertions.assertTrue(loggedOutPage.isUserLoggedOut());

        WpNotePage notePage = WpNotePage.open(noteUrl, driver);
        Assertions.assertEquals(noteTitle, notePage.getTitle());
        Assertions.assertEquals(noteContent, notePage.getContent());
    }

}
