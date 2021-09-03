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
    public BasketPage basketPage;

    public MainPage() {
        super("https://www.regard.ru/");
        basketPage = new BasketPage();
    }

    public void choosingCategoryAndSubTypeOfProduct(String pathToProductCategory, String pathToProductSubType) {
        driver.findElement(By.xpath(String.format("//a[text()='%s']", pathToProductCategory))).click();
        driver.findElement(By.xpath(String.format("//li[@class='container    open']//a[text()='%s']", pathToProductSubType))).click();
//        //доп проверка, что мы перешли к подтипу товара
        Assertions.assertTrue(driver.getCurrentUrl().contains("catalog"));
    }

    public void addItemToShoppingCard(int itemNumber) {
        Assertions.assertNotNull(driver.findElement(By.xpath(String.format("//div[@class='block'][%d]", itemNumber))));
        WebElement buttonAddToCard = driver.findElement(By.xpath(String.format("//div[@class='block'][%d]//div[@class='price']//a", itemNumber)));

        WebElement itemName = driver.findElement(By.xpath(String.format("//div[@class='block'][%s]//div[@class='aheader']/a", itemNumber)));
        basketPage.products.add(itemName.getText());
        buttonAddToCard.click();
    }

    public void openPageItem(int itemNumber) {
        //доп проверка, что передаваемое число элемента есть на странице
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='content']/div[@class='block']"));
        Assertions.assertTrue(itemNumber <= elements.size());

        WebElement itemPage = driver.findElement(By.xpath(String.format("//div[@class='block'][%s]//a[@class='header']", itemNumber)));
        try{
            itemPage.click();
        }catch (WebDriverException e){
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", itemPage);
            itemPage.click();
            //или использовать явное нажатие
            //((JavascriptExecutor) driver).executeScript("arguments[0].click()", itemPage);
        }
    }

    public void addToCardFromProductPage() {
        WebElement buttonAddToCard = driver.findElement(By.xpath("//*[@id='add_cart']"));
        Assertions.assertEquals( "Добавить в корзину", buttonAddToCard.getText());

        WebElement itemName = driver.findElementByXPath("//*[@id='hits-long']//div[@class='goods_header']//span");
        basketPage.products.add(itemName.getAttribute("content"));
        buttonAddToCard.click();
    }

    public void moveToCardFromProductPage() {
        WebElement buttonMoveToCard = driver.findElement(By.xpath("//*[@id='add_cart']"));
        //явно ждём, чтобы текст у элемента успел смениться
        new WebDriverWait(driver, 5).until(ExpectedConditions.textToBePresentInElement(buttonMoveToCard, "Перейти в корзину"));
        Assertions.assertEquals("Перейти в корзину", buttonMoveToCard.getText());
        buttonMoveToCard.click();
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
}
