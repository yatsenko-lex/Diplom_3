package praktikum.screens;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.generators.User;
import praktikum.generators.UserActions;

public class RegistrationScreen {

    public By nameInput = By.cssSelector("input.text.input__textfield.text_type_main-default");
    public By registerButton = By.xpath("//button[contains(text(), 'Зарегистрироваться')]");
    public By wrongPasswordMessage = By.xpath("//p[contains(text(), 'Некорректный пароль')]");
    public By loginButton = By.xpath("//a[@href='/login']");
    private WebDriver webDriver;
    private Faker faker;
    private User user;

    public RegistrationScreen(WebDriver webDriver) {
        this.webDriver = webDriver;
        faker = new Faker();
    }

    @Step("Открытие страницы регистрации")
    public void openPage() {
        webDriver.get("https://stellarburgers.nomoreparties.site/register");
    }

    @Step("Успешная регистрация пользователя")
    public User userRegistration(String password) {
        user = UserActions.randomUser().password(password);
        webDriver.findElements(nameInput).get(0).sendKeys(user.getName());
        webDriver.findElements(nameInput).get(1).sendKeys(user.getEmail());
        webDriver.findElements(nameInput).get(2).sendKeys(user.getPassword());
        webDriver.findElement(registerButton).click();
        return user;
    }

    @Step("Отображение ошибки пароля")
    public boolean passwordError() {
        return webDriver.findElement(wrongPasswordMessage).isDisplayed();
    }

    @Step("Нажатие на кнопку 'Войти' на ЭФ Регистрации")
    public void enterButtonClick() {
        webDriver.findElement(loginButton).click();
    }
}