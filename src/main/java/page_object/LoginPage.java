package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private static WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.cssSelector(".text[name=\"name\"]");
    private By passwordField = By.cssSelector(".text[name=\"Пароль\"]");
    private By enterButton = By.cssSelector(".button_button__33qZ0");
    private By signUpText = By.cssSelector(".Auth_link__1fOlj[href=\"/register\"]");
    private By forgotPasswordText = By.cssSelector(".Auth_link__1fOlj[href=\"/forgot-password\"]");
    private By headerName = By.xpath("//div/h2[text()='Вход']");


    public WebElement waitForEmailFieldVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
    }
    public WebElement waitForPasswordFieldVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
    }
    public WebElement waitForEnterButtonVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(enterButton));
    }
    public WebElement waitForSignUpTextVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(signUpText));
    }
    public WebElement waitForForgotPasswordTextVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordText));
    }


    public void waitAndClickEmailField()
    {
        waitForEmailFieldVisibility();
        driver.findElement(emailField).click();
    }
    public void waitAndClickPasswordField()
    {
        waitForPasswordFieldVisibility();
        driver.findElement(passwordField).click();
    }
    public void waitAndClickEnterButton()
    {
        waitForEnterButtonVisibility();
        driver.findElement(enterButton).click();
    }
    public void waitAndClickSignUpText()
    {
        waitForSignUpTextVisibility();
        driver.findElement(signUpText).click();
    }
    public void waitAndClickForgotPasswordText()
    {
        waitForForgotPasswordTextVisibility();
        driver.findElement(forgotPasswordText).click();
    }

    public void sendKeysToEmailField(String text) {
        waitAndClickEmailField();
        WebElement emailInputElement = driver.findElement(emailField);
        emailInputElement.clear();
        emailInputElement.sendKeys(text);
    }
    public void sendKeysToPasswordField(String text) {
        waitAndClickPasswordField();
        WebElement emailInputElement = driver.findElement(passwordField);
        emailInputElement.clear();
        emailInputElement.sendKeys(text);
    }

    public String getHeaderText() {
        WebElement buttonElement = driver.findElement(headerName);
        return buttonElement.getText();
    }
}
