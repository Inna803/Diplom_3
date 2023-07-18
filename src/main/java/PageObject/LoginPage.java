package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    // url страницы входа
    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    @FindBy(xpath = ".//h2[contains(text(),'Вход')]")
    private SelenideElement wordVhod;

    @FindBy(xpath = ".//a[contains(@class,'Auth_link')][contains(text(),'Зарегистрироваться')]")
    private SelenideElement registrationButton;

    // поле ввода почты
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    public SelenideElement emailField;

    // поле ввода пароля
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    public SelenideElement passwordField;

    // кнопка "Войти"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement entryButton;

    public void clickRegButton() {
        registrationButton.click();
    }

    public boolean existVhod() {
        wordVhod.shouldHave(Condition.visible);
        return wordVhod.exists();
    }

    /**
     * Вводит значение в поле электронной почты
     *
     * @param email Значение электронной почты
     */
    @Step("Ввод email")
    public void setEmail(String email) {
        emailField.setValue(email);
    }

    /**
     * Вводит значение в поле пароля
     *
     * @param password Значение пароля
     */
    @Step("Ввод password")
    public void setPassword(String password) {
        passwordField.setValue(password);
    }

    /**
     * Нажимает на кнопку входа
     */
    @Step("Нажать кнопку ввода")
    public void clickEntryButton() {
        entryButton.scrollTo();
        entryButton.click();
    }

    /**
     * Выполняет вход, вводя указанные email и password
     *
     * @param email    Значение электронной почты
     * @param password Значение пароля
     */
    @Step("Вход")
    public void entry(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickEntryButton();
        // ждем пока кнопка входа исчезнет после успешного входа
        entryButton.shouldBe(Condition.hidden);
    }

    /**
     * Ожидает после успешного входа в систему
     */
    @Step("Подождать после входа в систему")
    public void waitAfterEntry() {
        entryButton.shouldBe(Condition.hidden);
    }

    // переменные для входа пользователя (постоянный тестовый пользователь)
    public final String EMAIL = "krestitell2020@mail.ru";
    public final String PASSWORD = "cbkmdfcbkmdf19";

}