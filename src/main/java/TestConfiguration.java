import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestConfiguration {

    /**
     * Конфигурирует браузер в зависимости от переданного параметра.
     *
     * @param browser Браузер для конфигурации
     */
    public static void configureBrowser(String browser) {
        if (browser.equals("Yandex")) {
            // путь к драйверу Chrome
            System.setProperty("webdriver.chrome.driver", "C:\\WebDriver112\\bin\\chromedriver.exe");

            // настройки для Яндекс браузера
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\Пользователь\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");

            Configuration.browser = "chrome";
            Configuration.browserCapabilities = options;
        } else {
            // конфигурируем другой браузер
            Configuration.browser = browser;
        }
    }
}