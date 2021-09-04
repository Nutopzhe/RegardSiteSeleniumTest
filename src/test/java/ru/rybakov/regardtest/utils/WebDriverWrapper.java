package ru.rybakov.regardtest.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebDriverWrapper {
    private static RemoteWebDriver driver;
    private static WebDriverWrapper wrap;
    private final WebDriverWait wait;

    private static Logger log = LogManager.getRootLogger();

    private WebDriverWrapper() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        wait = new WebDriverWait(driver, 5, 250);
    }

    public static WebDriverWrapper getInstance() {
        if (wrap == null) {
            wrap = new WebDriverWrapper();
        }
        return wrap;
    }

    public void get(String baseUrl) {
        driver.get(baseUrl);
    }

    public WebElement findElement(By xpath) {
        WebElement element = driver.findElement(xpath);
        driver.executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }

    public List<WebElement> findElements(By xpath) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));
    }

    public void waitTextToBePresentInElement(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void waitElementToBeClickable(String xpath) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public void close() {
        driver.close();
    }

    public void quit() {
        driver.quit();
    }

    public WebDriver.TargetLocator switchTo() {
        return driver.switchTo();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
