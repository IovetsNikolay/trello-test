package HomeWorkTests;

import com.trello.UI.Pages.LoginPage;
import com.trello.UI.core.BrowserFactory;
import org.testng.annotations.Test;


public class FavoriteBoardsTest extends BrowserFactory {

    @Test
    private void favoriteBoardsTest() {
        new LoginPage().open().login()
                .makeBoardFavorite()
                .makeBoardUnfavorite();
    }
}
