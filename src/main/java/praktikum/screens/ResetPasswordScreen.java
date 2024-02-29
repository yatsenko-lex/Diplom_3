package praktikum.screens;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordScreen {

    public By forgetPasswordButton = By.xpath("//a[@href='/login']");
    private WebDriver webDriver;

    public ResetPasswordScreen(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открытие ЭФ 'Восстановление пароля'")
    public void openResetPasswordScreen() {
        webDriver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    @Step("Нажатие на кнопку 'Войти' на ЭФ Восстановления пароля")
    public void enterClickButton() {
        webDriver.findElement(forgetPasswordButton).click();
    }
}