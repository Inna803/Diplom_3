package PageObject;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PersonalAccountPage {

    // url личного кабинета
    public static final String PERSONAL_ACCOUNT_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    // кнопка "История заказов" - уникальный локатор для верификации личного кабинета
    @FindBy(how = How.XPATH, using = ".//a[text()='История заказов']")
    private SelenideElement ordersHistoryButton;

    // кнопка "Выход"
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement logOutButton;

    // кнопка "Конструктор"
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    private SelenideElement constructorButton;

    // логотип бургер
    @FindBy(how = How.XPATH, using = ".//*[@id='root']/div/header/nav/div/a")
    private SelenideElement logoButton;

    /**
     * Нажимает на кнопку "Выход"
     */
    @Step("Нажать на кнопку \"Выход\"")
    public void clickTheLogOutButton() {
        logOutButton.click();
    }

    /**
     * Нажимает на кнопку "Конструктор"
     */
    @Step("Нажать на кнопку \"Конструктор\"")
    public void clickTheConstructorButton() {
        constructorButton.click();
    }

    /**
     * Нажимает на логотип бургер
     */
    @Step("Нажать на логотип бургер")
    public void clickTheLogo() {
        logoButton.click();
    }

    /**
     * Ожидает загрузку страницы после перехода
     */
    @Step("Ждем загрузку страницы")
    public void waitAfterTransition() {
        logOutButton.shouldBe(Condition.visible);
    }

    /**
     * Ожидает исчезновение кнопки "Выход" после выхода из личного кабинета
     */
    @Step("Ожидание после выхода")
    public void waitAfterLogOut() {
        logOutButton.shouldBe(Condition.disappear);
    }
}