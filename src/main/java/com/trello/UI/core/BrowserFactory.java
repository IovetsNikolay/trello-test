package com.trello.UI.core;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

public class BrowserFactory {

    private static WebDriver driver;

    @BeforeTest
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:/webDrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver = new EventFiringWebDriver(driver).register(new BrowserListener());
//        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }

    @AfterTest
    public void TearDown() {
        driver().quit();
    }

    public static WebDriver driver() {
        return driver;
    }

    public void get(String url) {
        driver().get(url);
    }

    public static FluentWait<WebDriver> getWebDriverWait(long timeout) {
        return new WebDriverWait(driver(), timeout)
                .pollingEvery(ofMillis(500))
                .ignoring(ElementNotVisibleException.class).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
    }

    public String getPageUrl() {
        return driver().getCurrentUrl();
    }

    public WebDriver getAnoutherDriver() {
        driver = new ChromeDriver();
        return driver;
    }
}
