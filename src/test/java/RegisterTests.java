import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_object.LoginPage;
import page_object.MainPage;
import page_object.RegistrationPage;

import static driver.WebDriverCreator.createWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class RegisterTests {

    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    String email = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
    String name = RandomStringUtils.randomAlphabetic(10);
    String validPassword = RandomStringUtils.randomAlphabetic(6);
    String invalidPassword = RandomStringUtils.randomAlphabetic(4);


    @Before
    public void setUp() {
        driver = createWebDriver();

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

        driver.get("https://stellarburgers.nomoreparties.site");
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

    }

    @DisplayName("Регистрация с главной страницы")
    @Test
    public void registrationFromMainPageTest() {
        mainPage.waitForMakeOrderButtonVisibility();
        String buttonText = mainPage.getMakeOrderButtonText();
        Assert.assertTrue(buttonText.contains("Войти в аккаунт"));

        mainPage.waitAndClickHeaderPersonalAccountButton();
        loginPage.waitAndClickSignUpText();

        registrationPage.sendKeysToNameField(name);
        registrationPage.sendKeysToEmailField(email);
        registrationPage.sendKeysToPasswordField(validPassword);
        registrationPage.waitAndClickSignUpButton();

        loginPage.waitAndClickEmailField();
        loginPage.sendKeysToEmailField(email);
        loginPage.sendKeysToPasswordField(validPassword);
        loginPage.waitAndClickEnterButton();
        mainPage.waitForMakeOrderButtonVisibility();

        buttonText = mainPage.getMakeOrderButtonText();
        Assert.assertTrue(buttonText.contains("Оформить заказ"));
    }

    @DisplayName("Регистрация с паролем <6 символов")
    @Test
    public void registrationWithInvalidPasswordTest() {
        mainPage.waitForMakeOrderButtonVisibility();
        String buttonText = mainPage.getMakeOrderButtonText();
        Assert.assertTrue(buttonText.contains("Войти в аккаунт"));

        mainPage.waitAndClickHeaderPersonalAccountButton();
        loginPage.waitAndClickSignUpText();

        registrationPage.sendKeysToNameField(name);
        registrationPage.sendKeysToEmailField(email);
        registrationPage.sendKeysToPasswordField(invalidPassword);
        registrationPage.waitAndClickSignUpButton();

        String passwordFieldErrorText = registrationPage.getPasswordFieldErrorText();
        Assert.assertTrue(passwordFieldErrorText.contains("Некорректный пароль"));
    }

    @After
    public void tearDownAndDeleteUser() {
        String accessToken = given()
                .contentType(ContentType.JSON)
                .body("{\"email\":\"" + email + "\"," + "\"password\":\"" + validPassword + "\"}")
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
