package ru.rybakov.regardtest.cucumbers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ru.rybakov.regardtest.pages.MainPage;

public class AuthorizationStepDef {
    MainPage mainPage = new MainPage();

    @Given("user in the main page")
    public void user_in_the_main_page() {
        mainPage.open();
    }

    @When("clicked on the personal account tab and went to the registration tab")
    public void clicked_on_the_personal_account_tab_and_went_to_the_registration_tab() {
        mainPage.clickOnPersonalAccountTabAndGoToRegistration();
    }

    @When("in the login field enter {string} and in the password field enter {string}")
    public void in_the_login_field_enter_and_in_the_password_field_enter(String login, String password) {
        mainPage.setLoginAndPassword(login, password);
    }

    @Then("user clicked red button registration")
    public void user_clicked_red_button_registration() {
        mainPage.clickRegistrationButton();
    }

    @Then("check that the login is unavailable")
    public void check_that_the_login_is_unavailable() {
        mainPage.checkingThatLoginIsInvalid();
    }
}
