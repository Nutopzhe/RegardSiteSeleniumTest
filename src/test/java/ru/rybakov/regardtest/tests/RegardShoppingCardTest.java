package ru.rybakov.regardtest.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class RegardShoppingCardTest extends BaseTest {

    @Test
    void testAddingProductsToCard() {
        mainPage.open();
        mainPage.choosingCategoryAndSubTypeOfProduct("Материнские платы", "Intel Socket 1200");
        mainPage.addItemToShoppingCard(5, shoppingList);
        mainPage.choosingCategoryAndSubTypeOfProduct("Корпуса", "AEROCOOL");
        mainPage.addItemToShoppingCard(4, shoppingList);
        mainPage.openProductPage(10);

        productPage.addToCardFromProductPage(shoppingList);
        productPage.moveToCardFromProductPage();

        basketPage.productsIsNotEmpty();
        basketPage.comparisonOfProductName(shoppingList);
    }

    @Test
    void testAddingProductsToCardFromConfigPC() {
        mainPage.open();
        mainPage.openConfigurationPCMenu();

        configPCPage.selectBlock("Корпус");
        configPCPage.selectItem(50, shoppingList);
        configPCPage.selectBlock("Процессор");
        configPCPage.selectItem(100, shoppingList);
        configPCPage.selectBlock("Видеокарта");
        configPCPage.selectItem(20, shoppingList);
        configPCPage.placeAnOrder();

        basketPage.productsIsNotEmpty();
        basketPage.comparisonOfProductName(shoppingList);
    }

    @Test
    void testDeletingProductsAndCheckingProductsInShoppingCard() {
        mainPage.open();
        mainPage.choosingCategoryAndSubTypeOfProduct("Видеокарты", "NVIDIA GeForce");
        mainPage.addItemToShoppingCard(1, shoppingList);
        mainPage.addItemToShoppingCard(2, shoppingList);
        mainPage.addItemToShoppingCard(3, shoppingList);
        mainPage.addItemToShoppingCard(4, shoppingList);
        mainPage.goToShoppingCard();

        basketPage.productsIsNotEmpty();
        basketPage.comparisonOfProductName(shoppingList);
        basketPage.deleteProductFromShoppingCard(shoppingList, 4);
        basketPage.comparisonOfProductName(shoppingList);
        basketPage.deleteProductFromShoppingCard(shoppingList, 1);
        basketPage.comparisonOfProductName(shoppingList);
    }

    @AfterEach
    void afterEach() {
        shoppingList.clear();
        basketPage.clearBasket();
    }
}
