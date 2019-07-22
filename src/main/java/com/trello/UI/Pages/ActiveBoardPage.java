package com.trello.UI.Pages;

import com.trello.API.Models.Board;
import com.trello.API.Models.Card;
import com.trello.API.Models.ListOnBoard;
import com.trello.API.TrelloRestClient;
import com.trello.UI.Pages.Modals.CardModal;
import com.trello.UI.core.Elem;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.trello.UI.core.BrowserFactory.driver;
import static com.trello.UI.core.Constants.*;

public class ActiveBoardPage {

    public Elem sidebarMenu = new Elem(By.xpath("//div[@class='board-menu-tab-content']"));
    public Elem sidebarMenuMoreButton = new Elem(By.xpath("//a[@class='board-menu-navigation-item-link js-open-more']"));
    public Elem sidebarMenuCloseBoardButton = new Elem(By.xpath("//a[@class='board-menu-navigation-item-link js-close-board']"));
    public Elem sidebarMenuCloseBoardConfirmationButton = new Elem(By.xpath("//input[@class='js-confirm full negate']"));
    public Elem permitionDropdown = new Elem(By.xpath("//a[@id='permission-level']"));
    public Elem privateBoardCheckBox = new Elem(By.xpath("//ul[@class='pop-over-list']/li[1]//span[@class='icon-sm icon-check']"));
    public Elem noPermitionToAccessBoardMessageContainer = new Elem(By.xpath("//div[@class='big-message quiet']/h1"));
    public Elem publicBoardBlock = new Elem(By.xpath("//ul[@class='pop-over-list']/li[4]"));
    public Elem publicConfirmationButton = new Elem(By.xpath("//button[@class='js-submit wide primary full make-public-confirmation-button']"));
    public Elem publicBoardCheckBox = new Elem(By.xpath("//ul[@class='pop-over-list']/li[4]//span[@class='icon-sm icon-check']"));
    public Elem permissionDropDownBlock = new Elem(By.xpath("//div[@class='no-back']"));
    public Elem addListBtn = new Elem(By.xpath("//span[@class='placeholder']"));
    public Elem addListInput = new Elem(By.xpath("//input[@class='list-name-input']"));
    public Elem addListSubmitBtn = new Elem(By.xpath("//input[@class='primary mod-list-add-button js-save-edit']"));
    public Elem addCardBtn = new Elem(By.xpath("(//a[@class='open-card-composer js-open-card-composer'])[1]"));
    public Elem addCardInput = new Elem(By.xpath("//textarea[@class='list-card-composer-textarea js-card-title']"));
    public Elem addCardSubmitBtn = new Elem(By.xpath("//input[@class='primary confirm mod-compact js-add-card']"));
    public Elem cardBlock = new Elem(By.xpath("//div[@class='list-card-details js-card-details']"));
    public Elem listNames = new Elem(By.xpath("//h2[@class='list-header-name-assist js-list-name-assist']"));
    public Elem listDropDown = new Elem(By.xpath("//a[contains(@class, 'list-header-extras-menu')]"));
    public Elem copyListDropDown = new Elem(By.xpath("//a[@class='js-copy-list']"));
    public Elem archiveListDropDown = new Elem(By.xpath("//a[@class='js-close-list']"));
    public Elem copyListTextarea = new Elem(By.xpath("//textarea[@class='js-autofocus']"));
    public Elem copyListSubmitBtn = new Elem(By.xpath("//input[@class='primary wide js-submit']"));

    TrelloRestClient client = new TrelloRestClient();

    public boolean isOpened() {
        return sidebarMenu.isPresent(4);
    }

    public boolean isBoardNamePresentInUrl(String boardName) {
        return driver().getCurrentUrl().contains(boardName.toLowerCase().replaceAll(" ", "-"));
    }

    public CloseBoardPage deleteBoard() {
        sidebarMenuMoreButton.click();
        sidebarMenuCloseBoardButton.click();
        sidebarMenuCloseBoardConfirmationButton.click();
        CloseBoardPage closeBoardPage = new CloseBoardPage();
        Assert.assertTrue(closeBoardPage.isOpenedAfterDeleteBoard(), "Close board page is not opened");
        return closeBoardPage;
    }

    public CloseBoardPage checkThatBoardIsPrivate() {
        Assert.assertTrue(permitionDropdown.isElementActive());
        permitionDropdown.click();
        Assert.assertTrue(permissionDropDownBlock.isElementActive());
        Assert.assertTrue(privateBoardCheckBox.isPresent(4));
        driver().manage().deleteAllCookies();
        driver().navigate().refresh();
        CloseBoardPage closeBoardPage = new CloseBoardPage();
        Assert.assertTrue(closeBoardPage.isOpenedAfterMakeBoardPrivate(), "Close board page is not opened");
        return closeBoardPage;
    }

    public ActiveBoardPage checkThatBoardIsPublic() {
        Assert.assertTrue(permitionDropdown.isElementActive());
        permitionDropdown.click();
        Assert.assertTrue(publicBoardBlock.isPresent(4));
        publicBoardBlock.click();
        publicConfirmationButton.click();
        permitionDropdown.click();
        Assert.assertTrue(publicBoardCheckBox.isPresent(4));
        driver().manage().deleteAllCookies();
        driver().navigate().refresh();
        sidebarMenu.isPresent(4);
        return this;
    }

    public ActiveBoardPage addList() {
        addListBtn.click();
        addListInput.type(TEST_LIST_NAME_UI);
        addListSubmitBtn.click();
        return this;
    }

    public List<String> getTitleList() {
        List<Elem> titleElemsList = listNames.getElemList();
        List<String> titleList = new ArrayList<>();
        for (Elem e : titleElemsList) {
            titleList.add(e.getAttribute("innerHTML"));
        }
        return titleList;
    }

    public boolean isCheckListPresent(String listName) {
        return getTitleList().contains(listName);
    }

    public ActiveBoardPage copyList() {
        listDropDown.click();
        copyListDropDown.click();
        copyListTextarea.type(TEST_LIST_NAME_UI);
        copyListSubmitBtn.click();
        return this;
    }

    public void deleteList() {
        listDropDown.click();
        archiveListDropDown.click();
    }

    public ActiveBoardPage addCard() {
        addCardBtn.click();
        addCardInput.type("New Test Card");
        addCardSubmitBtn.click();
        return this;
    }

    public CardModal openCard() {
        cardBlock.click();
        CardModal cardModal = new CardModal();
        cardModal.isOpened();
        return cardModal;
    }

    public String getCardId() throws IOException {
        List<Board> boardList = client.boardsService.getMembersBoards(USER_NAME_DEFAULT).execute().body();
        List<ListOnBoard> lists = client.listsService.getLists(boardList.get(0).id).execute().body();
        List<Card> cards = client.cardService.getCards(lists.get(0).id).execute().body();
        return cards.get(0).id;
    }
}

