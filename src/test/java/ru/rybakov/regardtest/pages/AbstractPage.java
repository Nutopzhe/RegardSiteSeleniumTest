package ru.rybakov.regardtest.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.rybakov.regardtest.utils.WebDriverWrapper;

public abstract class AbstractPage {
    public static Logger log = LogManager.getRootLogger();
    public WebDriverWrapper driver = WebDriverWrapper.getInstance();
    public String baseUrl;

    public AbstractPage(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void open() {
        driver.get(baseUrl);
        log.info("Страница по адресу '{}' открыта", baseUrl);
    }

    public void close() {
        driver.close();
        log.info("Страница по адресу '{}' была закрыта", baseUrl);
    }
}
