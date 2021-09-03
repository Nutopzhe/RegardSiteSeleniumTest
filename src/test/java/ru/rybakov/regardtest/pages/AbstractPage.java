package ru.rybakov.regardtest.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import ru.rybakov.regardtest.utils.DriverWrapper;

public class AbstractPage {
    public RemoteWebDriver driver = DriverWrapper.getInstance();
    public String baseUrl;

    public AbstractPage(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void open() {
        driver.get(baseUrl);
    }
}
