package ru.rybakov.regardtest.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasketPage extends AbstractPage {
    public List<String> products = new ArrayList<>();
    WebDriverWait wait = new WebDriverWait(driver, 5, 250);

    public BasketPage() {
        super("https://www.regard.ru/basket/");
    }

    //наполняем лист products класса BasketPage продуктами, которые есть на странице
    private void fillingBasketWithProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[@class='goods_line']//a[@class='underline']")));
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
        driver.findElementByXPath("//a[@title='Очистить корзину']").click();
        driver.switchTo().alert().accept();
    }

    public void deleteProductFromShoppingCard(List<String> shoppingList, int itemNumber) {
        driver.findElementByXPath(String.format("//tbody//tr[@data-groupid][%d]//a[@title='Удалить товар из корзины']", itemNumber)).click();
        Assertions.assertTrue(shoppingList.size() >= itemNumber, "Передаваемого номера элемента нет в корзине!");
        itemNumber--;
        shoppingList.remove(itemNumber);
    }
}
