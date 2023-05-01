package dwbi.proiect_dwbi;

import io.cucumber.java.After;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.springframework.util.ObjectUtils.isEmpty;

public class Hook {
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
        chromeOptions.addArguments("chrome.switches", "--disable-extensions --disable-extensions-file-access-check --disable-extensions-http-throttling --disable-infobars --enable-automation --start-maximized");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        return webDriver;
    }

    public void closeDriver() {
        if (webDriver == null) {
            return;
        }
        webDriver.quit();
        webDriver = null;
    }

    @After
    public void closeBrowserAfterScenario(){
        webDriver.close();
    }
}
