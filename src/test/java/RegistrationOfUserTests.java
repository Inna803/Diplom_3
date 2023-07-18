import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegisterPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import user.User;
import user.UserClient;

import java.util.logging.Level;
import java.util.logging.Logger;

import static PageObject.LoginPage.LOGIN_PAGE_URL;
import static PageObject.MainPage.MAIN_PAGE_URL;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegistrationOfUserTests {
    static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);// создаем логгер для записи сообщений
    private User user; // объявление приватной переменной типа User для хранения данных о пользователе
    private MainPage mainPage; // объявление приватной переменной типа MainPage для доступа к странице главного меню
    private LoginPage loginPage; // объявление приватной переменной типа LoginPage для доступа к странице входа
    private RegisterPage registrationPage; // объявление приватной переменной типа RegistrationPage для доступа к странице регистрации
    private final UserClient userClient = new UserClient(); // создание экземпляра класса UserClient для выполнения операций с пользователем


    //параметризация для кроссбраузерного тестирования
    private final String browser;

    public RegistrationOfUserTests(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters(name = "{0} browser")
    public static Object[][] browserForTest() {
        return new Object[][]{
                {"Chrome"},
                {"Yandex"}

        };
    }

    @Before
    public void setUp() {
        user = User.createUserRandom();// генерируем случайного пользователя
        closeWebDriver();// закрываем веб-драйвер
        TestConfiguration.configureBrowser(browser);
        mainPage = open(MAIN_PAGE_URL, MainPage.class); // открытие главной страницы и привязка объекта MainPage к ней
        loginPage = page(LoginPage.class); // привязка объекта LoginPage к странице входа
        registrationPage = page(RegisterPage.class); // привязка объекта RegistrationPage к странице регистрации
    }


    // удаление пользователя
    @After
    public void deleteUser() {
        if (user != null) {
            try {
                userClient.deleteUser(user.getEmail(), user.getPassword()); // удаление пользователя с использованием UserClient
                logger.log(Level.INFO, "Пользователь успешно удален."); // логирование успешного удаления пользователя
            } catch (Exception e) {
                logger.log(Level.WARNING, "Пользователь не был удален. Возможно, он не был создан."); // логирование ошибки при удалении пользователя
            }
        }
    }


    //при успешной регистрации переход на страницу входа
    @Test
    @DisplayName("Успешная регистрация")
    public void checkRegistrationValidTest() {
        mainPage.clickAccountEntryButton(); // нажатие на кнопку "Войти в аккаунт" на главной странице
        loginPage.clickRegButton(); // нажатие на кнопку "Регистрация" на странице входа
        registrationPage.registration(user.getName(), user.getEmail(), user.getPassword()); // ввод данных регистрации на странице регистрации
        assertTrue(loginPage.existVhod()); // проверка, что на странице входа отображается элемент "Вход"
        // проверяем, что пользователь будет перенаправлен на страницу входа после успешной регистрации
        assertEquals("После успешной регистрации пользователь должен быть перенаправлен на страницу входа в систему!",
                url(), LOGIN_PAGE_URL);
    }


    //регистрация с коротким паролем, отображение ошибки
    @Test
    @DisplayName("Регистрация с коротким паролем")
    public void checkTheRegistrationWithIncorrectPassword() {
        mainPage.clickAccountEntryButton(); // нажатие на кнопку "Войти в аккаунт" на главной странице
        loginPage.clickRegButton(); // нажатие на кнопку "Регистрация" на странице входа
        registrationPage.registration(user.getName(), user.getEmail(), User.SHORT_PASS); // регистрация пользователя с коротким паролем
        assertTrue(registrationPage.errorExist()); // проверка, что на странице регистрации отображается ошибка
    }
}