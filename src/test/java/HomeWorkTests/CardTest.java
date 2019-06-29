package HomeWorkTests;

import com.trello.API.Models.CardLabel;
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
        Assert.assertEquals(memberCreatedUi, memberFromApi, "Member created from UI is not equals API get member");
    }

    @Test
    public void AddLabel() throws IOException {
        CardLabel labelCreatedUi = cardModal.addLabel();
        CardLabel labelFromApi = cardModal.getLabelApi(cardId);
        Assert.assertEquals(labelCreatedUi, labelFromApi, "Label created from UI is not equals API get label");
    }
}