package Exam;

import com.trello.UI.Pages.BoardsListPage;
import com.trello.UI.Pages.LoginPage;
import com.trello.UI.core.BrowserFactory;
import org.testng.annotations.Test;

public class LogoutTest extends BrowserFactory {

    @Test
    public void logoutTest() {
        new LoginPage()
                .autoLogin()
                .logout();
    }
}
