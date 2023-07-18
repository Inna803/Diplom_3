package user;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UserClient {

    public void createUserRest(String name, String email, String password) {
        // создание объекта Map для тела запроса
        Map<String, String> body = new HashMap<>();
        body.put("password", password);
        body.put("email", email);
        body.put("name", name);

        // отправка POST-запроса для создания пользователя
        given()
                .header("Content-type", "application/json") // установка заголовка Content-type
                .body(body) // установка тела запроса
                .when()
                .post(EndPoint.BASE_URL + EndPoint.BASE_PATH + EndPoint.REG_USER); // выполнение POST-запроса на указанный URL
    }

    public void deleteUser(String email, String password) {
        // создание объекта Map для тела запроса
        Map<String, String> body = new HashMap<>();
        body.put("password", password);
        body.put("email", email);

        // отправка POST-запроса для аутентификации пользователя
        Response response = given()
                .header("Content-type", "application/json") // установка заголовка Content-type
                .body(body) // Установка тела запроса
                .when()
                .post(EndPoint.BASE_URL + EndPoint.BASE_PATH + EndPoint.LOGIN); // выполнение POST-запроса на указанный URL
        String token = response.path("accessToken"); // получение токена доступа из ответа

        // отправка DELETE-запроса для удаления пользователя с использованием полученного токена
        given()
                .auth().oauth2(token.substring("Bearer ".length())) // установка аутентификации с использованием токена
                .delete(EndPoint.BASE_URL + EndPoint.BASE_PATH + EndPoint.AUTH_USER); // выполнение DELETE-запроса на указанный URL
    }
}