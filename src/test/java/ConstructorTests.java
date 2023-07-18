import PageObject.MainPage;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static PageObject.MainPage.MAIN_PAGE_URL;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ConstructorTests {

    // параметризация для кроссбраузерного тестирования
    private final String browser;

    /**
     * Конструктор класса.
     *
     * @param browser Браузер для тестирования
     */
    public ConstructorTests(String browser) {
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
     * Тест, проверяющий возможность добавления элемента из раздела "Булки" в корзину.
     */
    @Test
    @DisplayName("Проверяем, что после перехода в раздел 'Булки' можно добавить элемент из этого раздела в корзину")
    public void checkTransitionOfBunsButton() {
        MainPage main = open(MAIN_PAGE_URL, MainPage.class);
        assertTrue("The 'Buns' section should be active after clicking the button", main.openIngredientSectionBuns());
        assertTrue("After drag and drop the bun in the order basket must be visible", main.dragTheBunsToTheBasket());
    }

    /**
     * Тест, проверяющий возможность добавления элемента из раздела "Соусы" в корзину.
     */
    @Test
    @DisplayName("Проверяем, что после перехода в раздел 'Соусы' можно добавить элемент из этого раздела в корзину")
    public void checkTransitionOfSaucesButton() {
        MainPage main = open(MAIN_PAGE_URL, MainPage.class);
        assertTrue("The 'Sauces' section should be active after clicking the button", main.openIngredientSectionSauces());
        assertTrue("After drag and drop the sauce in the order basket must be visible", main.dragTheSauceToTheBasket());
    }

    /**
     * Тест, проверяющий возможность добавления элемента из раздела "Начинки" в корзину.
     */
    @Test
    @DisplayName("Проверяем, что после перехода в раздел 'Начинки' можно добавить элемент из этого раздела в корзину")
    public void checkTransitionOfFillingButton() {
        MainPage main = open(MAIN_PAGE_URL, MainPage.class);
        assertTrue("The 'Fillings' section should be active after clicking the button", main.openIngredientSectionFillings());
        assertTrue("After drag and drop the filling in the order basket must be visible", main.dragTheFillingToTheBasket());
    }

}