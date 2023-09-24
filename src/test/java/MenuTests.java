import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object.MainPage;

public class MenuTests {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();

        mainPage = new MainPage(driver);
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @DisplayName("Переход к части меню 'Соусы'")
    @Test
    public void moveToSauceTab()
    {
        mainPage.waitAndClickConstructorSauceTab();
        mainPage.waitForMenuSauceBlockVisibility();
    }

    @DisplayName("Переход к части меню 'Булки'")
    @Test
    public void moveToBunsTab()
    {
        mainPage.waitAndClickConstructorSauceTab();
        mainPage.waitAndClickConstructorBunsTab();
        mainPage.waitForMenuBunsBlockVisibility();
    }

    @DisplayName("Переход к части меню 'Начинки'")
    @Test
    public void moveToFillingsTab()
    {
        mainPage.waitAndClickConstructorSauceTab();
        mainPage.waitForMenuFillingsBlockVisibility();
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }


}
