package HomeWorkTests;

import com.trello.UI.Pages.ActiveBoardPage;
import com.trello.UI.Pages.LoginPage;
import com.trello.UI.core.BrowserFactory;
import org.testng.annotations.Test;

public class CreateBoardTest extends BrowserFactory {

        private static final String BOARD_NAME = "Test 123";

        @Test
        public void CreateBoardTest() {
            new LoginPage().open()
                    .login()
                    .createNewBoard()
                    .submitBoardCreate(BOARD_NAME);
        }

        @Test
        public void DeleteBoardTest() {
            new ActiveBoardPage().deleteBoard().permanentlyDeleteBoard();
        }
}
