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
public class LoginPageTest {
    private String baseUrl = "http://localhost:7400/";

    @Autowired
    private WebDriver driver;

    @Before
    public void setUp(){
        driver.get(baseUrl);
    }

    @Test
    public void loginWithoutErrorTest() {
        new HomePage(driver).clickForAdminButton().setLogin("admin").setPassword("adminpwd").submit();
    }

    @Test
    public void loginWithErrorTest() {
        new HomePage(driver).clickForAdminButton().setLogin("admine").setPassword("adminpwd").submitWithError()
                .checkErrorMsg("Wrong login or password.");
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
