package com.trello.UI.Pages;

import com.trello.UI.core.Elem;
import org.openqa.selenium.By;

public class LoggedOutPage {

    public Elem loginHeaderBtn = new Elem(By.xpath("//a[@class='global-header-section-button']"));

    public boolean isOpened() {
        return loginHeaderBtn.isPresent(4);
    }

}
