package praktikum.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.generators.User;

import static io.restassured.RestAssured.given;

public class UserActionsApi {
    private static final String LOGIN = "https://stellarburgers.nomoreparties.site/api/auth/login";
    private static final String DELETE = "https://stellarburgers.nomoreparties.site/api/auth/user";
    private static final String REGISTER = "https://stellarburgers.nomoreparties.site/api/auth/register";
    public static String accessToken;

    @Step("Создание пользователя")
    public static void create(User user) {
        Response response = given()
                .header("Content-Type", "application/json")
                .and()
                .body(user)
                .when()
                .post(REGISTER);
        accessToken = response.then().extract().path("accessToken");

    }

    @Step("Получение Token")
    public static Response getToken(User user) {
        Response response = given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(LOGIN);
        accessToken = response.then().extract().path("accessToken");
        return response;
    }

    @Step("Удаление пользователя")
    public static void delete() {
        if (accessToken != null) {
            given()
                    .header("Content-Type", "application/json")
                    .header("Authorization", accessToken)
                    .when()
                    .delete(DELETE);
        }
    }
}