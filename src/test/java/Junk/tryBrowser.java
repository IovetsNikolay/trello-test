package Junk;

import com.trello.UI.core.BrowserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class tryBrowser extends BrowserFactory {
//    private Logger logger = LoggerFactory.getLogger(TryLoger.class);

    @Test
    public void setUp() {
        driver().get("https://www.google.com/");
    }
}
