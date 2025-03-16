package Lesson_15_Junit5;

/*
Test сase
0. Открыть браузур (Chrome) и перейти поссылке URL https://www.mts.by/
и добавить флюент ожидание для появления кнопки "Принять" на модалке
1. Кликнуть кнопку "Принять" на модалке "Обработка файлов cookie"
2. Проверить название для блока "Онлайн пополнение"
3. Проверить наличие логотипов платежных систем
4. Проверить работу ссылки "Подробнее о сервисе"
5-8. Заполнить поля платежной формы для "Услуги связи" и кликнуть по кнопке
Закрыть браузер
 */

import io.github.bonigarcia.wdm.WebDriverManager;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GoogleChrome_Junit5 {

    private static WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DisplayName("Перейти по ссылке в Google Chrome на сайт МТС")
    @Test
    @Order(0)
    void goToLink() {
        driver.get("https://www.mts.by/");
    }


    @DisplayName("Принять cookie")
    @Test
    @Order(1)
    void acceptCookie() {
        FluentWait<WebDriver>wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(1))
                .ignoring(Exception.class);

        WebElement acceptButton = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("cookie-agree"));
            }
        });
        acceptButton.click();
    }

    @DisplayName("Проверка названия блока \"Онлайн пополнение без комиссии\"")
    @Test
    @Order(2)
    void checkBlockName () {
        WebElement payWrapper = driver.findElement(By.className("pay__wrapper"));
        WebElement header = payWrapper.findElement(By.tagName("h2"));
        assertEquals("Онлайн пополнение\nбез комиссии", header.getText().trim(), "Текст заголовка не совпадает.");
      //  WebElement blockTitle = driver.findElement(By.xpath("//h2[contains(text(),'Онлайн пополнение')]"));
    }

    @DisplayName("Проверка логотипов платежных систем")
    @Test
    @Order(3)
    void checkLogos () {
        List<WebElement> logos = driver.findElements(By.cssSelector(".pay__partners img"));
        String[] expectedAltValues = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкард"};
    }

    @DisplayName("Проверка ссылки \"Подробнее о сервисе\"")
    @Test
    @Order(4)
    void checkLink () {
        WebElement link = driver.findElement(By.linkText("Подробнее о сервисе"));
        String href = link.getAttribute("href");
        String expectedHref = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        assertEquals(expectedHref, href, "Некорректный URL");
    }

    @DisplayName("Заполнение поля \"Номер телефона\"")
    @Test
    @Order(5)
    void clickAndEnterPhone () {
        WebElement phoneField = driver.findElement(By.id("connection-phone"));
        phoneField.click();
        String valueToEnter = "297777777";
        phoneField.sendKeys(valueToEnter);
    }

    @DisplayName("Заполнение поля \"Сумма\"")
    @Test
    @Order(6)
    void clickAndEnterSum () {
        WebElement sumField = driver.findElement(By.id("connection-sum"));
        sumField.click();
        String valueToEnter = "15";
        sumField.sendKeys(valueToEnter);
    }

    @DisplayName("Заполнение поля \"E-mail для отправки чека\"")
    @Test
    @Order(7)
    void clickAndEnterEmail () {
        WebElement emailField = driver.findElement(By.id("connection-email"));
        emailField.click();
        String valueToEnter = "test123test@gmail.com";
        emailField.sendKeys(valueToEnter);
    }

    @DisplayName("Клик по кнопке \"Продолжить\"")
    @Test
    @Order(8)
    void payButtonClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement payButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button.button__default")));
        payButton.click();
    }
}
