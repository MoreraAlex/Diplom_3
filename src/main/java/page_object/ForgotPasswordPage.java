package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    private static WebDriver driver;
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.xpath("//fieldset/div/div/label[text()='Email']");
    private By recoverButton = By.cssSelector(".button_button__33qZ0");
    private By signInText = By.cssSelector(".Auth_link__1fOlj[href=\"/login\"]");


    public WebElement waitForEmailFieldVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
    }
    public WebElement waitForRecoverButtonVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(recoverButton));
    }
    public WebElement waitForSignInTextVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(signInText));
    }

    public void waitAndClickEmailField()
    {
        waitForEmailFieldVisibility();
        driver.findElement(emailField).click();
    }
    public void waitAndClickRecoverButton()
    {
        waitForEmailFieldVisibility();
        driver.findElement(recoverButton).click();
    }
    public void waitAndClickSignInText()
    {
        waitForSignInTextVisibility();
        driver.findElement(signInText).click();
    }

    public void sendKeysToEmailField(String text) {
        waitAndClickEmailField();
        WebElement emailInputElement = driver.findElement(emailField);
        emailInputElement.clear();
        emailInputElement.sendKeys(text);
    }


}
