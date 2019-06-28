package Junk;

import com.trello.API.Models.Token;
import com.trello.API.TrelloRestClient;
import com.trello.UI.Pages.BoardsListPage;
import com.trello.UI.core.BrowserFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TryTrelloAPI extends BrowserFactory {

    TrelloRestClient client = new TrelloRestClient();
    List<Token> tokenList = new ArrayList<>();

    @Test
    public void callIt() throws IOException {
        tokenList = (client.boardsService.getUserTokenList("mykola175").execute().body());
        tokenList.forEach(e -> System.out.println(e));
    }

    @Test
    public void login() throws IOException {
        new BoardsListPage().open();
    }
}
