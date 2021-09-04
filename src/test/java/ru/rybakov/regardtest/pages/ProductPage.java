package ru.rybakov.regardtest.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage extends AbstractPage{
    public ProductPage() {
        super("https://www.regard.ru/catalog/tovar");
    }

    public void addToCardFromProductPage(List<String> shoppingList) {
        WebElement buttonAddToCard = driver.findElement(By.xpath("//*[@id='add_cart']"));
        Assertions.assertEquals( "Добавить в корзину", buttonAddToCard.getText());

        WebElement itemName = driver.findElementByXPath("//*[@id='hits-long']//div[@class='goods_header']//span");
        shoppingList.add(itemName.getAttribute("content"));
        buttonAddToCard.click();
    }

    public void moveToCardFromProductPage() {
        WebElement buttonMoveToCard = driver.findElement(By.xpath("//*[@id='add_cart']"));
        //явно ждём, чтобы текст у элемента успел смениться
        new WebDriverWait(driver, 5).until(ExpectedConditions.textToBePresentInElement(buttonMoveToCard, "Перейти в корзину"));
        Assertions.assertEquals("Перейти в корзину", buttonMoveToCard.getText());
        buttonMoveToCard.click();
    }
}
