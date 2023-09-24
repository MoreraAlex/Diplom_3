package page_object;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private static WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локаторы элементов на стартовой странице
    private By headerConstructorButton = By.cssSelector(".AppHeader_header__link__3D_hX[href=\"/\"]");
    private By headerFeedButton = By.cssSelector(".AppHeader_header__link__3D_hX[href=\"/feed\"]");
    private By headerLogoButton = By.cssSelector(".AppHeader_header__logo__2D0X2");
    private By headerPersonalAccountButton = By.cssSelector(".AppHeader_header__link__3D_hX[href=\"/account\"]");
    private By constructorBunsTab = By.xpath("//section[1]/div[1]/div[1]/span[text()='Булки']");
    private By constructorSauceTab = By.xpath("//section[1]/div[1]/div[2]/span[text()='Соусы']");
    private By constructorFillingsTab = By.xpath("//section[1]/div[1]/div[3]/span[text()='Начинки']");
    private By makeOrderButton = By.cssSelector(".button_button__33qZ0");
    private By tableText = By.cssSelector(".text.text_type_main-large.mb-5.mt-10");
    private By menuBunsBlock = By.xpath("//section[1]/div[2]/h2[1][text()='Булки']");
    private By menuSauceBlock = By.xpath("//section[1]/div[2]/h2[2][text()='Соусы']");
    private By menuFillingsBlock = By.xpath("//section[1]/div[2]/h2[3][text()='Начинки']");


    //геттеры
    public String getMakeOrderButtonText() {
        WebElement buttonElement = driver.findElement(makeOrderButton);
        return buttonElement.getText();
    }
    public String getTableText() {
        WebElement buttonElement = driver.findElement(tableText);
        return buttonElement.getText();
    }
    public String getMenuBunsBlockText() {
        WebElement buttonElement = driver.findElement(menuBunsBlock);
        return buttonElement.getText();
    }
    public String getMenuSauceBlockText() {
        WebElement buttonElement = driver.findElement(menuSauceBlock);
        return buttonElement.getText();
    }
    public String getMenuFillingsBlockText() {
        WebElement buttonElement = driver.findElement(menuFillingsBlock);
        return buttonElement.getText();
    }


//Методы для ожидания видимости
    public WebElement waitForMenuBunsBlockVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(menuBunsBlock));
    }
    public WebElement waitForMenuSauceBlockVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(menuSauceBlock));
    }
    public WebElement waitForMenuFillingsBlockVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(menuFillingsBlock));
    }

    public WebElement waitForHeaderConstructorButtonVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerConstructorButton));
    }
    public WebElement waitForHeaderFeedButtonVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerFeedButton));
    }
    public WebElement waitForHeaderLogoVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerLogoButton));
    }
    public WebElement waitHeaderPersonalAccountButtonVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerPersonalAccountButton));
    }
    public WebElement waitForConstructorBunsTabVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(constructorBunsTab));
    }
    public WebElement waitForConstructorSauceTabVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(constructorSauceTab));
    }
    public WebElement waitForConstructorFillingsTabVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(constructorFillingsTab));
    }
    public WebElement waitForMakeOrderButtonVisibility()
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));
    }


//Кликеры
    public void waitAndClickHeaderConstructorButton()
    {
        waitForHeaderConstructorButtonVisibility();
        driver.findElement(headerConstructorButton).click();
    }
    public void waitAndClickHeaderFeedButton()
    {
        waitForHeaderFeedButtonVisibility();
        driver.findElement(headerFeedButton).click();
    }
    public void waitAndClickHeaderLogoButton()
    {
        waitForHeaderLogoVisibility();
        driver.findElement(headerLogoButton).click();
    }
    public void waitAndClickHeaderPersonalAccountButton()
    {
        waitHeaderPersonalAccountButtonVisibility();
        driver.findElement(headerPersonalAccountButton).click();
    }
    public void waitAndClickConstructorBunsTab()
    {
        waitForConstructorBunsTabVisibility();
        driver.findElement(constructorBunsTab).click();
    }
    public void waitAndClickConstructorSauceTab()
    {
        waitForConstructorSauceTabVisibility();
        driver.findElement(constructorSauceTab).click();
    }
    public void waitAndClickConstructorFillingsTab()
    {
        waitForConstructorFillingsTabVisibility();
        driver.findElement(constructorFillingsTab).click();
    }
    public void waitAndClickMakeOrderButton()
    {
        waitForMakeOrderButtonVisibility();
        driver.findElement(makeOrderButton).click();
    }



}
