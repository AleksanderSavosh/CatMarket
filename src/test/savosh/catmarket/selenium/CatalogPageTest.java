package savosh.catmarket.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import savosh.catmarket.selenium.pages.CatalogPage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/selenium-context.xml"})
public class CatalogPageTest {

    private String userUrl = "http://localhost:7400/catalog";

    @Autowired
    private WebDriver driver;

    @Before
    public void setUp(){
        driver.get(userUrl);
    }

    @Test
    public void addItemsToCardTestCase(){
        new CatalogPage(driver).addItemsInCard().goToCard();
    }


    @After
    public void closeBrowser() {
        driver.quit();
    }
}
