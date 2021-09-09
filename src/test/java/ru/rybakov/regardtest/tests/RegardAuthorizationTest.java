package ru.rybakov.regardtest.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class RegardAuthorizationTest extends BaseTest {

    @ParameterizedTest(name = "#{index} - test with {0} login")
    @CsvFileSource(resources = "/data/invalidLogin.csv", numLinesToSkip = 1)
    void testRegistrationWithInvalidLogin(String login, String password) {
        mainPage.open();
        mainPage.clickOnPersonalAccountTabAndGoToRegistration();
        mainPage.setLoginAndPassword(login, password);
        mainPage.clickRegistrationButton();
        mainPage.checkingThatLoginIsInvalid();
    }
}
