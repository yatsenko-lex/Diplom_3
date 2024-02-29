package praktikum.screens;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainScreen {

    public By enterAccountButton = By.xpath("//button[contains(text(), 'Войти в аккаунт')]");
    public By accountButton = By.xpath("//a[@href='/account']");
    public By bunsButton = By.xpath("//span[contains(text(), 'Булки')]");
    public By bunsText = By.xpath("//h2[contains(text(), 'Булки')]");
    public By saucesButton = By.xpath("//span[contains(text(), 'Соусы')]");
    public By saucesText = By.xpath("//h2[contains(text(), 'Соусы')]");
    public By fillingsButton = By.xpath("//span[contains(text(), 'Начинки')]");
    public By fillingsText = By.xpath("//h2[contains(text(), 'Начинки')]");

    private WebDriver webDriver;
    private WebDriverWait wait;

    public MainScreen(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 3);
    }

    @Step("Открытие конструктора")
    public void openConstructor() {
        webDriver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Нажатие на кнопку 'Войти в аккаунт'")
    public void enterAccountClickButton() {
        webDriver.findElement(enterAccountButton).click();
    }

    @Step("Клик по кнопке личный кабинет")
    public void accountClickButton() throws InterruptedException {
        webDriver.findElement(accountButton).click();
        Thread.sleep(1500);
    }

    @Step("Scroll до раздела 'Булки'")
    public void bunsScroll() throws InterruptedException {
        webDriver.findElement(bunsButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunsText));
        Thread.sleep(1000);
    }

    @Step("Проверка отображения текста 'Булки'")
    public boolean bunsIsVisible() {
        return webDriver.findElement(bunsText).isDisplayed();
    }

    @Step("Scroll до раздела 'Соусы'")
    public void sauceScroll() throws InterruptedException {
        webDriver.findElement(saucesButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(saucesText));
        Thread.sleep(1000);
    }

    @Step("Проверка отображения текста 'Соусы'")
    public boolean saucesIsVisible() {
        return webDriver.findElement(saucesText).isDisplayed();
    }

    @Step("Scroll до раздела 'Начинки'")
    public void fillingsScroll() throws InterruptedException {
        webDriver.findElement(fillingsButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsText));
        Thread.sleep(1000);
    }

    @Step("Проверка отображения текста 'Начинки'")
    public boolean fillingsIsVisible() {
        return webDriver.findElement(fillingsText).isDisplayed();
    }
}