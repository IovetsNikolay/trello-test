package HomeWorkTests;

import com.trello.API.Models.Members;
import com.trello.UI.Pages.BoardsListPage;
import com.trello.UI.Pages.LoginPage;
import com.trello.UI.Pages.Modals.CardModal;
import com.trello.UI.core.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class CardTest extends BrowserFactory {

    CardModal cardModal = new CardModal();
    String cardId;

    @BeforeTest
    public void login() throws IOException, InterruptedException {
        cardId = new LoginPage().open().login()
                .setUpCardApi();
        new BoardsListPage().openBoard(1)
                .openCard();
    }

    @Test
    public void addMembers() throws IOException {
        Members memberCreatedUi = cardModal.addMember();
        Members memberFromApi = cardModal.getMemberApi(cardId);
        Assert.assertEquals(memberCreatedUi, memberFromApi, "Member create from UI is not equal API get member");
    }
}