package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import praktikum.screens.MainScreen;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TabsTests {

    Settings settings = new Settings();
    WebDriver webDriver;
    MainScreen mainScreen;

    @Before
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", settings.getDriverPath());
        webDriver = new ChromeDriver();
        mainScreen = new MainScreen(webDriver);
        mainScreen.openConstructor();
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    public void scrollToBunsTest() throws InterruptedException {
        mainScreen.bunsScroll();
        assertTrue(mainScreen.bunsIsVisible());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    public void scrollToSauceTest() throws InterruptedException {
        mainScreen.sauceScroll();
        assertTrue(mainScreen.saucesIsVisible());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    public void scrollToFillingsTest() throws InterruptedException {
        mainScreen.fillingsScroll();
        assertTrue(mainScreen.fillingsIsVisible());
    }

    @After
    public void tearDown() {
        webDriver.close();
    }
}