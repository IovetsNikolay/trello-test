package com.trello.UI.Pages.Modals;

import com.trello.UI.Pages.ActiveBoardPage;
import com.trello.UI.core.Elem;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.trello.UI.core.BrowserFactory.driver;

public class CreateBoardModal {

    public Elem createBoardModalInput = new Elem(By.xpath("//div[@class='board-tile create-board-tile has-photo-background']//div[1]/input"));
    public Elem createBoardModalSubmitButton = new Elem(By.xpath("//button[@class='primary']"));

    public boolean isOpened() {
        return createBoardModalInput.isPresent(4);
    }

    public ActiveBoardPage submitBoardCreate(String createGroupName) {
        createBoardModalInput.type(createGroupName);
        createBoardModalSubmitButton.click();
        ActiveBoardPage activeBoardPage = new ActiveBoardPage();
        Assert.assertTrue(activeBoardPage.isOpened(), "Board page is not opened");
        Assert.assertTrue(activeBoardPage.isBoardNamePresentInUrl(createGroupName), "Board page URL is not contents board name");
        return activeBoardPage;
    }
}
