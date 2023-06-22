package PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class MainPage {

    // url главной страницы
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    // кнопка "Войти в аккаунт"
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement accountEntryButton;

    // кнопка "Личный кабинет"
    @FindBy(how = How.XPATH, using = ".//p[text()='Личный Кабинет']")
    private SelenideElement personalAccountButton;

    // кнопка "Булки"
    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunsButton;

    // заголовок "Булки" для верификации
    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement bunsSign;

    // кнопка "Соусы"
    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement saucesButton;

    // заголовок "Соусы" для верификации
    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement saucesSign;

    // кнопка "Начинки"
    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingsButton;

    // заголовок "Начинки" для верификации
    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement fillingsSign;

    // последний элемент в конструкторе для проверки переходов
    @FindBy(how = How.XPATH, using = "//p[text()='Сыр с астероидной плесенью']")
    private SelenideElement lastIngredient;

    // корзина для создания заказа
    @FindBy(how = How.CLASS_NAME, using = "BurgerConstructor_basket__list__l9dp_")
    private SelenideElement orderBasket;

    // элемент из раздела булок для drag and drop
    @FindBy(how = How.XPATH, using = ".//p[text()='Флюоресцентная булка R2-D3']")
    private SelenideElement bunForDrop;

    // отображение булки после перемещения в корзину
    @FindBy(how = How.XPATH, using = ".//span[text()='Флюоресцентная булка R2-D3 (верх)']")
    private SelenideElement bunInBasket;

    // элемент из раздела соусов для drag and drop
    @FindBy(how = How.XPATH, using = ".//p[text()='Соус Spicy-X']")
    private SelenideElement sauceForDrop;

    // отображение соуса после перемещения в корзину
    @FindBy(how = How.XPATH, using = ".//span[text()='Соус Spicy-X']")
    private SelenideElement sauceInBasket;

    // элемент из раздела начинок для drag and drop
    @FindBy(how = How.XPATH, using = ".//p[text()='Хрустящие минеральные кольца']")
    private SelenideElement fillingForDrop;

    // отображение начинки после перемещения в корзину
    @FindBy(how = How.XPATH, using = ".//span[text()='Хрустящие минеральные кольца']")
    private SelenideElement fillingInBasket;

    public MainPage() {
    }

    /**
     * Нажимает кнопку "Войти в аккаунт"
     */
    @Step("Нажать кнопку войти в аккаунт")
    public void clickAccountEntryButton() {
        accountEntryButton.click();
    }

    /**
     * Нажимает кнопку "Личный кабинет"
     */
    @Step("Нажать на кнопку Личный кабинет")
    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }

    /**
     * Нажимает кнопку "Начинки" и проверяет отображение в корзине
     *
     * @return true, если начинка отображается в корзине, в противном случае - false
     */
    @Step("Нажать кнопку \"Начинки\" и проверить отображение в корзине")
    public boolean clickFillingButtonAndCheckTheSign() {
        fillingsButton.click(); // нажимает кнопку "Начинки"
        fillingsSign.shouldBe(visible); // проверяет отображение заголовка "Начинки"
        fillingForDrop.dragAndDropTo(orderBasket); // перетаскивает элемент "Начинки" в корзину
        return fillingInBasket.isDisplayed(); // проверяет отображение начинки в корзине
    }

    /**
     * Нажимает кнопку "Соусы" и проверяет отображение в корзине
     *
     * @return true, если соус отображается в корзине, в противном случае - false
     */
    @Step("Нажать кнопку \"Соусы\" и проверить отображение в корзине")
    public boolean clickSaucesButtonAndCheckTheSign() {
        lastIngredient.scrollIntoView(true); // прокручивает страницу, чтобы элемент lastIngredient был видимым
        saucesButton.click(); // нажимает кнопку "Соусы"
        saucesSign.shouldBe(visible); // проверяет отображение заголовка "Соусы"
        sauceForDrop.dragAndDropTo(orderBasket); // перетаскивает элемент "Соус" в корзину
        return sauceInBasket.isDisplayed(); // возвращает true, если соус отображается в корзине
    }

    /**
     * Нажимает кнопку "Булки" и проверяет отображение в корзине
     *
     * @return true, если булка отображается в корзине, в противном случае - false
     */
    @Step("Нажать кнопку \"Булки\" и проверить отображение в корзине")
    public boolean clickBunsButtonCheckTheSign() {
        lastIngredient.scrollIntoView(true); // прокручивает страницу, чтобы элемент lastIngredient был видимым
        bunsButton.click(); // нажимает кнопку "Булки"
        bunsSign.shouldBe(visible); // проверяет отображение заголовка "Булки"
        bunForDrop.dragAndDropTo(orderBasket); // перетаскивает элемент "Булка" в корзину
        return bunInBasket.isDisplayed(); // возвращает true, если булка отображается в корзине
    }

}