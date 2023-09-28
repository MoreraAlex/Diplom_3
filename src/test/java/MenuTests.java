import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_object.MainPage;

import static driver.WebDriverCreator.createWebDriver;

public class MenuTests {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = createWebDriver();

        mainPage = new MainPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @DisplayName("Переход к части меню 'Соусы'")
    @Test
    public void moveToSauceTab() {
        mainPage.waitAndClickConstructorSauceTab();
        mainPage.waitForConstructorSauceTabVisibility();
        String er = mainPage.checkForMenuSauceBlockActivation().getText();
        mainPage.waitForConstructorSauceTabVisibility();
        Assert.assertTrue(er.equals("Соусы"));
    }

    @DisplayName("Переход к части меню 'Булки'")
    @Test
    public void moveToBunsTab() {
        mainPage.waitAndClickConstructorSauceTab();
        mainPage.waitAndClickConstructorBunsTab();
        mainPage.waitForConstructorBunsTabVisibility();
        String er = mainPage.checkForMenuBunsBlockActivation().getText();
        mainPage.waitForConstructorBunsTabVisibility();
        Assert.assertTrue(er.equals("Булки"));
    }

    @DisplayName("Переход к части меню 'Начинки'")
    @Test
    public void moveToFillingsTab() {
        mainPage.waitAndClickConstructorFillingsTab();
        mainPage.waitForConstructorFillingsTabVisibility();
        String er = mainPage.checkForMenuFillingsBlockActivation().getText();
        mainPage.waitForConstructorFillingsTabVisibility();
        Assert.assertTrue(er.equals("Начинки"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
