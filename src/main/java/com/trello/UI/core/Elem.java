package com.trello.UI.core;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.List;

import static com.trello.UI.core.BrowserFactory.*;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class Elem {

    private By by;
    private String name;

    public Elem(By by, String name) {
        this.by = by;
        this.name = name;
    }

    public Elem(By by) {
        this.by = by;
    }

    @Step
    public void click() {
        getWebDriverWait(6)
                .until(ExpectedConditions.elementToBeClickable(by));
        driver().findElement(by).click();
    }

    @Step
    public void type(String text) {
        getWebDriverWait(6)
                .until(ExpectedConditions.presenceOfElementLocated(by));
        driver().findElement(by).clear();
        driver().findElement(by).sendKeys(text);
    }

    public boolean isPresent(long seconds) {
        Wait wait = getWebDriverWait(seconds);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementActive() {
        Wait wait = getWebDriverWait(4);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            Actions actions = new Actions(driver());
            actions.moveToElement(driver().findElement(by));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public Elem addToLocator(Elem plusElem, int GroupNumber) {
        String[] array1 = this.by.toString().split("xpath: ", 2);
        String[] array2 = plusElem.by.toString().split("xpath: ", 2);
        return new Elem(By.xpath("(" + array1[1] + array2[1] + ")[" + GroupNumber + "]"));
    }

    public List<Elem> getElemList() {
        int elementsNumber = driver().findElements(by).size();
        List<Elem> elemList = new ArrayList<>();
        for (int i = 0; i < elementsNumber; i++) {
            elemList.add(new Elem(By.xpath("(" + this.getLocatorFromElem() + ")[" + (i+1) + "]")));
        }
        return elemList;
    }

    public By getByFromElem() {
        return by;
    }

    public String getLocatorFromElem() {
        return by.toString().replaceAll(".*:", "");
    }

    public String getAttribute(String attributeTitle) {
        return driver().findElement(by).getAttribute(attributeTitle);
    }

    public String getText() {
        return driver().findElement(by).getText();
    }

    public void moveToElem() {
        Actions actions = new Actions(driver());
        actions.moveToElement(driver().findElement(by)).build().perform();
    }

    @Step
    public void selectDropDownByValue(String value) {
        Select select = new Select(driver().findElement(by));
        select.selectByValue(value);
    }

}
