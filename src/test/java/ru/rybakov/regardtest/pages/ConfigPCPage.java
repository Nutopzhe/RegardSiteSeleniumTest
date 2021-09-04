package ru.rybakov.regardtest.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ConfigPCPage extends AbstractPage{
    WebDriverWait wait = new WebDriverWait(driver, 5, 250);

    String buttonPlaceAnOrder = "//button[text()='Оформить заказ']";

    public ConfigPCPage() {
        super("https://www.regard.ru/cfg");
    }

    public void selectBlock(String blockName) {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format("//div[text()='%s']", blockName))));
        driver.findElementByXPath(String.format("//div[text()='%s']", blockName)).click();
    }

    public void selectItem(int itemNumber, List<String> shoppingList) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='cfg-ajax']/table")));
        List<WebElement> items = driver.findElements(By.xpath("//*[@id='cfg-ajax']/table"));
        Assertions.assertTrue(items.size() >= itemNumber);

        WebElement elementByXPath = driver.findElementByXPath(String.format("//table[%d]//a[@class='header title']", itemNumber));
        shoppingList.add(elementByXPath.getText());
        itemNumber--;
        items.get(itemNumber).click();
    }

    public void placeAnOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonPlaceAnOrder)));
        WebElement buttonPlaceOrder = driver.findElementByXPath(buttonPlaceAnOrder);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", buttonPlaceOrder);
    }
}
