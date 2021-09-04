package ru.rybakov.regardtest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.rybakov.regardtest.pages.*;
import ru.rybakov.regardtest.utils.WebDriverWrapper;

import java.util.ArrayList;
import java.util.List;

public class TestRegard {
    List<String> shoppingList = new ArrayList<>();

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

        BasketPage basketPage = new BasketPage();
        basketPage.productsIsNotEmpty();
        basketPage.comparisonOfProductName(shoppingList);
        //Удаляем содержимое корзины для дальнейших тестов
        basketPage.clearBasket();
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

        BasketPage basketPage = new BasketPage();
        basketPage.productsIsNotEmpty();
        basketPage.comparisonOfProductName(shoppingList);
        basketPage.clearBasket();
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

        BasketPage basketPage = new BasketPage();
        basketPage.productsIsNotEmpty();
        basketPage.comparisonOfProductName(shoppingList);
        basketPage.deleteProductFromShoppingCard(shoppingList, 4);
        basketPage.comparisonOfProductName(shoppingList);
        basketPage.deleteProductFromShoppingCard(shoppingList, 1);
        basketPage.comparisonOfProductName(shoppingList);
        basketPage.clearBasket();
    }

    @ParameterizedTest(name = "#{index} - test with {0} login")
    @CsvFileSource(resources = "/invalidLogin.csv", numLinesToSkip = 1)
    void testRegistrationWithInvalidLogin(String login, String password) {
        MainPage mainPage = new MainPage();
        mainPage.open();
        mainPage.clickOnPersonalAccountTabAndGoToRegistration();
        mainPage.setLoginAndPassword(login, password);
        mainPage.clickRegistrationButton();
        mainPage.checkingThatLoginIsInvalid();
    }

    @AfterEach
    void afterEach() {
        shoppingList.clear();
    }

    @AfterAll
    static void afterAll(){
        WebDriverWrapper.getInstance().quit();
    }
}
