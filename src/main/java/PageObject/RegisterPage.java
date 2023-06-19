package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage {

    // url страницы регистрации
    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";

    // поля: имя, почта, пароль (индекс имени = 0, индекс почты = 1, индекс пароля = 2)
    @FindBy(how = How.XPATH, using = ".//input[@class='text input__textfield text_type_main-default']")
    public ElementsCollection nameEmailPasswordFields;

    // кнопка "Зарегистрироваться"
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registryButton;

    // кнопка "Войти"
    @FindBy(how = How.XPATH, using = "//*[@href='/login']")
    private SelenideElement entryButton;

    // кадпись "Некорректный пароль"
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement incorrectPasswordSign;

    /**
     * Ввод имени
     */
    @Step("Ввод имени")
    public void setName(String name) {
        nameEmailPasswordFields.get(0).setValue(name);
    }

    /**
     * Ввод почты
     */
    @Step("Ввод почты")
    public void setEmail(String email) {
        nameEmailPasswordFields.get(1).setValue(email);
    }

    /**
     * Ввод пароля
     */
    @Step("Ввод пароля")
    public void setPassword(String password) {
        nameEmailPasswordFields.get(2).setValue(password);
    }

    /**
     * Нажатие на кнопку регистрации
     */
    @Step("Нажатие на кнопку регистрации")
    public void clickRegistrationButton() {
        registryButton.click();
    }

    /**
     * Регистрация
     */
    @Step("Регистрация")
    public void registration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
    }

    /**
     * Проверка надписи некорректного пароля
     */
    @Step("Проверка надписи некорректного пароля")
    public boolean checkIncorrectPasswordSign() {
        return incorrectPasswordSign.isDisplayed();
    }

    /**
     * Подождать после регистрации
     * Ждем пока кнопка регистрации исчезнет после успешной регистрации
     */
    @Step("Подождать после регистрации")
    public void waitAfterRegistration() {
        registryButton.shouldBe(Condition.hidden);
    }

    /**
     * Нажать на кнопку "Войти"
     */
    @Step("Нажать на кнопку \"Войти\"")
    public void clickTheEntryButton() {
        entryButton.scrollTo().click();
    }

    // переменные для регистрации пользователя
    public final String NAME = "test" + RandomStringUtils.randomAlphabetic(3);
    public final String EMAIL = "test" + RandomStringUtils.randomAlphabetic(3) + "@mail.ru";
    public final String PASSWORD = "123456";
    public final String INCORRECT_PASSWORD = "12345";
}