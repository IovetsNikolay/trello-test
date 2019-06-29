package com.trello.UI.Pages.Modals;

import com.trello.API.Models.*;
import com.trello.API.TrelloRestClient;
import com.trello.UI.core.Elem;
import org.openqa.selenium.By;
import static com.trello.UI.core.Constants.*;

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
        List<CardLabel> labelList = card.labels;
        return labelList.get(0);
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
        return client.cardService.getChecklistList(cardId).execute().body().get(0);
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
        Card card = new Card();
        card = client.cardService.getCard(cardId).execute().body();
        return card.due;
    }
}
