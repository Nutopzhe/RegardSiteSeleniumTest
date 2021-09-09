package ru.rybakov.regardtest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.rybakov.regardtest.pages.MainPage;
import ru.rybakov.regardtest.utils.WebDriverWrapper;

public class RegardAuthorizationTest {

    @ParameterizedTest(name = "#{index} - test with {0} login")
    @CsvFileSource(resources = "/data/invalidLogin.csv", numLinesToSkip = 1)
    void testRegistrationWithInvalidLogin(String login, String password) {
        MainPage mainPage = new MainPage();
        mainPage.open();
        mainPage.clickOnPersonalAccountTabAndGoToRegistration();
        mainPage.setLoginAndPassword(login, password);
        mainPage.clickRegistrationButton();
        mainPage.checkingThatLoginIsInvalid();
    }

//    @AfterAll
//    static void afterAll(){
//        WebDriverWrapper.getInstance().quit();
//    }
}
