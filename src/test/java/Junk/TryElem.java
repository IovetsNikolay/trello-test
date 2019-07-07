package Junk;

import com.trello.UI.Pages.LoginPage;
import com.trello.UI.core.BrowserFactory;
import com.trello.UI.core.Elem;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.List;

public class TryElem extends BrowserFactory {

    public Elem CloseBoardConfirmationLink = new Elem(By.xpath("//a[@class='quiet js-delete']"));
    public Elem personalBoardsElements = new Elem(By.xpath("//span[@class='icon-lg icon-member']/../../..//li"));


    @Test
    public void elemTest() {
        new LoginPage().autoLogin();
        List<Elem> elemList = personalBoardsElements.getElemList();
    }
}
