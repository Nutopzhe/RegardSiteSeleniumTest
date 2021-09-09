package ru.rybakov.regardtest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.rybakov.regardtest.pages.*;
import ru.rybakov.regardtest.utils.WebDriverWrapper;

import java.util.ArrayList;
import java.util.List;

public class RegardShoppingCardTest {
    List<String> shoppingList = new ArrayList<>();
    BasketPage basketPage = new BasketPage();

    @Test()
    void testAddingProductsToCard() {
        MainPage mainPage = new MainPage();
        mainPage.open();
        mainPage.choosingCategoryAndSubTypeOfProduct("Материнские платы", "Intel Socket 1200");
        mainPage.addItemToShoppingCard(5, shoppingList);
        mainPage.choosingCategoryAndSubTypeOfProduct("Корпуса", "AEROCOOL");
        mainPage.addItemToShoppingCard(4, shoppingList);
        mainPage.openProductPage(10);

        ProductPage productPage = new ProductPage();
        productPage.addToCardFromProductPage(shoppingList);
        productPage.moveToCardFromProductPage();

        basketPage.productsIsNotEmpty();
        basketPage.comparisonOfProductName(shoppingList);
    }

    @Test
    void testAddingProductsToCardFromConfigPC() {
        MainPage mainPage = new MainPage();
        mainPage.open();
        mainPage.openConfigurationPCMenu();

        ConfigPCPage configPCPage = new ConfigPCPage();
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
        MainPage mainPage = new MainPage();
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

//    @AfterAll
//    static void afterAll(){
//        WebDriverWrapper.getInstance().quit();
//    }
}
