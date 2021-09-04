package ru.rybakov.regardtest.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BasketPage extends AbstractPage {
    public List<String> products = new ArrayList<>();

    public BasketPage() {
        super("https://www.regard.ru/basket/");
    }

    //наполняем лист products класса BasketPage продуктами, которые есть на странице
    private void fillingBasketWithProducts() {
        List<WebElement> elements = driver.findElements(By.xpath("//tr[@class='goods_line']//a[@class='underline']"));
        for (WebElement element : elements) {
            products.add(element.getText());
        }
        log.debug("Список продуктов был заполнен названиями элементов, которые находятся на странице корзины!");
    }

    //очищаем products от всех элементов
    private void removingProductsFromBasket() {
        products.clear();
        log.debug("Список продуктов очищен");
    }

    public void productsIsNotEmpty() {
        fillingBasketWithProducts();
        Assertions.assertFalse(products.isEmpty(), "Список продуктов пуст!");
        removingProductsFromBasket();
    }

    public void comparisonOfProductName(List<String> comparedProducts) {
        fillingBasketWithProducts();
        Assertions.assertIterableEquals(products, comparedProducts, "В корзине и списке продуктов есть различия!");
        removingProductsFromBasket();
    }

    public void clearBasket() {
        driver.findElement(By.xpath("//a[@title='Очистить корзину']")).click();
        driver.switchTo().alert().accept();
        log.debug("Корзина на сайте успешно очищена!");
    }

    public void deleteProductFromShoppingCard(List<String> shoppingList, int itemNumber) {
        driver.findElement(By.xpath(String.format("//tbody//tr[@data-groupid][%d]//a[@title='Удалить товар из корзины']", itemNumber))).click();
        Assertions.assertTrue(shoppingList.size() >= itemNumber, "Передаваемого номера элемента нет в корзине!");
        itemNumber--;
        shoppingList.remove(itemNumber);
        log.debug("Продукт по номеру '{}' был успешно удален из корзины на сайте", itemNumber);
    }
}
