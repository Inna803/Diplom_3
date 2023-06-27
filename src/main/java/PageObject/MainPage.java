package PageObject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;

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

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab_type_current')]/span[contains(@class, 'text_type_main-default')]")
    private SelenideElement activeElement;

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
    @Step("Открыть раздел 'Начинки'")
    public boolean openIngredientSectionFillings() {
        // нажимаем кнопку "Начинки"
        fillingsButton.shouldBe(enabled).click();
        // проверяем, что активный элемент содержит текст "Начинки" и возвращает результат его наличия
        return getActiveElement().shouldHave(text("Начинки")).exists();
    }


    @Step("Перетащить элемент 'Начинки' и проверить отображение в корзине")
    public boolean dragTheFillingToTheBasket() {
        fillingsSign.shouldBe(visible); // проверяет отображение заголовка "Начинки"
        fillingForDrop.dragAndDropTo(orderBasket); // перетаскивает элемент "Начинки" в корзину
        return fillingInBasket.isDisplayed(); // проверяет отображение начинки в корзине
    }


    /**
     * Нажимает кнопку "Соусы" и проверяет отображение в корзине
     *
     * @return true, если соус отображается в корзине, в противном случае - false
     */

    @Step("Открыть раздел 'Соусы'")
    public boolean openIngredientSectionSauces() {
        // прокручивает страницу, чтобы элемент lastIngredient был видимым
        lastIngredient.scrollIntoView(true);
        // пажимаем кнопку "Соусы"
        saucesButton.shouldBe(enabled).click();
        // проверяем, что активный элемент содержит текст "Соусы" и возвращает результат его наличия
        return getActiveElement().shouldHave(text("Соусы")).exists();
    }

    @Step("Перетащить элемент 'Соусы' и проверить отображение в корзине")
    public boolean dragTheSauceToTheBasket() {
        saucesSign.shouldBe(visible); // проверяет отображение заголовка "Соусы"
        sauceForDrop.dragAndDropTo(orderBasket); // перетаскивает элемент "Соус" в корзину
        return sauceInBasket.isDisplayed(); // возвращает true, если соус отображается в корзине
    }

    /**
     * Нажимает кнопку "Булки" и проверяет отображение в корзине
     *
     * @return true, если булка отображается в корзине, в противном случае - false
     */

    @Step("Открыть раздел 'Булки'")
    public boolean openIngredientSectionBuns() {
        // прокручивает страницу, чтобы элемент lastIngredient был видимым
        lastIngredient.scrollIntoView(true);
        // нажимаем кнопку "Булки"
        bunsButton.shouldBe(enabled).click();
        // проверяем, что активный элемент содержит текст "Булки" и возвращает результат его наличия
        return getActiveElement().shouldHave(text("Булки")).exists();
    }

    @Step("Перетащить элемент 'Булки' и проверить отображение в корзине")
    public boolean dragTheBunsToTheBasket() {
        bunsSign.shouldBe(visible); // проверяет отображение заголовка "Булки"
        bunForDrop.dragAndDropTo(orderBasket); // перетаскивает элемент "Булка" в корзину
        return bunInBasket.isDisplayed(); // возвращает true, если булка отображается в корзине
    }

    public SelenideElement getActiveElement() {
        return activeElement;
    }

}