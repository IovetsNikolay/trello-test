package HomeWorkTests;

import com.trello.API.Models.CardLabel;
import com.trello.API.Models.CheckList;
import com.trello.API.Models.Members;
import com.trello.UI.Pages.BoardsListPage;
import com.trello.UI.Pages.LoginPage;
import com.trello.UI.Pages.Modals.CardModal;
import com.trello.UI.core.BrowserFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import static com.trello.UI.core.Constants.*;

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

    @Test(dependsOnMethods = "addMembers")
    public void AddLabel() throws IOException {
        CardLabel labelCreatedUi = cardModal.addLabel();
        CardLabel labelFromApi = cardModal.getLabelApi(cardId);
        Assert.assertEquals(labelCreatedUi, labelFromApi, "Label created from UI is not equals API get label");
    }

    @Test(dependsOnMethods = "AddLabel")
    public void addCheckList() throws IOException {
        CheckList checklistCreatedUi = cardModal.addChecklist();
        CheckList checklistFromApi  = cardModal.getCheckList(cardId);
        Assert.assertEquals(checklistCreatedUi, checklistFromApi, "Checklist created from UI is not equals API get checklist");
    }

    @Test(dependsOnMethods = "addCheckList")
    public void addDueDate() throws IOException {
        cardModal.addDueDate();
        String dueDateFromApi = cardModal.getDueDate(cardId);
        Assert.assertTrue(dueDateFromApi.equals(DUE_DATE_DEFAULT_API_VALUE), "Due Date created from UI is not equals API get Due Date");
    }

    @Test(dependsOnMethods = "adDueDate")
    public void addAttachment() throws IOException {
        Attachment attachmentCreatedUi = cardModal.addAttachment();
        Attachment attachmentFromApi = cardModal.getAttachmentApi(cardId);
        Assert.assertEquals(attachmentCreatedUi, attachmentFromApi, "Attachment created from UI is not equals API get attachment");
    }
}