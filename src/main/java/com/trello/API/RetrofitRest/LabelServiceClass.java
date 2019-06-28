package com.trello.API.RetrofitRest;

import com.trello.API.Models.*;
import com.trello.API.TrelloRestClient;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

import static com.trello.API.Models.ColoursEnum.*;


public class LabelServiceClass {
    TrelloRestClient client = new TrelloRestClient();
    List<Board> boardList;
    List<ListOnBoard> listOnBoards;
    List<Card> cardList;
    List<CardLabel> cardLabels;
    CardLabel cardLabel;

    @BeforeTest
    public void setUp() throws IOException {
        boardList = client.boardsService.getMembersBoards("mykola175").execute().body();
        listOnBoards = client.listsService.getLists(boardList.get(0).id).execute().body();

    }


    @Test
    public void getLabelsList() throws IOException {
        cardList = client.cardService.getCards(listOnBoards.get(0).id).execute().body();
        cardLabels = cardList.get(0).labels;
        cardLabels.forEach(System.out::println);
    }

    @Test(dependsOnMethods = "getLabelsList")
    public void createLabel() throws IOException {
        cardLabel = client.labelsService.createLabel("New Label", blue, boardList.get(0).id).execute().body();
    }

    @Test(dependsOnMethods = "createLabel")
    public void modifyLabel() throws IOException {
        cardLabel.color = "red";
        cardLabel = client.labelsService.modifyLabel(cardLabel.id, cardLabel).execute().body();
    }

    @Test(dependsOnMethods = "modifyLabel")
    public void deleteLabel() throws IOException {
        client.labelsService.deleteLabel(cardLabel.id).execute();
    }

}
