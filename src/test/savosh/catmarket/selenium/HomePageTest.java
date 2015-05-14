package savosh.catmarket.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import savosh.catmarket.selenium.pages.HomePage;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/selenium-context.xml"})
public class HomePageTest {
    private String baseUrl = "http://localhost:7400/";

    @Autowired
    private WebDriver driver;

    @Before
    public void setUp(){
        driver.get(baseUrl);
    }

    @Test
    public void goToUserCatalogTestCase() {
        new HomePage(driver).clickToCatalogButton();
    }

    @Test
    public void goToAdminLoginFormTestCase() {
        new HomePage(driver).clickForAdminButton();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
