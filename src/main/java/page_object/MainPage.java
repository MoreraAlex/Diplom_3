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
    private By constructorBunsTab = By.xpath(".//span[text()='Булки']");
    private By constructorSauceTab = By.xpath(".//span[text()='Соусы']");
    private By constructorFillingsTab = By.xpath(".//span[text()='Начинки']");
    private By makeOrderButton = By.cssSelector(".button_button__33qZ0");
    private By tableText = By.cssSelector(".text.text_type_main-large.mb-5.mt-10");
    private By menuBunsBlock = By.xpath(".//h2[text()='Булки']");
    private By menuSauceBlock = By.xpath(".//h2[text()='Соусы']");
    private By menuFillingsBlock = By.xpath(".//h2[text()='Начинки']");
    private By activeTabAtribute = By.className("tab_tab_type_current__2BEPc");


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
    public WebElement checkForMenuBunsBlockActivation() {
        return driver.findElement(activeTabAtribute);
    }

    public WebElement checkForMenuSauceBlockActivation() {
        return driver.findElement(activeTabAtribute);

    }

    public WebElement checkForMenuFillingsBlockActivation() {
        return driver.findElement(activeTabAtribute);

    }


    public WebElement waitForHeaderConstructorButtonVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerConstructorButton));
    }

    public WebElement waitForHeaderFeedButtonVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerFeedButton));
    }

    public WebElement waitForHeaderLogoVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerLogoButton));
    }

    public WebElement waitHeaderPersonalAccountButtonVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(headerPersonalAccountButton));
    }

    public boolean waitForConstructorBunsTabVisibility() {
        return driver.findElement(constructorBunsTab).isDisplayed();
    }

    public boolean waitForConstructorSauceTabVisibility() {
        return driver.findElement(constructorSauceTab).isDisplayed();
    }

    public boolean waitForConstructorFillingsTabVisibility() {
        return driver.findElement(constructorFillingsTab).isDisplayed();
    }

    public WebElement waitForMakeOrderButtonVisibility() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));
    }


    //Кликеры
    public void waitAndClickHeaderConstructorButton() {
        waitForHeaderConstructorButtonVisibility();
        driver.findElement(headerConstructorButton).click();
    }

    public void waitAndClickHeaderFeedButton() {
        waitForHeaderFeedButtonVisibility();
        driver.findElement(headerFeedButton).click();
    }

    public void waitAndClickHeaderLogoButton() {
        waitForHeaderLogoVisibility();
        driver.findElement(headerLogoButton).click();
    }

    public void waitAndClickHeaderPersonalAccountButton() {
        waitHeaderPersonalAccountButtonVisibility();
        driver.findElement(headerPersonalAccountButton).click();
    }

    public void waitAndClickConstructorBunsTab() {
        waitForConstructorBunsTabVisibility();
        driver.findElement(constructorBunsTab).click();
    }

    public void waitAndClickConstructorSauceTab() {
        waitForConstructorSauceTabVisibility();
        driver.findElement(constructorSauceTab).click();
    }

    public void waitAndClickConstructorFillingsTab() {
        waitForConstructorFillingsTabVisibility();
        driver.findElement(constructorFillingsTab).click();
    }

    public void waitAndClickMakeOrderButton() {
        waitForMakeOrderButtonVisibility();
        driver.findElement(makeOrderButton).click();
    }


}
