import Constructor.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_object.LoginPage;
import page_object.MainPage;
import page_object.ProfilePage;

import static driver.WebDriverCreator.createWebDriver;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class LogoutTests {
    private WebDriver driver;
    private MainPage mainPage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    String accessToken;
    String email = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
    String name = RandomStringUtils.randomAlphabetic(10);
    String password = RandomStringUtils.randomAlphabetic(10);
    User user = new User(email, password, name);



    @Before
    public void setUp() {
        driver = createWebDriver();

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
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

    @DisplayName("Проверка логаута")
    @Test
    public void logoutTest() {
        mainPage.waitAndClickHeaderPersonalAccountButton();
        loginPage.sendKeysToEmailField(email);
        loginPage.sendKeysToPasswordField(password);
        loginPage.waitAndClickEnterButton();
        mainPage.waitAndClickHeaderPersonalAccountButton();

        profilePage.waitAndClickLogoutButton();

        loginPage.waitForEnterButtonVisibility();
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
