package HomeWorkTests;

import com.trello.UI.Pages.LoginPage;
import com.trello.UI.core.BrowserFactory;
import org.testng.annotations.Test;

public class PrivatePublicBoardTest extends BrowserFactory {

    @Test
    public void privateBoardTest() {
        new LoginPage().open().login()
                .createNewBoard()
                .submitBoardCreate("Privat Group")
                .checkThatBoardIsPrivate();
    }

    @Test (dependsOnMethods = "privateBoardTest")
    public void publicBoardTest() {
        new LoginPage().open().login()
                .openBoard(1)
                .checkThatBoardIsPublic();
    }
}
