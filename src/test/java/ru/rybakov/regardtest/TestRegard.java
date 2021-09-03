package ru.rybakov.regardtest;

import org.junit.jupiter.api.Test;
import ru.rybakov.regardtest.pages.BasketPage;
import ru.rybakov.regardtest.pages.MainPage;

/*1. Открыть главную страницу
2. В левом боковом меню выбрать категорию материнские платы - в подменю выбрать Intel socket 1200
3. Найти 5-ю в списке материнскую плату, положить ее в корзину (маленькая круглая красная кнопочка около товара).
4. В левом боковом меню выбрать категорию корпуса - в подменю выбрать Aerocool
5. Найти 4-й в списке корпус, положить ее в корзину (маленькая круглая красная кнопочка около товара).
6. Найти 10-й в списке корпус, кликнуть на ссылку-название (откроется страница товара).
7. Нажать на красную кнопку "Добавить в корзину" справа от товара.
8. Нажать на голубую кнопку "Перейти в корзину".
9. Убедиться, что корзина не пустая, и что в ней содержаться ровно те наименования товаров, которые вы покупали.
 */

public class TestRegard {

    @Test()
    void testAddingProductsToCard() {
        //*1. Открыть главную страницу
        MainPage mainPage = new MainPage();
        mainPage.open();
        //2. В левом боковом меню выбрать категорию материнские платы - в подменю выбрать Intel socket 1200
        mainPage.choosingCategoryAndSubTypeOfProduct("Материнские платы", "Intel Socket 1200");
        //3. Найти 5-ю в списке материнскую плату, положить ее в корзину (маленькая круглая красная кнопочка около товара).
        mainPage.addItemToShoppingCard(5);
        //4. В левом боковом меню выбрать категорию корпуса - в подменю выбрать Aerocool
        mainPage.choosingCategoryAndSubTypeOfProduct("Корпуса", "AEROCOOL");
        //5. Найти 4-й в списке корпус, положить ее в корзину (маленькая круглая красная кнопочка около товара).
        mainPage.addItemToShoppingCard(4);
        //6. Найти 10-й в списке корпус, кликнуть на ссылку-название (откроется страница товара).
        mainPage.openPageItem(10);
        //7. Нажать на красную кнопку "Добавить в корзину" справа от товара.
        mainPage.addToCardFromProductPage();
        //8. Нажать на голубую кнопку "Перейти в корзину".
        mainPage.moveToCardFromProductPage();
        //9. Убедиться, что корзина не пустая, и что в ней содержаться ровно те наименования товаров, которые вы покупали.
        BasketPage basketPage = new BasketPage();
        basketPage.productsIsNotEmpty();
        basketPage.comparisonOfProductName(mainPage.basketPage.products);
    }
}
