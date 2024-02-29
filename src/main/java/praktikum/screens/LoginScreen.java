package praktikum.screens;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.api.UserActionsApi;
import praktikum.generators.User;
import praktikum.generators.UserActions;

public class LoginScreen {

    public By loginInput = By.xpath("//*/input[@class='text input__textfield text_type_main-default']");
    public By logInButton = By.xpath("//button[contains(text(), 'Войти')]");
    private WebDriver webDriver;

    public LoginScreen(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открытие сайта")
    public void openSite() {
        webDriver.get("https://stellarburgers.nomoreparties.site/login");
    }

    @Step("Создание пользователя и авторизация")
    public void createUserAndLogin() {
        User user = UserActions.randomUser();
        UserActionsApi.create(user);
        webDriver.findElements(loginInput).get(0).sendKeys(user.getEmail());
        webDriver.findElements(loginInput).get(1).sendKeys(user.getPassword());
        webDriver.findElement(logInButton).click();
    }
}