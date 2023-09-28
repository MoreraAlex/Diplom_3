import Constructor.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_object.ForgotPasswordPage;
import page_object.LoginPage;
import page_object.MainPage;
import page_object.RegistrationPage;

import static driver.WebDriverCreator.createWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class LoginTests {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private ForgotPasswordPage forgotPasswordPage;

    String accessToken;
    String email = java.util.UUID.randomUUID() + "@gmail.com";
    String password = java.util.UUID.randomUUID().toString();
    String name = java.util.UUID.randomUUID().toString();

    User user = new User(email, password, name);

    @Before
    public void setUp() {
        driver = createWebDriver();

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);

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


    @DisplayName("Логин через кнопку 'Личный кабинет'")
    @Test
    public void loginFromPersonalAccountHeaderButton() {
        mainPage.waitForMakeOrderButtonVisibility();
        String buttonText = mainPage.getMakeOrderButtonText();
        Assert.assertTrue(buttonText.contains("Войти в аккаунт"));

        mainPage.waitAndClickHeaderPersonalAccountButton();
        loginPage.sendKeysToEmailField(email);
        loginPage.sendKeysToPasswordField(password);
        loginPage.waitAndClickEnterButton();
        mainPage.waitForMakeOrderButtonVisibility();

        buttonText = mainPage.getMakeOrderButtonText();
        Assert.assertTrue(buttonText.contains("Оформить заказ"));
    }

    @DisplayName("Логин через кнопку 'Войти в аккаунт'")
    @Test
    public void loginFromEnterAccountButton() {
        mainPage.waitForMakeOrderButtonVisibility();
        String buttonText = mainPage.getMakeOrderButtonText();
        Assert.assertTrue(buttonText.contains("Войти в аккаунт"));

        mainPage.waitAndClickMakeOrderButton();
        loginPage.sendKeysToEmailField(email);
        loginPage.sendKeysToPasswordField(password);
        loginPage.waitAndClickEnterButton();
        mainPage.waitForMakeOrderButtonVisibility();

        buttonText = mainPage.getMakeOrderButtonText();
        Assert.assertTrue(buttonText.contains("Оформить заказ"));
    }

    @DisplayName("Логин после перехода со страницы Регистрации")
    @Test
    public void loginFromRegistrationPage() {
        mainPage.waitForMakeOrderButtonVisibility();
        String buttonText = mainPage.getMakeOrderButtonText();
        Assert.assertTrue(buttonText.contains("Войти в аккаунт"));

        mainPage.waitAndClickHeaderPersonalAccountButton();
        loginPage.waitAndClickSignUpText();
        registrationPage.waitAndClickSignInText();

        loginPage.sendKeysToEmailField(email);
        loginPage.sendKeysToPasswordField(password);
        loginPage.waitAndClickEnterButton();
        mainPage.waitForMakeOrderButtonVisibility();

        buttonText = mainPage.getMakeOrderButtonText();
        Assert.assertTrue(buttonText.contains("Оформить заказ"));
    }

    @DisplayName("Логин после перехода со страницы восстановления пароля")
    @Test
    public void loginFromForgotPasswordPage() {
        mainPage.waitForMakeOrderButtonVisibility();
        String buttonText = mainPage.getMakeOrderButtonText();
        Assert.assertTrue(buttonText.contains("Войти в аккаунт"));

        mainPage.waitAndClickHeaderPersonalAccountButton();
        loginPage.waitAndClickForgotPasswordText();

        forgotPasswordPage.waitAndClickSignInText();

        loginPage.sendKeysToEmailField(email);
        loginPage.sendKeysToPasswordField(password);
        loginPage.waitAndClickEnterButton();
        mainPage.waitForMakeOrderButtonVisibility();

        buttonText = mainPage.getMakeOrderButtonText();
        Assert.assertTrue(buttonText.contains("Оформить заказ"));
    }

    @After
    public void tearDown() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then()
                .statusCode(202)
                .body("message", equalTo("User successfully removed"));

        driver.quit();
    }


}
