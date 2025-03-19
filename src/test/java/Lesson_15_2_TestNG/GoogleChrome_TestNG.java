package Lesson_15_2_TestNG;

/*
Test сase
Открыть браузур (Chrome) и перейти поссылке URL https://www.mts.by/
и добавить флюент ожидание для появления кнопки "Принять" на модалке
Кликнуть кнопку "Принять" на модалке "Обработка файлов cookie"
1. Проверить название для блока "Онлайн пополнение"
2. Проверить наличие логотипов платежных систем
3. Проверить работу ссылки "Подробнее о сервисе"
4-7. Заполнить поля платежной формы для "Услуги связи", кликнуть по кнопке и проверить переход на окно оплаты
Закрыть браузер
 */

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class GoogleChrome_TestNG {

    private static WebDriver driver;

    @BeforeClass
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(Exception.class);

        try {
            WebElement acceptButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            acceptButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка \"Принять куки\" не отобразилась");

        }
    }

    @AfterMethod
    static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "Проверка названия блока \"Онлайн пополнение без комиссии\"")
    void checkBlockName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement payWrapper = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("pay__wrapper")));
        WebElement header = payWrapper.findElement(By.tagName("h2"));
        assertEquals("Онлайн пополнение\nбез комиссии", header.getText().trim(), "Текст заголовка не совпадает");
    }

    @Test(description = "Проверка логотипов платежных систем")
    void checkLogos() {
        List<WebElement> logos = driver.findElements(By.cssSelector(".pay__partners img"));
        String[] expectedAltValues = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкард"};
    }

        @Test(description = "Проверка ссылки \"Подробнее о сервисе\"")
        void checkLink () {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Подробнее о сервисе")));
            String href = link.getAttribute("href");
            String expectedHref = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
            assertEquals(expectedHref, href, "Некорректный URL");
        }

        @Test(description = "Заполнение поля \"Номер телефона\"")
        void clickAndEnterPhone () {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")));
            phoneField.click();
            String valueToEnter = "297777777";
            phoneField.sendKeys(valueToEnter);
            String actualValue = phoneField.getAttribute("value").replaceAll("\\D", "");
            assertEquals(valueToEnter, actualValue, "Введенное значение не соответствует ожидаемому");
        }

        @Test(description = "Заполнение поля \"Сумма\"")
        void clickAndEnterSum () {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement sumField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")));
            wait.until(ExpectedConditions.elementToBeClickable(sumField));
            sumField.click();
            String valueToEnter = "15";
            sumField.sendKeys(valueToEnter);
            wait.until(ExpectedConditions.attributeToBe(sumField, "value", valueToEnter));
            String actualValue = sumField.getAttribute("value");
            assertEquals(valueToEnter, actualValue, "Введенное значение не соответствует ожидаемому");
        }

        @Test(description = "Заполнение поля \"E-mail для отправки чека\"")
        void clickAndEnterEmail () {
            WebElement emailField = driver.findElement(By.id("connection-email"));
            emailField.click();
            String valueToEnter = "test123test@gmail.com";
            emailField.sendKeys(valueToEnter);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.attributeToBe(emailField, "value", valueToEnter));
            String actualValue = emailField.getAttribute("value");
            assertEquals(valueToEnter, actualValue, "Введенное значение не соответствует ожидаемому");
        }

        @Test(description = "Клик по кнопке \"Продолжить\"")
        void payButtonClick () {
            clickAndEnterPhone();
            clickAndEnterSum();
            clickAndEnterEmail();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            WebElement payButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button.button__default")));
            payButton.click();
            System.out.println("Кнопка \"Продолжить\" успешно нажата");

            WebElement iframeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//iframe[@class='bepaid-iframe'])[1]")));
            assertNotNull(iframeElement, "Не удалось найти iframe");

            driver.switchTo().frame(iframeElement);
            System.out.println("Переключение на iframe успешно");

            WebElement paymentForm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='payment-page__container']")));
            System.out.println("Окно оплаты отображается");
            assertNotNull(paymentForm, "Форма для оплаты отсутствует");

            driver.switchTo().defaultContent();
        }
}
