package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private static WebDriver driver;
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private By accountInfo = By.cssSelector(".Account_text__fZAIn");
    private By logoutButton = By.xpath("//button[contains(.,'Выход')]");

    public WebElement waitForAccountInfoVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountInfo));
    }
    public WebElement waitForLogoutButtonVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
    }
    public String getAccountInfoText()
    {
        WebElement accountInfoText = driver.findElement(accountInfo);
        return accountInfoText.getText();
    }

    public void waitAndClickLogoutButton()
    {
        waitForLogoutButtonVisibility();
        driver.findElement(logoutButton).click();
    }
}
