package com.trello.UI.Pages.Modals;

import com.trello.API.Models.Board;
import com.trello.API.Models.Card;
import com.trello.API.Models.CardLabel;
import com.trello.API.Models.Members;
import com.trello.API.TrelloRestClient;
import com.trello.UI.core.Elem;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;

public class CardModal {

    Elem modalTitle = new Elem(By.xpath("//textarea[@class='mod-card-back-title js-card-detail-title-input']"));
    Elem membersBtn = new Elem(By.xpath("//a[@class='button-link js-change-card-members']"));
    Elem memberNameBtn= new Elem(By.xpath("//a[contains(@class,'name js-select-member')]"));
    Elem memberCloseBtn= new Elem(By.xpath("//a[@class='pop-over-header-close-btn icon-sm icon-close']"));
    Elem memberName = new Elem(By.xpath("//span[@class='full-name']"));
    Elem labelsBtn = new Elem(By.xpath("//a[contains(@class,'button-link js-edit-labels')]"));
    Elem labelBlock = new Elem(By.cssSelector("span.card-label"));
    Elem labelsCloseBtn = new Elem(By.xpath("//a[@class='pop-over-header-close-btn icon-sm icon-close']"));

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
}
