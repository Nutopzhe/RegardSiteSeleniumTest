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
        log.debug("Инициализирую обертку над веб драйвером");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        wait = new WebDriverWait(driver, 5, 250);
        log.debug("Инициализация обертки над веб драйвером завершена!");
    }

    public static WebDriverWrapper getInstance() {
        log.debug("Запрос экземпляра веб драйвера");
        if (wrap == null) {
            wrap = new WebDriverWrapper();
        }
        return wrap;
    }

    public void get(String baseUrl) {
        log.debug("Открываю страницу по адресу '{}'", baseUrl);
        driver.get(baseUrl);
        log.debug("Страница по адресу '{}' успешно открыта", baseUrl);
    }

    public WebElement findElement(By xpath) {
        log.debug("Ищу элемент по локатору '{}'", xpath);
        WebElement element = driver.findElement(xpath);
        driver.executeScript("arguments[0].scrollIntoView(true);", element);
        log.debug("Элемент по локатору '{}' найден!", xpath);
        return element;
    }

    public List<WebElement> findElements(By xpath) {
        log.debug("Ищу список элементов по локатору '{}'", xpath);
        List<WebElement> elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpath));
        log.debug("Список элементов по локатору '{}' найден!", xpath);
        return elements;
    }

    public void waitTextToBePresentInElement(WebElement element, String text) {
        log.debug("Жду пока у элемента '{}' отобразиться текст '{}'", element, text);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        log.debug("Ожидаемый текст у элемента '{}' отобразился", element);
    }

    public void waitElementToBeClickable(String xpath) {
        log.debug("Жду пока по локатору '{}' будет доступен элемент для клика по нему", xpath);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        log.debug("Элемент по локатору '{}' доступен для клика", xpath);
    }

    public void close() {
        log.debug("Закрываю текущую страницу!");
        driver.close();
    }

    public void quit() {
        log.debug("Закрываю браузер!");
        driver.quit();
    }

    public WebDriver.TargetLocator switchTo() {
        log.debug("Переключаюсь на всплывающее окно Alert");
        return driver.switchTo();
    }

    public String getCurrentUrl() {
        log.debug("Получаю адрес текущей страницы");
        return driver.getCurrentUrl();
    }
}
