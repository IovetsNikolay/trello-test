package HomeWorkTests;

import com.trello.UI.Pages.ActiveBoardPage;
import com.trello.UI.Pages.LoginPage;
import com.trello.UI.core.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CreateBoardTest extends BrowserFactory {

        private static final String BOARD_NAME = "Test 123";

        @Test
        public void CreateBoardTest() {
            new LoginPage()
                    .autoLogin()
                    .createNewBoard()
                    .submitBoardCreate(BOARD_NAME);
        }

        @Test
        public void DeleteBoardTest() {
            new ActiveBoardPage().deleteBoard().permanentlyDeleteBoard();
        }

    public void dragAndDrop(By first, By second){
        Actions actions = new Actions(driver());
        actions.dragAndDrop(driver().findElement(first), driver().findElement(second)).perform();
    }

}
