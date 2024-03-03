package praktikum.screens;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountScreen {

    public By exitButton = By.xpath("//li/button");
    public By logo = By.xpath("//div/a[@href='/']");
    public By constructorButton = By.xpath("//li/a[@href='/']");

    private WebDriver webDriver;

    public AccountScreen(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Нажатие на логотип")
    public void logoClick() {
        webDriver.findElement(logo).click();
    }

    @Step("Нажатие на кнопку конструктора")
    public void constructorClick() {
        webDriver.findElement(constructorButton).click();
    }

    @Step("Нажатие на кнопку 'Выход'")
    public void exitClick() throws InterruptedException {
        webDriver.findElement(exitButton).click();
        Thread.sleep(1500);
    }
}