package savosh.catmarket.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class HomePage {

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        if (!"Home page".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the home page");
        }
    }

    By forAdminButtonLocator = By.id("forAdmin");
    By toCatalogButtonLocator = By.id("toCatalog");

    public LoginPage clickForAdminButton() {
        driver.findElement(forAdminButtonLocator).click();
        return new LoginPage(driver);
    }

    public CatalogPage clickToCatalogButton() {
        driver.findElement(toCatalogButtonLocator).click();
        return new CatalogPage(driver);
    }

}
