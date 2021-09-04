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
    }

    //очищаем products от всех элементов
    private void removingProductsFromBasket() {
        products.clear();
    }

    public void productsIsNotEmpty() {
        fillingBasketWithProducts();
        Assertions.assertFalse(products.isEmpty());
        removingProductsFromBasket();
    }

    public void comparisonOfProductName(List<String> comparedProducts) {
        fillingBasketWithProducts();
        Assertions.assertIterableEquals(products, comparedProducts, "В корзинах есть различия!");
        removingProductsFromBasket();
    }

    public void clearBasket() {
        driver.findElement(By.xpath("//a[@title='Очистить корзину']")).click();
        driver.switchTo().alert().accept();
    }

    public void deleteProductFromShoppingCard(List<String> shoppingList, int itemNumber) {
        driver.findElement(By.xpath(String.format("//tbody//tr[@data-groupid][%d]//a[@title='Удалить товар из корзины']", itemNumber))).click();
        Assertions.assertTrue(shoppingList.size() >= itemNumber, "Передаваемого номера элемента нет в корзине!");
        itemNumber--;
        shoppingList.remove(itemNumber);
    }
}
