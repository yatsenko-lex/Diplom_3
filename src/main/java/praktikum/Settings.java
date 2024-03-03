package praktikum;

import io.qameta.allure.Step;

import java.io.IOException;

public class Settings {

    private String driverPath;

    @Step("Получить путь к вебдрайверу")
    public String getDriverPath() throws IOException {
        System.getProperties().load(ClassLoader.getSystemResourceAsStream("driver.properties"));
        driverPath = System.getProperty("driverPath");
        return driverPath;
    }
}
