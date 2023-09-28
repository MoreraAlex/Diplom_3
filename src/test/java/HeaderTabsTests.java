import Constructor.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_object.*;

import static driver.WebDriverCreator.createWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class HeaderTabsTests {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;
    private ProfilePage profilePage;

    String accessToken;
    String email = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
    String password = RandomStringUtils.randomAlphabetic(10);
    String name = RandomStringUtils.randomAlphabetic(10);

    User user = new User(email, password, name);

    @Before
    public void setUp() {
        driver = createWebDriver();

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        profilePage = new ProfilePage(driver);

        driver.get("https://stellarburgers.nomoreparties.site");

        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        accessToken = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/auth/register")
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body(CoreMatchers.containsString("user"))
                .body(CoreMatchers.containsString("accessToken"))
                .body(CoreMatchers.containsString("refreshToken"))
                .extract()
                .path("accessToken");


    }


    @DisplayName("Клик на кнопку 'Личный кабинет' неавторизованным юзером")
    @Test
    public void clickPersonalAccountButtonAsUnauthorizedUser() {
        mainPage.waitAndClickHeaderPersonalAccountButton();

        String headerName = loginPage.getHeaderText();
        Assert.assertTrue(headerName.contains("Вход"));
    }

    @DisplayName("Клик на кнопку 'Личный кабинет' авторизованным юзером")
    @Test
    public void clickPersonalAccountButtonAsAuthorizedUser() {
        mainPage.waitAndClickHeaderPersonalAccountButton();
        loginPage.sendKeysToEmailField(email);
        loginPage.sendKeysToPasswordField(password);
        loginPage.waitAndClickEnterButton();

        mainPage.waitAndClickHeaderPersonalAccountButton();

        profilePage.waitForAccountInfoVisibility();
        String infoText = profilePage.getAccountInfoText();
        Assert.assertTrue(infoText.contains("В этом разделе вы можете изменить свои персональные данные"));
    }

    @DisplayName("Клик на кнопку 'Конструктор' из 'Личный кабинет'")
    @Test
    public void moveToMainPageFromPersonalAccount() {
        mainPage.waitAndClickHeaderPersonalAccountButton();
        mainPage.waitAndClickHeaderConstructorButton();

        String mainPageTableText = mainPage.getTableText();
        Assert.assertTrue(mainPageTableText.contains("Соберите бургер"));
    }

    @DisplayName("Клик на 'логотип'")
    @Test
    public void moveToMainPageByLogoClick() {
        mainPage.waitAndClickHeaderLogoButton();

        String mainPageTableText = mainPage.getTableText();
        Assert.assertTrue(mainPageTableText.contains("Соберите бургер"));
    }

    @After
    public void tearDown() {
        String accessToken = given()
                .contentType(ContentType.JSON)
                .body("{\"email\":\"" + email + "\"," + "\"password\":\"" + password + "\"}")
                .when()
                .post("/api/auth/login")
                .then()
                .extract()
                .path("accessToken");

        if (accessToken != null) {
            given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", accessToken)
                    .when()
                    .delete("/api/auth/user")
                    .then()
                    .statusCode(202)
                    .body("message", equalTo("User successfully removed"));
        }

        driver.quit();
    }

}
