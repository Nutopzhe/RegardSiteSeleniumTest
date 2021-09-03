package ru.rybakov.regardtest.utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverWrapper {
    private static RemoteWebDriver driver;

    private DriverWrapper() {
    }

    public static RemoteWebDriver getInstance() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

            System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
        }
        return driver;
    }
}
