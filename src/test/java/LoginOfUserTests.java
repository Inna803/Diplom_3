import PageObject.ForgotPasswordPage;
import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.RegisterPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static PageObject.ForgotPasswordPage.FORGOT_PASSWORD_URL;
import static PageObject.MainPage.MAIN_PAGE_URL;
import static PageObject.RegisterPage.REGISTER_PAGE_URL;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LoginOfUserTests {
    MainPage main = page(MainPage.class);
    LoginPage login = page(LoginPage.class);
    RegisterPage register = page(RegisterPage.class);
    ForgotPasswordPage forgotPasswordPage = page(ForgotPasswordPage.class);

    // параметризация для кроссбраузерного тестирования
    private final String browser;

    /**
     * Конструктор класса.
     *
     * @param browser Браузер для тестирования
     */
    public LoginOfUserTests(String browser) {
        this.browser = browser;
    }

    /**
     * Метод, возвращающий параметры для тестирования.
     *
     * @return Массив параметров
     */
    @Parameterized.Parameters(name = "{0} browser")
    public static Object[][] browserForTest() {
        return new Object[][]{
                {"Chrome"},
                {"Yandex"}
        };
    }

    /**
     * Метод, выполняющий настройку перед каждым тестом.
     */
    @Before
    public void setUp() {
        closeWebDriver();
        TestConfiguration.configureBrowser(browser);
    }

    /**
     * Метод, выполняющий очистку после каждого теста.
     */
    @After
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    /**
     * Тест, проверяющий корректный вход пользователя с главной страницы через кнопку "Войти в аккаунт".
     */
    @Test
    @DisplayName("Корректный вход с главной страницы через кнопку \"Войти в аккаунт\"")
    public void checkLoginFromMainPageViaEntryButton() {
        open(MAIN_PAGE_URL);
        main.clickAccountEntryButton();
        login.entry(login.EMAIL, login.PASSWORD);
        assertEquals("After successful login user must be redirected on the main page!", url(), MAIN_PAGE_URL);
    }

    /**
     * Тест, проверяющий корректный вход пользователя с главной страницы через кнопку "Личный кабинет".
     */
    @Test
    @DisplayName("Корректный вход с главной страницы через кнопку \"Личный кабинет\"")
    public void checkLoginFromMainPageViaPersonalAccountButton() {
        open(MAIN_PAGE_URL);
        main.clickPersonalAccountButton();
        login.entry(login.EMAIL, login.PASSWORD);
        assertEquals("After successful login user must be redirected on the main page!", url(), MAIN_PAGE_URL);
    }

    /**
     * Тест, проверяющий корректный вход пользователя со страницы регистрации через кнопку "Войти".
     */
    @Test
    @DisplayName("Корректный вход со страницы регистрации через кнопку \"Войти\"")
    public void checkLoginFromRegistrationPageViaPersonalAccountButton() {
        open(REGISTER_PAGE_URL);
        register.clickTheEntryButton();
        login.entry(login.EMAIL, login.PASSWORD);
        assertEquals("After successful login user must be redirected on the main page!", url(), MAIN_PAGE_URL);
    }

    /**
     * Тест, проверяющий переход пользователя со страницы восстановления пароля через кнопку "Войти".
     */
    @Test
    @DisplayName("Переход со страницы восстановления пароля через кнопку \"Войти\"")
    public void checkLoginFromForgotPasswordPageViaEntryButton() {
        open(FORGOT_PASSWORD_URL);
        forgotPasswordPage.clickTheEntryButton();
        login.entry(login.EMAIL, login.PASSWORD);
        assertEquals("After successful login user must be redirected on the main page!", url(), MAIN_PAGE_URL);
    }

}