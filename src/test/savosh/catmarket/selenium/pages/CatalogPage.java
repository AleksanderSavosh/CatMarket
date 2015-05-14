package savosh.catmarket.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CatalogPage {

    private final WebDriver driver;
    private int itemCount;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        if (!"Catalog".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the catalog page");
        }
    }

    By addInCardButtonsLocator = By.cssSelector("button.btn.btn-success");
    By countInCardLocator = By.id("count");
    By goToCardLocator = By.id("goToCard");

    public CatalogPage addItemsInCard(){
        List<WebElement> elements = driver.findElements(addInCardButtonsLocator);
        for(WebElement element : elements){
            element.click();
        }
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement element = driver.findElement(countInCardLocator);

        if(!Integer.valueOf(element.getText()).equals(elements.size())){
            throw new IllegalStateException("Number items in card doesn't match with number of added items");
        }
        itemCount = elements.size();
        return this;
    }

    public CardPage goToCard(){
        driver.findElement(goToCardLocator).click();
        return new CardPage(driver, itemCount);
    }

}
