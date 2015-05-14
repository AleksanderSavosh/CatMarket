package savosh.catmarket.selenium.pages;


import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class ThankForBuyPage {
    private final WebDriver driver;

    public ThankForBuyPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        if (!"Thank for buy".equals(driver.getTitle())) {
            throw new IllegalStateException("This is not the thank for buy page");
        }
    }


}
