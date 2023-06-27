package user;

import com.github.javafaker.Faker;

public class User {
    private static String name; // переменная для хранения имени пользователя
    private static String email; // переменная для хранения адреса электронной почты пользователя
    private static String password; // переменная для хранения пароля пользователя
    public static final String SHORT_PASS = "123"; // константа для короткого пароля

    public User(String name, String email, String password) {
        this.name = name; // присваивание переданного имени пользователю
        this.email = email; // присваивание переданного адреса электронной почты пользователю
        this.password = password; // присваивание переданного пароля пользователю
    }

    public User() {
        // конструктор по умолчанию
    }

    public static User createUserRandom() {
        Faker faker = new Faker(); // создание объекта Faker для генерации случайных данных

        // генерация случайного имени, адреса электронной почты и пароля для создания пользователя
        return new User(faker.name().name(), faker.internet().emailAddress(), faker.internet().password(7, 20));
    }

    public String getName() {
        return name; // возвращает имя пользователя
    }

    public String getEmail() {
        return email; // возвращает адрес электронной почты пользователя
    }

    public String getPassword() {
        return password; // возвращает пароль пользователя
    }
}