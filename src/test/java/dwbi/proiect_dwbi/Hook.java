package dwbi.proiect_dwbi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.springframework.util.ObjectUtils.isEmpty;

public class Hook {
    private static final Logger LOGGER = LoggerFactory.getLogger(Hook.class);

    public WebDriver webDriver;


    public WebDriver getDriver() {
        if (isEmpty(webDriver)) {
            initializeDriver();
        }
        return webDriver;
    }

    public WebDriver initializeDriver(){
        if (!isEmpty(webDriver)) {
            closeDriver();
        }
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("window-size=1920,1080");
        return webDriver = new ChromeDriver(chromeOptions);
    }

    public void closeDriver() {
        if (webDriver == null) {
            return;
        }
        webDriver.quit();
        webDriver = null;
    }
}
