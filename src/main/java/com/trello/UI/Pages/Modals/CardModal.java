package com.trello.UI.Pages.Modals;

import com.trello.API.Models.*;
import com.trello.API.TrelloRestClient;
import com.trello.UI.core.Elem;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static com.trello.UI.core.BrowserFactory.getWebDriverWait;
import static com.trello.UI.core.Constants.*;
import static java.time.Duration.ofMillis;

import java.io.IOException;
import java.util.List;



public class CardModal {

    private Elem modalTitle = new Elem(By.xpath("//textarea[@class='mod-card-back-title js-card-detail-title-input']"));
    private Elem membersBtn = new Elem(By.xpath("//a[@class='button-link js-change-card-members']"));
    private Elem memberNameBtn= new Elem(By.xpath("//a[contains(@class,'name js-select-member')]"));
    private Elem memberCloseBtn= new Elem(By.xpath("//a[@class='pop-over-header-close-btn icon-sm icon-close']"));
    private Elem memberName = new Elem(By.xpath("//span[@class='full-name']"));
    private Elem labelsBtn = new Elem(By.xpath("//a[contains(@class,'button-link js-edit-labels')]"));
    private Elem labelBlock = new Elem(By.cssSelector("span.card-label"));
    private Elem labelsCloseBtn = new Elem(By.xpath("//a[@class='pop-over-header-close-btn icon-sm icon-close']"));
    private Elem checklistBtn = new Elem(By.xpath("//a[@class='button-link js-add-checklist-menu']"));
    private Elem checklistInput = new Elem(By.xpath("//input[@id='id-checklist']"));
    private Elem checklistAddBtn = new Elem(By.xpath("//input[@class='primary wide confirm js-add-checklist']"));
    private Elem checklistName = new Elem(By.xpath("//h3[@class='current hide-on-edit']"));
    private Elem dueDateBtn = new Elem(By.xpath("//a[@class='button-link js-add-due-date']"));
    private Elem dueDateInput = new Elem(By.xpath("//input[@class='datepicker-select-input js-dpicker-date-input js-autofocus']"));
    private Elem dueDateTimeInput = new Elem(By.xpath("//input[@class='datepicker-select-input js-dpicker-time-input']"));
    private Elem dueDateSaveBtn = new Elem(By.xpath("//input[@class='primary wide confirm']"));
    private Elem dueDateBlock = new Elem(By.xpath("//span[@class='js-date-text card-detail-due-date-text js-details-edit-due-date']"));
    private Elem dueDateCheckBox = new Elem(By.xpath("//span[@class='card-detail-badge-due-date-complete-box js-card-detail-due-date-badge-complete js-details-toggle-due-date-complete']"));
    private Elem attachmentBtn = new Elem(By.xpath("//a[@class='button-link js-attach']"));
    private Elem attachLinkInput = new Elem(By.xpath("//input[@id='addLink']"));
    private Elem attachNameInput = new Elem(By.xpath("//input[@id='nameLink']"));
    private Elem attachBtn = new Elem(By.xpath("//input[@class='js-add-attachment-url']"));
    private Elem attachmentLinkPreview = new Elem(By.xpath("//a[@class='attachment-thumbnail-preview']"));
    private Elem attachmentName = new Elem(By.xpath("//span[@class='attachment-thumbnail-name']"));
    private Elem moveBtn = new Elem(By.xpath("//a[@class='button-link js-move-card']"));
    private Elem moveListSelect = new Elem(By.xpath("//select[@class='js-select-list']"));
    private Elem moveSubmitBtn = new Elem(By.xpath("//input[@class='primary wide js-submit']"));
    private Elem copyBtn = new Elem(By.xpath("//a[@class='button-link js-copy-card']"));
    private Elem copyNameInput = new Elem(By.xpath("//textarea[@class='js-autofocus']"));
    private Elem copySelect = new Elem(By.xpath("//select[@class='js-select-list']"));
    private Elem copyCreateCardBtn = new Elem(By.xpath("//input[@class='primary wide js-submit']"));
    private Elem sunscribeBtn = new Elem(By.xpath("//a[@class='button-link toggle-button is-on js-unsubscribe']"));
    private Elem archiveBtn = new Elem(By.xpath("//a[@class='button-link js-archive-card']"));
    private Elem unarchiveBtn = new Elem(By.xpath("//a[@class='button-link js-unarchive-card']"));
    private Elem shareBtn = new Elem(By.xpath("//a[@class='button-link js-more-menu']"));
    private Elem shareInput = new Elem(By.xpath("//input[@class='js-short-url js-autofocus']"));
    private Elem shareCloseBtn = new Elem(By.xpath("//a[@class='pop-over-header-close-btn icon-sm icon-close']"));
    private Elem commentTextArea = new Elem(By.xpath("//textarea[@class='comment-box-input js-new-comment-input']"));
    private Elem commentSaveBtn = new Elem(By.xpath("//input[@class='primary confirm mod-no-top-bottom-margin js-add-comment']"));
    private Elem descTextInput = new Elem(By.xpath("//a[@class='description-fake-text-area hide-on-edit js-edit-desc js-hide-with-draft']"));
    private Elem descTextArea = new Elem(By.xpath("//textarea[@class='field field-autosave js-description-draft description card-description']"));
    private Elem descSaveBtn = new Elem(By.xpath("//input[@class='primary confirm mod-submit-edit js-save-edit']"));

    TrelloRestClient client = new TrelloRestClient();

    public boolean isOpened() {
        return modalTitle.isPresent(4);
    }

    public Members addMember() {
        Members members = new Members();
        membersBtn.click();
        memberNameBtn.click();
        String name = memberName.getAttribute("name");
        memberCloseBtn.click();
        members.fullName = name.replaceAll(" \\(.*", "");
        members.username = name.replaceAll(".* \\(", "").replaceAll("\\)", "");
        return members;
    }

    public Members getMemberApi(String cardId) throws IOException {
        return client.cardService.getMembersList(cardId).execute().body().get(0);
    }

    public CardLabel addLabel() {
        CardLabel cardLabel = new CardLabel();
        labelsBtn.click();
        cardLabel.color = labelBlock.getAttribute("data-color");
        cardLabel.id = labelBlock.getAttribute("data-idlabel");
        labelBlock.click();
        labelsCloseBtn.click();
        return cardLabel;
    }

    public CardLabel getLabelApi (String cardId) throws IOException {
        Card card = client.cardService.getCard(cardId).execute().body();
        getWebDriverWait(6).until(d-> card.labels.size() > 0);
        return card.labels.get(0);
    }

    public CheckList addChecklist() {
        CheckList checkList = new CheckList();
        checklistBtn.click();
        checklistInput.type(TEST_CHECKLIST_NAME);
        checklistAddBtn.click();
        checkList.name = checklistName.getText();
        return checkList;
    }

    public CheckList getCheckList(String cardId) throws IOException {
        List<CheckList> checkLists = client.cardService.getChecklistList(cardId).execute().body();
        getWebDriverWait(6).until(d-> checkLists.size() > 0);
        return checkLists.get(0);
    }

    public void addDueDate() {
        dueDateBtn.click();
        dueDateInput.type(DUE_DATE_DEFAULT_VALUE);
//        dueDateTimeInput.type(DUE_DATE_DEFAULT_TIME_VALUE);
        dueDateSaveBtn.click();
        dueDateBlock.isPresent(4);
        dueDateCheckBox.click();
    }

    public String getDueDate(String cardId) throws IOException {
        Card card = client.cardService.getCard(cardId).execute().body();
        return card.due;
    }

    public Attachment addAttachment() {
        Attachment attachment = new Attachment();
        attachmentBtn.click();
        attachLinkInput.type(ATTACHMENT_LINK_DEFAULT_VALUE);
        attachNameInput.type(ATTACHMENT_NAME_DEFAULT_VALUE);
        attachBtn.click();
        attachmentLinkPreview.isPresent(4);
        attachment.url = attachmentLinkPreview.getAttribute("href");
        attachment.name = attachmentName.getText();
        return attachment;
    }

    public Attachment getAttachmentApi(String cardId) throws IOException {
        List<Attachment> attachments = client.cardService.getAttachmentsList(cardId).execute().body();
        getWebDriverWait(6).until(d-> attachments.size() > 0);
        return attachments.get(0);
    }


    public List<ListOnBoard> getListOnBoardList() throws IOException {
        List<Board> boardList = client.boardsService.getMembersBoards(USER_NAME_DEFAULT).execute().body();
        return client.listsService.getLists(boardList.get(FIRST_CARD_IN_LIST_NUMBER).id).execute().body();
    }

    public List<Card> getCardsListByListsNumber(int listNumber) throws IOException {
        return client.cardService.getCards(getListOnBoardList().get(listNumber).id).execute().body();
    }

    public void moveCard() throws IOException {
        moveBtn.click();
        moveListSelect.selectDropDownByValue(getListOnBoardList().get(SECOND_LIST_ON_BOARD_NUMBER).id);
        moveSubmitBtn.click();
    }

    public boolean checkThatCardMoved(String cardId) throws IOException {
        return (getCardsListByListsNumber(SECOND_LIST_ON_BOARD_NUMBER).get(0).id.equals(cardId)
                && getCardsListByListsNumber(FIRST_LIST_ON_BOARD_NUMBER).isEmpty());
    }

    public void copyCard() throws IOException {
        copyBtn.click();
        copyNameInput.type(COPIED_CARD_TITLE);
        copySelect.selectDropDownByValue(getListOnBoardList().get(THIRD_LIST_ON_BOARD_NUMBER).id);
        copyCreateCardBtn.click();
    }

    public boolean checkThatCardCopied(String cardId) throws IOException {
        return (getCardsListByListsNumber(SECOND_LIST_ON_BOARD_NUMBER).get(FIRST_CARD_IN_LIST_NUMBER).id.equals(cardId)
                && getCardsListByListsNumber(THIRD_LIST_ON_BOARD_NUMBER).get(FIRST_CARD_IN_LIST_NUMBER).name.equals(COPIED_CARD_TITLE));
    }

    public void sunscribeCard() {
        sunscribeBtn.click();
    }

    public boolean getSunscribeStatusApi(String cardId) throws IOException {
        getWebDriverWait(6)
                .until(d-> {
                    try {
                        return !client.cardService.getCard(cardId).execute().body().subscribed;
                    } catch (IOException e) {
                        return false;
                    }
                });
        return false;
    }

    public void archiveCard() {
        archiveBtn.click();
    }

    public void unarchiveCard() {
        unarchiveBtn.click();
    }

    public boolean checkThatBoardArchived(String cardId) throws IOException {
        getWebDriverWait(6)
                .until(d-> {
                    try {
                        return client.cardService.getCard(cardId).execute().body().closed;
                    } catch (IOException e) {
                        return false;
                    }
                });
        return true;
    }

    public boolean checkThatBoardUnarchived(String cardId) throws IOException {
        getWebDriverWait(6)
                .until(d-> {
                    try {
                        return !client.cardService.getCard(cardId).execute().body().closed;
                    } catch (IOException e) {
                        return false;
                    }
                });
        return false;
    }

    public String getShareLink() {
        shareBtn.click();
        String shareLink = shareInput.getAttribute("value");
        shareCloseBtn.click();
        return shareLink;
    }

    public void postComment() {
        commentTextArea.type(COMMENT_DEFAULT_TEXT);
        commentSaveBtn.click();
    }

    public int getCommentCounter(String cardId) throws IOException {
        int commentsCounter = client.cardService.getCard(cardId).execute().body().badges.comments;
        getWebDriverWait(6).until(d-> commentsCounter > 0);
        return commentsCounter;
    }

    public void setDescription() {
        descTextInput.click();
        descTextArea.type(DEFAULT_DESCRIPTION);
        descSaveBtn.click();
    }

    public String getDescription(String cardId) throws IOException {
        getWebDriverWait(6)
                .until(d-> {
                    try {
                        return client.cardService.getCard(cardId).execute().body().desc.isEmpty();
                    } catch (IOException e) {
                        return false;
                    }
                });
        return client.cardService.getCard(cardId).execute().body().desc;
    }

}
