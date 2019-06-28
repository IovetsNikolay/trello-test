package com.trello.API.RetrofitRest;

import com.trello.API.Models.Board;
import com.trello.API.Models.Card;
import com.trello.API.Models.ListOnBoard;
import com.trello.API.TrelloRestClient;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class CardsServiceClass {

    TrelloRestClient client = new TrelloRestClient();
    List<Board> boardList;
    List<ListOnBoard> listOnBoards;
    List<Card> cardList;
    Card card;

    @BeforeTest
    public void setUp() throws IOException {
        boardList = client.boardsService.getMembersBoards("mykola175").execute().body();
        listOnBoards = client.listsService.getLists(boardList.get(0).id).execute().body();
    }

    @Test
    public void getCardsList() throws IOException {
        cardList = (client.cardService.getCards(listOnBoards.get(0).id).execute().body());
        cardList.forEach(System.out::println);
    }

    @Test(dependsOnMethods = "getCardsList")
    public void createCard() throws IOException {
        card = client.cardService.createCard("New Created Card", listOnBoards.get(0).id).execute().body();
    }

    @Test(dependsOnMethods = "createCard")
    public void modifyCard() throws IOException {
        card.name = "Updated name";
        card = client.cardService.updateCard(card.id, card).execute().body();
    }

    @Test(dependsOnMethods = "modifyCard")
    public void deleteCard() throws IOException {
        System.out.println(client.cardService.deleteCard(card.id).execute().body());
    }


}
