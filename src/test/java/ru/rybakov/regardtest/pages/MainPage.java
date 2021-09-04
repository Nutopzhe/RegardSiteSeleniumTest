package ru.rybakov.regardtest.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage extends AbstractPage {

    public MainPage() {
        super("https://www.regard.ru/");
    }

    public void choosingCategoryAndSubTypeOfProduct(String pathToProductCategory, String pathToProductSubType) {
        driver.findElement(By.xpath(String.format("//a[text()='%s']", pathToProductCategory))).click();
        driver.findElement(By.xpath(String.format("//li[@class='container    open']//a[text()='%s']", pathToProductSubType))).click();
//        //доп проверка, что мы перешли к подтипу товара
        Assertions.assertTrue(driver.getCurrentUrl().contains("catalog"));
    }

    public void addItemToShoppingCard(int itemNumber, List<String> shoppingList) {
        Assertions.assertNotNull(driver.findElement(By.xpath(String.format("//div[@class='block'][%d]", itemNumber))));
        WebElement buttonAddToCard = driver.findElement(By.xpath(String.format("//div[@class='block'][%d]//div[@class='price']//a", itemNumber)));

        WebElement itemName = driver.findElement(By.xpath(String.format("//div[@class='block'][%d]//div[@class='aheader']/a", itemNumber)));
        shoppingList.add(itemName.getText());
        buttonAddToCard.click();
    }

    public void openPageItem(int itemNumber) {
        //доп проверка, что передаваемое число элемента есть на странице
        List<WebElement> countProducts = driver.findElements(By.xpath("//div[@class='content']/div[@class='block']"));
        Assertions.assertTrue(itemNumber <= countProducts.size());

        WebElement itemPage = driver.findElement(By.xpath(String.format("//div[@class='block'][%d]//a[@class='header']", itemNumber)));
        try{
            itemPage.click();
        } catch (WebDriverException e){
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", itemPage);
            itemPage.click();
        }
    }

    public void clickOnPersonalAccountTabAndGoToRegistration() {
        driver.findElement(By.xpath("//span[@class='login']")).click();
        WebElement registrationTab = driver.findElementByXPath("//*[@id='persona_regShowButton']");
        registrationTab.click();
        Assertions.assertEquals(registrationTab.getAttribute("class"), "persona_tab activeTab");
    }

    public void setLoginAndPassword(String login, String password) {
        WebElement loginField = driver.findElementByXPath("//*[@id='username']");
        WebElement passwordField = driver.findElementByXPath("//*[@id='new_password1']");

        loginField.sendKeys(login);
        passwordField.sendKeys(password);
    }

    public void clickRegistrationButton() {
        driver.findElementByXPath("//*[@id='persona_regButton']").click();
    }

    public void checkingThatLoginIsInvalid() {
        String invalidMessage = driver.findElementByXPath("//*[@id='regORauth-log']").getText();
        Assertions.assertTrue(invalidMessage.contains("Недопустимый логин"));
    }

    public void openConfigurationPCMenu() {
        driver.findElementByXPath("//a[text()='Конфигуратор ПК']").click();
        Assertions.assertTrue(driver.getCurrentUrl().contains("cfg"));
    }
}
