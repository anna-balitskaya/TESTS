package Lesson_16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {

    private static WebDriver driver;
    private HomePage homePage;
    private PaymentPage paymentPage;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {

        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");

        homePage = new HomePage(driver);
        paymentPage = new PaymentPage(driver);

        homePage.acceptCookies();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @DisplayName("Проверка названия блока \"Онлайн пополнение без комиссии\"")
    @Test
    void checkBlockName() {
        WebElement payWrapper = driver.findElement(By.className("pay__wrapper"));
        WebElement header = payWrapper.findElement(By.tagName("h2"));
        assertEquals("Онлайн пополнение\nбез комиссии", header.getText().trim(), "Текст заголовка не совпадает");
    }

    @DisplayName("Проверка логотипов платежных систем")
    @Test
    void checkLogos() {
        List<WebElement> logos = driver.findElements(By.cssSelector(".pay__partners img"));
        String[] expectedAltValues = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};
        for (int i = 0; i < expectedAltValues.length; i++) {
            assertTrue(logos.get(i).getAttribute("alt").contains(expectedAltValues[i]),
                    "Логотип с alt значением '" + expectedAltValues[i] + "' не найден");
        }
    }

    @DisplayName("Проверка ссылки \"Подробнее о сервисе\"")
    @Test
    void checkLink() {
        WebElement link = driver.findElement(By.linkText("Подробнее о сервисе"));
        String href = link.getAttribute("href");
        String expectedHref = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        assertEquals(expectedHref, href, "Некорректный URL");
    }

    @DisplayName("Заполнение поля \"Номер телефона\"")
    @Test
    void clickAndEnterPhone() {
        homePage.enterPhone("297777777");

        WebElement phoneField = driver.findElement(By.id("connection-phone"));
        String actualValue = phoneField.getAttribute("value").replaceAll("\\D", "");
        assertEquals("297777777", actualValue, "Введенное значение не соответствует ожидаемому");
    }

    @DisplayName("Заполнение поля \"Сумма\"")
    @Test
    void clickAndEnterSum() {
        homePage.enterSum("15");

        WebElement sumField = driver.findElement(By.id("connection-sum"));
        String actualValue = sumField.getAttribute("value");
        assertEquals("15", actualValue, "Введенное значение не соответствует ожидаемому");
    }

    @DisplayName("Заполнение поля \"E-mail для отправки чека\"")
    @Test
    void clickAndEnterEmail() {
        homePage.enterEmail("test123test@gmail.com");

        WebElement emailField = driver.findElement(By.id("connection-email"));
        String actualValue = emailField.getAttribute("value");
        assertEquals("test123test@gmail.com", actualValue, "Введенное значение не соответствует ожидаемому");
    }

    @DisplayName("Клик по кнопке \"Продолжить\" и проверка ранее введенных значений на форме оплаты")
    @Test
    void payButtonClickAndCheckValues() {
        String enteredAmount = "15";
        String enteredPhoneNumber = "297777777";
        homePage.enterPhone(enteredPhoneNumber);
        homePage.enterSum(enteredAmount);
        homePage.enterEmail("test123test@gmail.com");

        paymentPage = homePage.clickPayButton();
        paymentPage.switchToIframe();

        assertTrue(paymentPage.isPaymentFormVisible(), "Форма для оплаты отсутствует");

        String displayedAmount = paymentPage.getDisplayedAmount();
        String expectedAmount = "15.00 BYN";
        assertEquals(expectedAmount, displayedAmount, "Сумма в окне оплаты отображается некорректно");

        String displayedPayButtonAmount = paymentPage.getPayButtonAmount();
        String expectedPayButtonAmount = "Оплатить 15.00 BYN";
        assertEquals(expectedPayButtonAmount, displayedPayButtonAmount, "Сумма на кнопке \"Оплатить\" некорректна");

        String displayedPhoneNumber = paymentPage.getPhoneNumber();
        assertEquals("Оплата: Услуги связи Номер:375" + enteredPhoneNumber, displayedPhoneNumber, "Номер телефона на странице оплаты некорректен");

        paymentPage.switchToDefaultContent();
    }

    @DisplayName("Проверка плейсхолдеров полей ввода и иконок платежных систем для формы оплаты")
    @Test
    void checkFieldsAndIcons() {

        String enteredAmount = "15";
        String enteredPhoneNumber = "297777777";
        homePage.enterPhone(enteredPhoneNumber);
        homePage.enterSum(enteredAmount);
        homePage.enterEmail("test123test@gmail.com");

        paymentPage = homePage.clickPayButton();
        paymentPage.switchToIframe();

        String placeholderText = paymentPage.getCardNumberPlaceholder();
        assertEquals("Номер карты", placeholderText, "Надпись в поле \"Номер карты\" некорректна");

        assertTrue(paymentPage.isVisaIconVisible(), "Иконка Visa не отображается");

        assertTrue(paymentPage.isMasterCardIconVisible(), "Иконка MasterCard не отображается");

        assertTrue(paymentPage.isBelCardIconVisible(), "Иконка Белкарт не отображается");

        // assertTrue(paymentPage.isMirCardIconVisible(), "Иконка MIR не отображается");

        // assertTrue(paymentPage.isMaestroCardIconVisible(), "Иконка Maestro не отображается");

        String validityPeriodPlaceholder = paymentPage.getCardValidityPeriodFieldPlaceholder();
        assertEquals("Срок действия", validityPeriodPlaceholder, "Надпись в поле \"Срок действия карты\" некорректна");

        String cvcPlaceholder = paymentPage.getCardCVCFieldPlaceholder();
        assertEquals("CVC", cvcPlaceholder, "Надпись в поле \"CVC\" некорректна");

        String cardHolderNamePlaceholder = paymentPage.getCardHolderNameFieldPlaceholder();
        assertEquals("Имя держателя (как на карте)", cardHolderNamePlaceholder, "Надпись в поле \"Имя владельца карты\" некорректна");
    }

    @DisplayName("Плейсхолдеры незаполненных полей для: \"Услуги связи\"")
    @Test
    void checkPlaceholders() {
        String phonePlaceholder = homePage.getPhoneFieldPlaceholder();
        String sumPlaceholder = homePage.getSumFieldPlaceholder();
        String emailPlaceholder = homePage.getEmailFieldPlaceholder();

        assertEquals("Номер телефона", phonePlaceholder, "Некорректный плейсхолдер для телефона");
        assertEquals("Сумма", sumPlaceholder, "Некорректный плейсхолдер для суммы");
        assertEquals("E-mail для отправки чека", emailPlaceholder, "Некорректный плейсхолдер для E-mail");
    }

    @DisplayName("Плейсхолдеры незаполненных полей для: \"Домашний интернет\"")
    @Test
    void checkPlaceholdersForHomeInternet() {

        homePage.clickInternetOption();

        String phonePlaceholder = homePage.getPhoneFieldInternetPlaceholder();
        String sumPlaceholder = homePage.getSumFieldInternetPlaceholder();
        String emailPlaceholder = homePage.getEmailFieldInternetPlaceholder();

        assertEquals("Номер абонента", phonePlaceholder, "Некорректный плейсхолдер для телефона");
        assertEquals("Сумма", sumPlaceholder, "Некорректный плейсхолдер для суммы");
        assertEquals("E-mail для отправки чека", emailPlaceholder, "Некорректный плейсхолдер для E-mail");
    }

    @DisplayName("Плейсхолдеры незаполненных полей для: \"Рассрочка\"")
    @Test
    void checkPlaceholdersForInstallment() {

        homePage.clickInstallmentOption();

        String accountPlaceholder = homePage.getAccountNumFieldInstallmentPlaceholder();
        String sumPlaceholder = homePage.getSumFieldInstallmentPlaceholder();
        String emailPlaceholder = homePage.getEmailFieldInstallmentPlaceholder();

        assertEquals("Номер счета на 44", accountPlaceholder, "Некорректный плейсхолдер для счета");
        assertEquals("Сумма", sumPlaceholder, "Некорректный плейсхолдер для суммы");
        assertEquals("E-mail для отправки чека", emailPlaceholder, "Некорректный плейсхолдер для E-mail");
    }

    @DisplayName("Плейсхолдеры незаполненных полей для: \"Задолженность\"")
    @Test
    void checkPlaceholdersForDebt() {

        homePage.clickDebtOption();

        String accountPlaceholder = homePage.getAccountNumFieldDebtPlaceholder();
        String sumPlaceholder = homePage.getSumFieldDebtPlaceholder();
        String emailPlaceholder = homePage.getEmailFieldDebtPlaceholder();

        assertEquals("Номер счета на 2073", accountPlaceholder, "Некорректный плейсхолдер для счета");
        assertEquals("Сумма", sumPlaceholder, "Некорректный плейсхолдер для суммы");
        assertEquals("E-mail для отправки чека", emailPlaceholder, "Некорректный плейсхолдер для E-mail");
    }

}

