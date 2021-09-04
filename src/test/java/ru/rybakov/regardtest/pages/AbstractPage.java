package ru.rybakov.regardtest.pages;

import ru.rybakov.regardtest.utils.WebDriverWrapper;

public class AbstractPage {
    public WebDriverWrapper driver = WebDriverWrapper.getInstance();
    public String baseUrl;

    public AbstractPage(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void open() {
        driver.get(baseUrl);
    }
}
