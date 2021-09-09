package ru.rybakov.regardtest.tests;

import ru.rybakov.regardtest.pages.BasketPage;
import ru.rybakov.regardtest.pages.ConfigPCPage;
import ru.rybakov.regardtest.pages.MainPage;
import ru.rybakov.regardtest.pages.ProductPage;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTest {

    protected List<String> shoppingList = new ArrayList<>();

    protected MainPage mainPage;
    protected BasketPage basketPage;
    protected ConfigPCPage configPCPage;
    protected ProductPage productPage;

    public BaseTest() {
        mainPage = new MainPage();
        basketPage = new BasketPage();
        configPCPage = new ConfigPCPage();
        productPage = new ProductPage();
    }
}
