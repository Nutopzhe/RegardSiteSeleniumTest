package ru.rybakov.regardtest.cucumbers;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.rybakov.regardtest.pages.BasketPage;
import ru.rybakov.regardtest.pages.ConfigPCPage;
import ru.rybakov.regardtest.pages.MainPage;
import ru.rybakov.regardtest.pages.ProductPage;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCardStepDef {
    List<String> shoppingList = new ArrayList<>();

    MainPage mainPage = new MainPage();
    ProductPage productPage = new ProductPage();
    BasketPage basketPage = new BasketPage();
    ConfigPCPage configPCPage = new ConfigPCPage();

    @Given("user is on main page")
    public void user_is_on_main_page() {
        mainPage.open();
    }

    @When("in the left side menu, the user selected {string} and the subcategory {string}")
    public void in_the_left_side_menu_the_user_selected_and_the_subcategory(String nameCategory, String nameSubType) {
        mainPage.choosingCategoryAndSubTypeOfProduct(nameCategory, nameSubType);
    }

    @Then("user selected {int} position from the list and put it in the basket")
    public void user_selected_products_in_the_list_and_put_it_in_the_basket(int itemNumber) {
        mainPage.addItemToShoppingCard(itemNumber, shoppingList);
    }

    @Then("user selected {int} products in the list and clicked on the link-name")
    public void user_selected_products_in_the_list_and_clicked_on_the_link_name(int itemNumber) {
        mainPage.openProductPage(itemNumber);
    }

    @When("user clicked on the red Add to cart button")
    public void user_clicked_on_the_red_add_to_cart_button() {
        productPage.addToCardFromProductPage(shoppingList);
    }

    @When("clicked on the blue button Go to cart")
    public void clicked_on_the_blue_button_go_to_cart() {
        productPage.moveToCardFromProductPage();
    }

    @Then("make sure that the basket is not empty and it contains the same products")
    public void make_sure_that_the_basket_is_not_empty_and_it_contains_the_same_products() {
        basketPage.productsIsNotEmpty();
        basketPage.comparisonOfProductName(shoppingList);
    }

    @When("in the upper block, select the pc configurator")
    public void in_the_upper_block_select_the_pc_configurator() {
        mainPage.openConfigurationPCMenu();
    }

    @When("click on the button {string}")
    public void click_on_the_button(String blockName) {
        configPCPage.selectBlock(blockName);
    }

    @Then("user selected {int} products and clicked on it")
    public void user_selected_products_and_clicked_on_it(int itemNumber) {
        configPCPage.selectItem(itemNumber, shoppingList);
    }

    @When("user clicked on the red checkout button")
    public void user_clicked_on_the_red_checkout_button() {
        configPCPage.placeAnOrder();
    }

    @When("user clicked on the basket from the top right")
    public void user_clicked_on_the_basket_from_the_top_right() {
        mainPage.goToShoppingCard();
    }

    @When("delete item with index {int}")
    public void delete_item_with_index(int itemNumber) {
        basketPage.deleteProductFromShoppingCard(shoppingList, itemNumber);
    }

    //узнать как вызвать метод после одного сценария
    @After
    public void afterEach() {
        shoppingList.clear();
        basketPage.clearBasket();
    }
}
