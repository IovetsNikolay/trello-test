package com.trello.UI.Pages;

import com.trello.API.AutoLogin.TrelloApiLogin;
import com.trello.UI.core.Elem;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.IOException;

import static com.trello.UI.core.BrowserFactory.*;
import static com.trello.UI.core.Constants.*;

public class LoginPage {

    private static final String PATH = "login";

    public Elem userInput = new Elem(By.id("user"), "Login Field");
    public Elem passwordInput = new Elem(By.id("password"),"Password Input");
    public Elem submitButton = new Elem(By.id("login"));

    public LoginPage open() {
        driver().get(URL + PATH);
        Assert.assertTrue(isOpened(), "Page 'login' ["+PATH+"] not opened");
        return this;
    }

    public boolean isOpened() {
        return submitButton.isPresent(4);
    }

    @Step ("Login with login:{email} and password:{password}")
    public BoardsListPage login(String email, String password) {
        userInput.type(email);
        passwordInput.type(password);
        submitButton.click();
        BoardsListPage boardsListPage = new BoardsListPage();
        Assert.assertTrue(boardsListPage.isOpened(), "Boards list page is not opened");
        return boardsListPage;
    }

    public BoardsListPage login() {
        return login(LOGIN_DEFAULT, PASSWORD_DEFAULT);
    }

    @Step
    public BoardsListPage autoLogin(String email, String password) {
        try {
            new TrelloApiLogin().LoginByApi(email, password);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        BoardsListPage boardsListPage = new BoardsListPage();
        Assert.assertTrue(boardsListPage.isOpened(), "Boards list page is not opened");
        return boardsListPage;
    }

    public BoardsListPage autoLogin() {
        return autoLogin(LOGIN_DEFAULT, PASSWORD_DEFAULT);
    }

}
