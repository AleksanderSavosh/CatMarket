package savosh.catmarket.selenium.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CardPage {

    private final WebDriver driver;

    public CardPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        if (!"Card".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the card page");
        }
    }

    public CardPage(WebDriver driver, int itemCount) {
        this(driver);
        List<WebElement> elements = driver.findElements(By.cssSelector("input.itemCount"));
        for(WebElement element : elements){
            itemCount -= Integer.valueOf(element.getAttribute("value"));
        }

        if(itemCount != 0){
            throw new IllegalStateException("Count in card doesn't match with count of added elements");
        }
    }

    public CardPage(WebDriver driver, String errorMessage) {
        this(driver);
        if(!driver.findElement(errorMessageLocation).getText().equalsIgnoreCase(errorMessage)){
            throw new IllegalStateException("Wrong error");
        }
    }

    By fioInputLocation = By.id("fio");
    By emailInputLocation = By.id("email");
    By phoneInputLocation = By.id("phone");
    By deliveryAddressInputLocation = By.id("deliveryAddress");
    By paymentDescriptionInputLocation = By.id("paymentDescription");
    By errorMessageLocation = By.id("errorMess");
    By submitOrderButtonLocation = By.id("makeOrder");

    public CardPage setFio(String fio){
        driver.findElement(fioInputLocation).sendKeys(fio);
        return this;
    }

    public CardPage setEmail(String email){
        driver.findElement(emailInputLocation).sendKeys(email);
        return this;
    }

    public CardPage setPhone(String phone){
        driver.findElement(phoneInputLocation).sendKeys(phone);
        return this;
    }

    public CardPage setDeliveryAddress(String address){
        driver.findElement(deliveryAddressInputLocation).sendKeys(address);
        return this;
    }

    public CardPage setPaymentDescription(String description){
        driver.findElement(paymentDescriptionInputLocation).sendKeys(description);
        return this;
    }

    public CardPage getErrorOnSubmitOrder(String errorMessage){
        driver.findElement(submitOrderButtonLocation).click();
        return new CardPage(driver, errorMessage);
    }

    public ThankForBuyPage submitOrder (){
        driver.findElement(submitOrderButtonLocation).click();
        return new ThankForBuyPage(driver);
    }
}
