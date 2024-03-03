package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.generators.User;
import praktikum.generators.UserActions;
import praktikum.screens.RegistrationScreen;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class RegistrationTests {

    Settings settings = new Settings();
    WebDriver webDriver;
    RegistrationScreen registrationScreen;
    UserActions userActions = new UserActions();

    @Before
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", settings.getDriverPath());
        webDriver = new ChromeDriver();
        registrationScreen = new RegistrationScreen(webDriver);
        registrationScreen.openPage();
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void successRegistrationTest() {
        User user = registrationScreen.userRegistration("123456");
        userActions.login(user).then().statusCode(200);
    }

    @Test
    @DisplayName("Регистрация с паролем < 6 символов")
    public void errorRegistrationTest() {
        User user = registrationScreen.userRegistration("12345");
        assertTrue(registrationScreen.passwordError());
        userActions.login(user).then().statusCode(401);
    }

    @After
    public void tearDown() {
        userActions.deleteUser();
        webDriver.close();
    }
}