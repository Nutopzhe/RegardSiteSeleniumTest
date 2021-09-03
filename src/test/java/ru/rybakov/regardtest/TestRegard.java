package ru.rybakov.regardtest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.rybakov.regardtest.pages.BasketPage;
import ru.rybakov.regardtest.pages.MainPage;
import ru.rybakov.regardtest.utils.DriverWrapper;

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
2. Нажать на вкладку личный кaбинет
3. Перейти на вкладку "регистрация"
4. В поле "логин/email" ввести один из предложенных параметров
5. В поле "пароль" ввести 123456789
6. Нажать на красную кнопку "РЕГИСТРАЦИЯ"
7. Проверить, что логин недопустим
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

    @ParameterizedTest(name = "#{index} - test with {0} login")
    @CsvFileSource(resources = "/invalidLogin.csv", numLinesToSkip = 1)
    void registrationWithInvalidLogin(String login, String password) {
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

    @AfterAll
    static void ternDown(){
        DriverWrapper.getInstance().quit();
    }
}
