package HomeWorkTests;

import com.trello.UI.Pages.LoginPage;
import com.trello.UI.core.BrowserFactory;
import org.testng.annotations.Test;

public class PrivatePublicBoardTest extends BrowserFactory {

    @Test
    public void privateBoardTest() {
        new LoginPage().autoLogin()
                .createNewBoard()
                .submitBoardCreate("Private Group")
                .checkThatBoardIsPrivate();
    }

    @Test (dependsOnMethods = "privateBoardTest")
    public void publicBoardTest() {
        new LoginPage().autoLogin()
                .openBoard(1)
                .checkThatBoardIsPublic();
    }
}
