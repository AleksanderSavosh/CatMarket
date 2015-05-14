package savosh.catmarket.selenium.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        if (!"Login page".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the login page");
        }
    }

    By loginLocator = By.id("login");
    By passwordLocator = By.id("pwd");
    By errorMsgLocator = By.cssSelector("div.alert.alert-warning");
    By submitBtnLocator = By.id("submit");

    public LoginPage setLogin(String login) {
        driver.findElement(loginLocator).sendKeys(login);
        return this;
    }

    public LoginPage setPassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public LoginPage submitWithError() {
        driver.findElement(submitBtnLocator).click();
        return new LoginPage(driver);
    }

    public LoginPage checkErrorMsg(String errorMsg) {
        if (!driver.findElement(errorMsgLocator).getText().equalsIgnoreCase(errorMsg)) {
            throw new IllegalStateException("Wrong error message.");
        }
        return this;
    }

    public CatalogPage submit() {
        driver.findElement(submitBtnLocator).click();
        return new CatalogPage(driver);
    }
}
