package com.trello.API.RetrofitRest;

import com.trello.API.Models.Board;
import com.trello.API.Models.Card;
import com.trello.API.Models.ListOnBoard;
import com.trello.API.TrelloRestClient;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ListServiceClass {

    TrelloRestClient client = new TrelloRestClient();
    List<Board> boardList;
    List<ListOnBoard> listOnBoards;
    ListOnBoard listOnBoard;

    @BeforeTest
    public void setUp() throws IOException {
        boardList = (client.boardsService.getMembersBoards("mykola175").execute().body());
    }

    @Test
    public void getList() throws IOException {
        listOnBoards = client.listsService.getLists(boardList.get(0).id).execute().body();
        listOnBoards.forEach(e -> System.out.println(e));
    }

    @Test(dependsOnMethods = "getList")
    public void createList() throws IOException {
        listOnBoard = client.listsService.createList("New Test List", boardList.get(0).id).execute().body();
    }

    @Test(dependsOnMethods = "createList")
    public void modifyList() throws IOException {
        listOnBoard.name = "New Modify Name";
        listOnBoard = client.listsService.updateList(listOnBoard.id, listOnBoard).execute().body();
    }

    @Test(dependsOnMethods = "modifyList")
    public void archiveList() throws IOException {
        listOnBoard = listOnBoards.get(0);
        System.out.println(client.listsService.archiveBoard(listOnBoard.id, true, listOnBoard).execute().body());
    }
}
