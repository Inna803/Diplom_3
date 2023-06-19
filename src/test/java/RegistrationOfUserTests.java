import PageObject.RegisterPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static PageObject.LoginPage.LOGIN_PAGE_URL;
import static PageObject.RegisterPage.REGISTER_PAGE_URL;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegistrationOfUserTests {

    // параметр для кроссбраузерного тестирования
    private final String browser;

    // конструктор класса, принимающий параметр browser
    public RegistrationOfUserTests(String browser) {
        this.browser = browser;
    }

    // метод, определяющий параметры для тестирования
    @Parameterized.Parameters(name = "{0} browser")
    public static Object[][] browserForTest() {
        return new Object[][]{
                {"Chrome"},
                {"Yandex"}
        };
    }

    // метод, выполняющий настройку перед каждым тестом
    @Before
    public void setUp() {
        closeWebDriver();
        TestConfiguration.configureBrowser(browser);
    }

    // тест, проверяющий успешную регистрацию пользователя
    @Test
    @DisplayName("Успешная регистрация")
    public void checkTheCorrectRegistration() {
        // открываем страницу регистрации
        RegisterPage registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);

        // выполняем регистрацию пользователя
        registerPage.registration(registerPage.NAME, registerPage.EMAIL, registerPage.PASSWORD);

        // ожидаем завершения регистрации
        registerPage.waitAfterRegistration();

        // проверяем, что пользователь будет перенаправлен на страницу входа после успешной регистрации
        assertEquals("After successful registration user must be redirected on the login page!",
                url(), LOGIN_PAGE_URL);
    }

    // тест, проверяющий регистрацию с коротким паролем и отображение ошибки
    @Test
    @DisplayName("Регистрация с коротким паролем")
    public void checkTheRegistrationWithIncorrectPassword() {
        // открываем страницу регистрации
        RegisterPage registerPage = open(REGISTER_PAGE_URL, RegisterPage.class);

        // выполняем регистрацию пользователя с коротким паролем
        registerPage.registration(registerPage.NAME, registerPage.EMAIL, registerPage.INCORRECT_PASSWORD);

        // проверяем, что знак некорректного пароля видим
        assertTrue("The incorrect password sign must be visible", registerPage.checkIncorrectPasswordSign());
    }
}