package com.trello.UI.Pages;

import com.trello.UI.core.Elem;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.trello.UI.core.BrowserFactory.driver;

public class CloseBoardPage {

    public Elem CloseBoardConfirmationLink = new Elem(By.xpath("//a[@class='quiet js-delete']"));
    public Elem CloseBoardConfirmationModalButton = new Elem(By.xpath("//input[@class='js-confirm full negate']"));
    public Elem boardMissedMessageContainer = new Elem(By.xpath("//div[@class='big-message quiet']"));
    public Elem homeHeaderButton = new Elem(By.xpath("//span[@name='house']"));
    public Elem loginButton = new Elem(By.xpath("//a[@class='_3bl7HhpXOOyEkB _3ZSGaT29-gFMtm']"));


    public boolean isOpenedAfterDeleteBoard() {
        return CloseBoardConfirmationLink.isPresent(4);
    }

    public boolean isOpenedAfterMakeBoardPrivate() {
        return boardMissedMessageContainer.isPresent(4);
    }

    public BoardsListPage permanentlyDeleteBoard() {
        CloseBoardConfirmationLink.click();
        CloseBoardConfirmationModalButton.click();
        Assert.assertTrue(boardMissedMessageContainer.isPresent(4), "Board not found message is not displayed");
        homeHeaderButton.click();
        BoardsListPage boardsPage = new BoardsListPage();
        Assert.assertTrue(boardsPage.isOpened());
        return boardsPage;
    }

    public LoginPage goToLoginPage() {
        loginButton.click();
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.isOpened());
        return loginPage;
    }
}
