package HomeWorkTests;

import com.trello.UI.Pages.BoardsListPage;
import com.trello.UI.Pages.LoginPage;
import com.trello.UI.core.BrowserFactory;
import org.testng.annotations.Test;

public class LoginTest extends BrowserFactory {

    @Test
    public void login() {
        new LoginPage().open().login();
    }

//    @Test (dependsOnMethods = "login")
    public void logoutTest() {
        new BoardsListPage().logout();
    }
}
