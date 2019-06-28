package com.trello.API.RetrofitRest;

import com.trello.API.Models.Board;
import com.trello.API.TrelloRestClient;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class BoardServiceClass {

    TrelloRestClient client = new TrelloRestClient();
    List<Board> boardList;
    Board board;

//    @Test
    public void getAuthentificationResponse() throws IOException {

        String respponce = client.boardsService.getAuthentificationResponse("password", "trellotesttest@yopmail.com", "feedwteks", "b5f934bc-4f71-4c46-8f2a-a4191430083e-fa48170d80d7820d2489ab97").execute().body().string();
    }

//    @Test
    public void getBoardList() throws IOException {
        boardList = (client.boardsService.getMembersBoards("mykola175").execute().body());
        boardList.forEach(e -> System.out.println(e));
    }

    @Test
    public void createBoard() throws IOException {
        board = client.boardsService.createBoard("Vasia Pupkin1").execute().body();
        System.out.println(board);
    }

    @Test(dependsOnMethods = "createBoard")
    public void updateBoard() throws IOException {
        board.desc = "ASDASDDSADASSDASDASASDDASDAS";
        client.boardsService.updateBoard(board.id, board).execute();
    }

    @Test (dependsOnMethods = "getBoardList")
    public void deleteBoard() throws IOException {
        client.boardsService.deleteBoard(boardList.get(0).id).execute();
    }

    @Test (dependsOnMethods = "getBoardList")
    public void deleteAllBoards() throws IOException {
        boardList.forEach(e-> {
            try {
                client.boardsService.deleteBoard(e.id).execute();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

}
