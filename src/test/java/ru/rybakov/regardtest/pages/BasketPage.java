package ru.rybakov.regardtest.pages;

import java.util.ArrayList;
import java.util.List;

public class BasketPage extends AbstractPage {
    private static BasketPage basketPage;
    public List<String> products = new ArrayList<>();

    private BasketPage() {
        super("https://www.regard.ru/basket/");
    }

    public static BasketPage getInstance() {
        if (basketPage == null) {
            basketPage = new BasketPage();
        }
        return basketPage;
    }
}
