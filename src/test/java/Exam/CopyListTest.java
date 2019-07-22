package Exam;

import com.trello.UI.Pages.ActiveBoardPage;
import com.trello.UI.Pages.BoardsListPage;
import com.trello.UI.Pages.LoginPage;
import com.trello.UI.core.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.trello.UI.core.Constants.*;


public class CopyListTest extends BrowserFactory {

    @Test
    public void createList() throws IOException {
        new LoginPage()
                .autoLogin()
                .setUpCardApi();
        new BoardsListPage()
                .openBoard(1)
                .copyList();
        Assert.assertTrue(new ActiveBoardPage().isCheckListPresent(TEST_LIST_NAME_UI), "List is not copied to board");
    }

}
