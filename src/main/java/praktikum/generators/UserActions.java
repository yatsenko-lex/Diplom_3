package praktikum.generators;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import praktikum.api.UserActionsApi;

public class UserActions {

    private User user;
    private static Faker faker = new Faker();

    public static User randomUser() {
        User user = new User();
        user.email(faker.internet().emailAddress());
        user.password(faker.internet().password(6, 8));
        user.name(faker.name().firstName());
        return user;
    }

    @Step("Авторизация")
    public Response login(User user) {
        Response response = UserActionsApi.getToken(user);
        System.out.println(response.asString());
        return response;
    }

    @Step("Авторизация")
    public Response login() {
        Response response = UserActionsApi.getToken(user);
        System.out.println(response.asString());
        return response;
    }

    @Step("Удаление пользователя")
    public void deleteUser() {
        UserActionsApi.delete();
    }
}