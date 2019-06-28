package com.trello.UI.Pages.Modals;

import com.trello.API.Models.Board;
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

    TrelloRestClient client = new TrelloRestClient();

    public boolean isOpened() {
        return modalTitle.isPresent(4);
    }

    public Members addMember() {
        membersBtn.click();
        memberNameBtn.click();
        String name = memberName.getAttribute("name");
        memberCloseBtn.click();
        Members members = new Members();
        members.fullName = name.replaceAll(" \\(.*", "");
        members.username = name.replaceAll(".* \\(", "").replaceAll("\\)", "");
        return members;
    }

    public Members getMemberApi(String cardId) throws IOException {
        List<Members> members = client.cardService.getMembersList(cardId).execute().body();
        return members.get(0);
    }
}
