package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.generators.UserActions;
import praktikum.screens.LoginScreen;
import praktikum.screens.MainScreen;
import praktikum.screens.RegistrationScreen;
import praktikum.screens.ResetPasswordScreen;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LoginTests {

    Settings settings = new Settings();
    WebDriver webDriver;
    RegistrationScreen registrationScreen;
    MainScreen mainScreen;
    LoginScreen loginScreen;
    ResetPasswordScreen resetPasswordScreen;
    private final String URL = "https://stellarburgers.nomoreparties.site/account/profile";
    UserActions userActions = new UserActions();

    @Before
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", settings.getDriverPath());
        webDriver = new ChromeDriver();
        registrationScreen = new RegistrationScreen(webDriver);
        mainScreen = new MainScreen(webDriver);
        loginScreen = new LoginScreen(webDriver);
        resetPasswordScreen = new ResetPasswordScreen(webDriver);
    }

    @Test
    @DisplayName("Авторизация по кнопке 'Войти в аккаунт' на главной")
    public void loginFromMainScreenTest() throws InterruptedException {
        mainScreen.openConstructor();
        mainScreen.enterAccountClickButton();
        loginScreen.createUserAndLogin();
        mainScreen.accountClickButton();
        assertEquals(URL, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Авторизация через 'Личный кабинет'")
    public void loginFromAccountTest() throws InterruptedException {
        mainScreen.openConstructor();
        mainScreen.accountClickButton();
        loginScreen.createUserAndLogin();
        mainScreen.accountClickButton();
        assertEquals(URL, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Авторизация после регистрации")
    public void loginAfterRegistrationTest() throws InterruptedException {
        registrationScreen.openPage();
        registrationScreen.enterButtonClick();
        loginScreen.createUserAndLogin();
        mainScreen.accountClickButton();
        assertEquals(URL, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Успешный вход через кнопку в форме восстановления пароля")
    public void loginAfterResetPasswordButton() throws InterruptedException {
        resetPasswordScreen.openResetPasswordScreen();
        resetPasswordScreen.enterClickButton();
        loginScreen.createUserAndLogin();
        mainScreen.accountClickButton();
        assertEquals(URL, webDriver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        userActions.deleteUser();
        webDriver.close();
    }
}