import PageObject.LoginPage;
import PageObject.MainPage;
import PageObject.PersonalAccountPage;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static PageObject.LoginPage.LOGIN_PAGE_URL;
import static PageObject.MainPage.MAIN_PAGE_URL;
import static PageObject.PersonalAccountPage.PERSONAL_ACCOUNT_PAGE_URL;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class PersonalAccountPageTests {

    // параметр для кроссбраузерного тестирования
    private final String browser;

    // конструктор класса, принимающий параметр browser
    public PersonalAccountPageTests(String browser) {
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

        // открываем главную страницу и выполняем вход в аккаунт
        MainPage main = open(MAIN_PAGE_URL, MainPage.class);
        main.clickAccountEntryButton();
        LoginPage login = page(LoginPage.class);
        login.entry(login.EMAIL, login.PASSWORD);
    }

    // метод, выполняющий очистку после каждого теста
    @After
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    // тест, проверяющий переход в личный кабинет с главной страницы
    @Test
    @DisplayName("Переход на страницу личного кабинета с главной страницы")
    public void checkTheTransitionToThePersonalAccountPage() {
        // кликаем на кнопку перехода в личный кабинет на главной странице
        MainPage main = page(MainPage.class);
        main.clickPersonalAccountButton();

        // ожидаем завершения перехода на страницу личного кабинета и проверяем URL
        PersonalAccountPage personalPage = page(PersonalAccountPage.class);
        personalPage.waitAfterTransition();
        assertEquals("After click to the PA button user must be redirected on the personal account page!",
                url(), PERSONAL_ACCOUNT_PAGE_URL);
    }

    // тест, проверяющий переход из личного кабинета в конструктор по нажатию на кнопку "Конструктор"
    @Test
    @DisplayName("Переход на главную страницу после нажатия кнопки конструктора")
    public void checkTheTransitionAfterClickConstructorButton() {
        // кликаем на кнопку перехода в личный кабинет на главной странице
        MainPage main = page(MainPage.class);
        main.clickPersonalAccountButton();

        // ожидаем завершения перехода на страницу личного кабинета
        PersonalAccountPage personalPage = page(PersonalAccountPage.class);
        personalPage.waitAfterTransition();

        // кликаем на кнопку "Конструктор" в личном кабинете и проверяем URL
        personalPage.clickTheConstructorButton();
        assertEquals("After click to the constructor button user must be redirected on the main page!",
                url(), MAIN_PAGE_URL);
    }

    // тест, проверяющий переход из личного кабинета в конструктор по нажатию на логотип бургера
    @Test
    @DisplayName("Переход на главную страницу после нажатия на логотип")
    public void checkTheTransitionAfterClickLogo() {
        // кликаем на кнопку перехода в личный кабинет на главной странице
        MainPage main = page(MainPage.class);
        main.clickPersonalAccountButton();

        // ожидаем завершения перехода на страницу личного кабинета
        PersonalAccountPage personalPage = page(PersonalAccountPage.class);
        personalPage.waitAfterTransition();

        // кликаем на логотип бургера в личном кабинете и проверяем URL
        personalPage.clickTheLogo();
        assertEquals("After click to the logo user must be redirected on the main page!",
                url(), MAIN_PAGE_URL);
    }

    // тест, проверяющий переход из личного кабинета на страницу логина после выхода
    @Test
    @DisplayName("Переход на страницу входа в систему после нажатия на кнопку выхода из системы")
    public void checkTheTransitionAfterLogOut() {
        // кликаем на кнопку перехода в личный кабинет на главной странице
        MainPage main = page(MainPage.class);
        main.clickPersonalAccountButton();

        // ожидаем завершения перехода на страницу личного кабинета
        PersonalAccountPage personalPage = page(PersonalAccountPage.class);
        personalPage.waitAfterTransition();

        // выполняем выход из аккаунта и ожидаем завершения
        personalPage.clickTheLogOutButton();
        personalPage.waitAfterLogOut();

        // проверяем, что после выхода пользователь будет перенаправлен на страницу входа
        assertEquals("After click to the log out button user must be redirected on the login page!",
                url(), LOGIN_PAGE_URL);
    }
}