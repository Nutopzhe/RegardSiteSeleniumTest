package ru.rybakov.regardtest.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage extends AbstractPage{
    public ProductPage() {
        super("https://www.regard.ru/catalog/tovar");
    }

    public void addToCardFromProductPage(List<String> shoppingList) {
        WebElement buttonAddToCard = driver.findElement(By.xpath("//*[@id='add_cart']"));
        Assertions.assertEquals( "Добавить в корзину", buttonAddToCard.getText(), "Элемент не является кнопкой добавления в корзину!");

        WebElement itemName = driver.findElement(By.xpath("//*[@id='hits-long']//div[@class='goods_header']//span"));
        shoppingList.add(itemName.getAttribute("content"));
        buttonAddToCard.click();
        log.debug("Продукт, со страницы продукта, был успешно добавлен в корзину на сайте!");
    }

    public void moveToCardFromProductPage() {
        WebElement buttonMoveToCard = driver.findElement(By.xpath("//*[@id='add_cart']"));
        //явно ждём, чтобы текст у элемента успел смениться
        driver.waitTextToBePresentInElement(buttonMoveToCard, "Перейти в корзину");
        Assertions.assertEquals("Перейти в корзину", buttonMoveToCard.getText(), "Элемент не является кнопкой перехода в корзину!");
        buttonMoveToCard.click();
        log.debug("Успешно перешли в корзину со страницы продукта!");
    }
}
