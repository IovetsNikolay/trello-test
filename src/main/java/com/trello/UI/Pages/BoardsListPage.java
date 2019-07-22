package com.trello.UI.Pages;

import com.trello.API.Models.Board;
import com.trello.API.Models.Card;
import com.trello.API.Models.ListOnBoard;
import com.trello.API.RetrofitRest.BoardServiceClass;
import com.trello.API.TrelloRestClient;
import com.trello.UI.Pages.Modals.CreateBoardModal;
import com.trello.UI.core.Elem;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.trello.UI.core.BrowserFactory.driver;
import static com.trello.UI.core.Constants.*;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;


public class BoardsListPage {

    private static final String PATH = "/boards";
    TrelloRestClient client = new TrelloRestClient();

    public Elem userDropdown = new Elem(By.xpath("//button[contains(@class, 'js-open-header-member-menu')]"));
    public Elem logoutOptionDropdown = new Elem(By.xpath("//button[@data-test-id='header-member-menu-logout']"));
    public Elem createNewDeckBlock = new Elem(By.xpath("//div[@class='board-tile mod-add']"));
    public Elem favoritedBoardsTitles = new Elem(By.xpath("//span[@class='icon-lg icon-star']/../../..//div[@dir='auto']"));
    public Elem personalBoardsTitles = new Elem(By.xpath("//span[@class='icon-lg icon-member']/../../..//div[@dir='auto']"));
    public Elem favoritedBoardsElements = new Elem(By.xpath("//span[@class='icon-lg icon-star']/../../..//li"));
    public Elem personalBoardsElements = new Elem(By.xpath("//span[@class='icon-lg icon-member']/../../..//li"));
    public Elem favoriteStarIcon = new Elem(By.xpath("//span[@class='icon-sm icon-star board-tile-options-star-icon']"));
    public Elem unfavoriteStarIcon = new Elem(By.xpath("//span[@class='icon-sm icon-star is-starred board-tile-options-star-icon']"));

    public void open() throws IOException {
        driver().get(URL + USER_NAME_DEFAULT + PATH);
        TrelloRestClient client = new TrelloRestClient();
        String token = client.boardsService.getUserTokenList(USER_NAME_DEFAULT).execute().body().get(0).id;
        Cookie cookie = new Cookie("token", token);
        driver().manage().addCookie(cookie);
        driver().navigate().refresh();
        Assert.assertTrue(isOpened(), "Page 'Boards' [" + USER_NAME_DEFAULT + PATH + "] not opened");
    }

    public boolean isOpened() {
        return createNewDeckBlock.isPresent(4);
    }

    public ActiveBoardPage openBoard(String urlName) {
        new Elem(By.cssSelector(".board-title[href*='" + urlName + "']"), urlName).click();
        ActiveBoardPage activeBoardPage = new ActiveBoardPage();
        Assert.assertTrue(activeBoardPage.isOpened(), "Board page is not opened");
        return activeBoardPage;
    }

    public ActiveBoardPage openBoard(int numberOfDocument) {
        new Elem(By.xpath("(//span[@class='icon-lg icon-member']/../../..//li)[" + numberOfDocument + "]")).click();
        ActiveBoardPage activeBoardPage = new ActiveBoardPage();
        Assert.assertTrue(activeBoardPage.isOpened(), "Board page is not opened");
        return activeBoardPage;
    }

    public LoggedOutPage logout() {
        userDropdown.click();
        logoutOptionDropdown.click();
        LoggedOutPage loggedOutPage = new LoggedOutPage();
        Assert.assertTrue(loggedOutPage.isOpened(), "Logged out page is not opened");
        return loggedOutPage;
    }

    public CreateBoardModal createNewBoard() {
        createNewDeckBlock.click();
        CreateBoardModal createBoardModal = new CreateBoardModal();
        Assert.assertTrue(createBoardModal.isOpened(), "Create board modal is not opened");
        return createBoardModal;
    }

    public List<String> getBoardsList(Elem elem) {
        List<String> boardsNameList = new ArrayList<>();
        elem.getElemList().forEach(e -> boardsNameList.add(e.getAttribute("title")));
        return boardsNameList;
    }

    public BoardsListPage makeBoardFavorite() {
        int favoritedBoardsListSize = favoritedBoardsTitles.getElemList().size();
        personalBoardsElements.getElemList().get(0).moveToElem();
        personalBoardsElements.addToLocator(favoriteStarIcon, 1).click();
        Assert.assertEquals(favoritedBoardsListSize, favoritedBoardsTitles.getElemList().size() - 1);
        return this;
    }

    public BoardsListPage makeBoardUnfavorite() {
        int favoritedBoardsListSize = favoritedBoardsTitles.getElemList().size();
        personalBoardsElements.getElemList().get(0).moveToElem();
        personalBoardsElements.addToLocator(unfavoriteStarIcon, 1).click();
        Assert.assertEquals(favoritedBoardsListSize, favoritedBoardsTitles.getElemList().size() + 1);
        return this;
    }

    public BoardsListPage deleteAllBoards() throws IOException {
        List<Board> boardList = client.boardsService.getMembersBoards(USER_NAME_DEFAULT).execute().body();
        boardList.forEach(e -> {
            try {
                client.boardsService.deleteBoard(e.id).execute();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        return this;
    }

    public String setUpCardApi() throws IOException {
        deleteAllBoards();
        Board board = client.boardsService.createBoard(TEST_BOARD_NAME).execute().body();
        ListOnBoard listOnBoard = client.listsService.createList(TEST_LIST_NAME, board.id).execute().body();
        Card card = client.cardService.createCard(TEST_CARD_NAME, listOnBoard.id).execute().body();
        return card.id;
    }

}
