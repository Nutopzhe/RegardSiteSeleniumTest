package ru.rybakov.regardtest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.rybakov.regardtest.pages.BasketPage;
import ru.rybakov.regardtest.pages.ConfigPCPage;
import ru.rybakov.regardtest.pages.MainPage;
import ru.rybakov.regardtest.pages.ProductPage;

import java.util.ArrayList;
import java.util.List;

/*
1. Открыть главную страницу
2. В левом боковом меню выбрать категорию материнские платы - в подменю выбрать Intel socket 1200
3. Найти 5-ю в списке материнскую плату, положить ее в корзину (маленькая круглая красная кнопочка около товара).
4. В левом боковом меню выбрать категорию корпуса - в подменю выбрать Aerocool
5. Найти 4-й в списке корпус, положить ее в корзину (маленькая круглая красная кнопочка около товара).
6. Найти 10-й в списке корпус, кликнуть на ссылку-название (откроется страница товара).
7. Нажать на красную кнопку "Добавить в корзину" справа от товара.
8. Нажать на голубую кнопку "Перейти в корзину".
9. Убедиться, что корзина не пустая, и что в ней содержаться ровно те наименования товаров, которые вы покупали.
*/

/*
1. Открыть главную страницу
2. В верхнем блоке выбрать конфигуратор пк
3. Убедиться что открылась страница конфигуратора
4. Нажать на кнопку "корпус"
5. Из всех предложенных корпусов выбрать 50, перед этим проверить что их больше 50
6. Нажать на кнопку "процессор"
7. Выбрать 100, с проверкой
8. Нажать на кнопку "видеокарта"
9. Выбрать 666
10. Нажать на красную кнопку "оформить заказ"
11. Убедиться, что в корзине содержаться те товары, которые были выбраны
*/

/*
1. Открыть главную страницу
2. Слева выбрать категорию "Видеокарты" - в подменю выбрать NVIDIA GEFORCE
3. Найти 1-ю видеокарту, положить в корзину (маленькая круглая красная кнопочка около товара).
4. Найти 2-ю видеокарту, положить в корзину (маленькая круглая красная кнопочка около товара).
5. Найти 3-ю видеокарту, положить в корзину (маленькая круглая красная кнопочка около товара).
6. Найти 4-ю видеокарту, положить в корзину (маленькая круглая красная кнопочка около товара).
7. Перейти к оформлению заказа. (Нажать на блок корзины справа вверху)
8. Проверить наличие товаров на соответствие добаленных товаров
9. Удалить последний добавленный элемент
10. Проверить наличие товаров в корзине на соответствие имеющихся товаров
11. Удалить первый добаленный товар и также проверить
*/

/*
1. Открыть главную страницу
2. Нажать на вкладку личный кaбинет
3. Перейти на вкладку "регистрация"
4. В поле "логин/email" ввести один из предложенных параметров
5. В поле "пароль" ввести 123456789
6. Нажать на красную кнопку "РЕГИСТРАЦИЯ"
7. Проверить, что логин недопустим
*/

public class TestRegard {
    List<String> shoppingList = new ArrayList<>();

    @Test()
    void testAddingProductsToCard() {
        //*1. Открыть главную страницу
        MainPage mainPage = new MainPage();
        mainPage.open();

        //2. В левом боковом меню выбрать категорию материнские платы - в подменю выбрать Intel socket 1200
        mainPage.choosingCategoryAndSubTypeOfProduct("Материнские платы", "Intel Socket 1200");

        //3. Найти 5-ю в списке материнскую плату, положить ее в корзину (маленькая круглая красная кнопочка около товара).
        mainPage.addItemToShoppingCard(5, shoppingList);

        //4. В левом боковом меню выбрать категорию корпуса - в подменю выбрать Aerocool
        mainPage.choosingCategoryAndSubTypeOfProduct("Корпуса", "AEROCOOL");

        //5. Найти 4-й в списке корпус, положить ее в корзину (маленькая круглая красная кнопочка около товара).
        mainPage.addItemToShoppingCard(4, shoppingList);

        //6. Найти 10-й в списке корпус, кликнуть на ссылку-название (откроется страница товара).
        mainPage.openPageItem(10);

        //7. Нажать на красную кнопку "Добавить в корзину" справа от товара.
        ProductPage productPage = new ProductPage();
        productPage.addToCardFromProductPage(shoppingList);

        //8. Нажать на голубую кнопку "Перейти в корзину".
        productPage.moveToCardFromProductPage();

        //9. Убедиться, что корзина не пустая, и что в ней содержаться ровно те наименования товаров, которые вы покупали.
        BasketPage basketPage = new BasketPage();
        basketPage.productsIsNotEmpty();
        basketPage.comparisonOfProductName(shoppingList);

        basketPage.clearBasket();
    }

    @Test
    void testAddingProductsToCardFromConfigPC() {
        //1. Открыть главную страницу
        MainPage mainPage = new MainPage();
        mainPage.open();

        //2. В верхнем блоке выбрать конфигуратор пк
        //3. Убедиться что открылась страница конфигуратора
        mainPage.openConfigurationPCMenu();

        //4. Нажать на кнопку "корпус"
        ConfigPCPage configPCPage = new ConfigPCPage();
        configPCPage.selectBlock("Корпус");

        //5. Из всех предложенных корпусов выбрать 50, перед этим проверить что их больше 50
        configPCPage.selectItem(50, shoppingList);

        //6. Нажать на кнопку "процессор"
        configPCPage.selectBlock("Процессор");

        //7. Выбрать 100, с проверкой
        configPCPage.selectItem(100, shoppingList);

        //8. Нажать на кнопку "видеокарта"
        configPCPage.selectBlock("Видеокарта");

        //9. Выбрать 20
        configPCPage.selectItem(20, shoppingList);

        //10. Нажать на красную кнопку "оформить заказ"
        configPCPage.placeAnOrder();

        //11. Убедиться, что в корзине содержаться те товары, которые были выбраны
        BasketPage basketPage = new BasketPage();
        basketPage.productsIsNotEmpty();
        basketPage.comparisonOfProductName(shoppingList);

        basketPage.clearBasket();
    }

    @ParameterizedTest(name = "#{index} - test with {0} login")
    @CsvFileSource(resources = "/invalidLogin.csv", numLinesToSkip = 1)
    void testRegistrationWithInvalidLogin(String login, String password) {
        //1. Открыть главную страницу
        MainPage mainPage = new MainPage();
        mainPage.open();

        //2. Нажать на вкладку личный кабинет
        //3. Перейти на вкладку "регистрация"
        mainPage.clickOnPersonalAccountTabAndGoToRegistration();

        //4. В поле "логин/email" ввести один из предложенных параметров
        //5. В поле "пароль" ввести 123456789
        mainPage.setLoginAndPassword(login, password);

        //6. Нажать на красную кнопку "РЕГИСТРАЦИЯ"
        mainPage.clickRegistrationButton();

        //7. Проверить, что логин недопустим
        mainPage.checkingThatLoginIsInvalid();
    }

    @AfterEach
    void afterEach() {
        shoppingList.clear();
    }

//    @AfterAll
//    static void afterAll(){
//        DriverWrapper.getInstance().quit();
//    }
}
