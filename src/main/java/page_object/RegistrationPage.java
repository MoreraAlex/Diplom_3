package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private static WebDriver driver;
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    //локаторы
    private By nameField = By.cssSelector(".Auth_fieldset__1QzWN:nth-child(1) .input__textfield");
    private By emailField = By.cssSelector(".Auth_fieldset__1QzWN:nth-child(2) .input__textfield");
    private By passwordField = By.cssSelector(".Auth_fieldset__1QzWN:nth-child(3) .input__textfield");
    private By signUpButton = By.cssSelector(".button_button__33qZ0");
    private By signInText = By.cssSelector(".Auth_link__1fOlj[href=\"/login\"]");
    private By passwordError = By.xpath("//p[contains(.,'Некорректный пароль')]");


    //ожидания видимости
    public WebElement waitForNameFieldVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }
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
    public WebElement waitForSignUpButtonVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(signUpButton));
    }
    public WebElement waitForSignInTextVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(signInText));
    }

    //кликеры
    public void waitAndClickNameField()
    {
        waitForNameFieldVisibility();
        driver.findElement(nameField).click();
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
    public void waitAndClickSignUpButton()
    {
        waitForSignUpButtonVisibility();
        driver.findElement(signUpButton).click();
    }
    public void waitAndClickSignInText()
    {
        waitForSignInTextVisibility();
        driver.findElement(signInText).click();
    }

    public void sendKeysToNameField(String text) {
        waitAndClickNameField();
        WebElement emailInputElement = driver.findElement(nameField);
        emailInputElement.clear();
        emailInputElement.sendKeys(text);
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

    public String getPasswordFieldErrorText() {
        WebElement buttonElement = driver.findElement(passwordError);
        return buttonElement.getText();
    }

}
