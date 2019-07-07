package com.trello.API;

import com.trello.API.Models.Card;
import com.trello.API.Models.CheckList;
import com.trello.API.Models.ChecklistItem;
import com.trello.API.Models.ListOnBoard;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class TrelloApiTest {

    private TrelloApi trelloApi;
    private List<ListOnBoard> listOnBoardList;
    private List<Card> cardList;
    private List<CheckList> checklistsList;

    private static final String BOARD_ID = "5ce422edf8b1357014edf236";

    @BeforeMethod
    public void getBoardsListTest() throws IOException {
        trelloApi = new TrelloApi();
        listOnBoardList = trelloApi.getFieldsOnBoardsList(BOARD_ID);
        cardList = trelloApi.getCardList(listOnBoardList.get(0).id);
        checklistsList = trelloApi.getCheckListsList(cardList.get(0).id);
    }

    @Test
    public void createListTest() throws IOException {
        Card list = new Card();
        list.name = "New List";
        trelloApi.createList(BOARD_ID, list);
        listOnBoardList = trelloApi.getFieldsOnBoardsList(BOARD_ID);
        trelloApi.archiveList(listOnBoardList.get(0));
    }

    @Test (dependsOnMethods = "createListTest")
    public void createCardTest() throws IOException {
//        listOnBoardList = trelloApi.getFieldsOnBoardsList(BOARD_ID);
        ListOnBoard list = listOnBoardList.get(0);
        Card card = new Card();
        card.name = "CardTemp1: " + list.name;
        card.desc = "New Awersome CardTemp";
        trelloApi.createCard(list.id, card);
        cardList = trelloApi.getCardList(listOnBoardList.get(0).id);
        trelloApi.deleteCard(cardList.get(0).id);
    }

    @Test (dependsOnMethods = "createCardTest")
    public void addChecklistTest() throws IOException {
        Card card = cardList.get(0);
        CheckList checkList = new CheckList();
        checkList.name = "New checklist";
        trelloApi.createChecklist(card.id, checkList);
    }

    @Test(dependsOnMethods = "addChecklistTest")
    public void addCheckItem() throws IOException {
        CheckList checkList = checklistsList.get(0);
        ChecklistItem checkListItem = new ChecklistItem();
        checkListItem.name = "New ChecklistItem";
        trelloApi.createChecklistItem(checkList.id, checkListItem);
    }

//    @Test
    public void updateCard() {
    }
}

