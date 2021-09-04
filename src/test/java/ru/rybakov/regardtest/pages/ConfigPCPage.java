package ru.rybakov.regardtest.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import java.util.List;

public class ConfigPCPage extends AbstractPage{
    String buttonPlaceAnOrder = "//button[text()='Оформить заказ']";

    public ConfigPCPage() {
        super("https://www.regard.ru/cfg");
    }

    public void selectBlock(String blockName) {
        driver.waitElementToBeClickable(String.format("//div[text()='%s']", blockName));
        driver.findElement(By.xpath(String.format("//div[text()='%s']", blockName))).click();
    }

    public void selectItem(int itemNumber, List<String> shoppingList) {
        List<WebElement> items = driver.findElements(By.xpath("//*[@id='cfg-ajax']/table"));
        Assertions.assertTrue(items.size() >= itemNumber);

        WebElement elementByXPath = driver.findElement(By.xpath(String.format("//table[%d]//a[@class='header title']", itemNumber)));
        shoppingList.add(elementByXPath.getText());
        itemNumber--;
        items.get(itemNumber).click();
        log.debug("Продукт под номером '{}' был успешно добавлен в конфигурацию на странице '{}'", itemNumber, baseUrl);
    }

    public void placeAnOrder() {
        driver.waitElementToBeClickable(buttonPlaceAnOrder);
        WebElement buttonPlaceOrder = driver.findElement(By.xpath(buttonPlaceAnOrder));
        buttonPlaceOrder.click();
        log.debug("Успешный переход на страницу оформления заказа");
    }
}
