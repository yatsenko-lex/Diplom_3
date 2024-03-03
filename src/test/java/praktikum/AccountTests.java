package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.generators.UserActions;
import praktikum.screens.AccountScreen;
import praktikum.screens.LoginScreen;
import praktikum.screens.MainScreen;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AccountTests {

    Settings settings = new Settings();
    WebDriver webDriver;
    MainScreen mainScreen;
    LoginScreen loginScreen;
    AccountScreen accountScreen;
    UserActions userActions = new UserActions();
    private final String PROFILE = "https://stellarburgers.nomoreparties.site/account/profile";
    private final String LOGIN = "https://stellarburgers.nomoreparties.site/login";
    private final String MAIN = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", settings.getDriverPath());
        webDriver = new ChromeDriver();
        mainScreen = new MainScreen(webDriver);
        loginScreen = new LoginScreen(webDriver);
        accountScreen = new AccountScreen(webDriver);
    }

    @Test
    @DisplayName("Переход авторизованного пользователя в личный кабинет")
    public void loginAndMoveToAccountTest() throws InterruptedException {
        mainScreen.openConstructor();
        mainScreen.accountClickButton();
        loginScreen.createUserAndLogin();
        mainScreen.accountClickButton();
        assertEquals(PROFILE, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход в конструктор неавторизованного пользователя")
    public void unauthorizedUserAccountTest() throws InterruptedException {
        mainScreen.openConstructor();
        mainScreen.accountClickButton();
        assertEquals(LOGIN, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход в конструктор по кнопке логотипа")
    public void moveToConstructorFromLogoTest() throws InterruptedException {
        loginScreen.openSite();
        loginScreen.createUserAndLogin();
        mainScreen.accountClickButton();
        accountScreen.logoClick();
        assertEquals(MAIN, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Переход в конструктор по кнопке конструктора")
    public void moveToConstructorTest() throws InterruptedException {
        loginScreen.openSite();
        loginScreen.createUserAndLogin();
        mainScreen.accountClickButton();
        accountScreen.constructorClick();
        assertEquals(MAIN, webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    public void exitAccountTest() throws InterruptedException {
        loginScreen.openSite();
        loginScreen.createUserAndLogin();
        mainScreen.accountClickButton();
        accountScreen.exitClick();
        assertEquals(LOGIN, webDriver.getCurrentUrl());
    }

    @After
    public void tearDown() {
        userActions.deleteUser();
        webDriver.close();
    }
}