package Lesson_16;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By iframeLocator = By.xpath("(//iframe[@class='bepaid-iframe'])[1]");
    private By paymentFormLocator = By.xpath("//div[@class='payment-page__container']");
    private By paymentAmount = By.xpath("(//span[normalize-space()='15.00 BYN'])[1]");
    private By payButtonAmount = By.xpath("//button[contains(@class, 'colored') and contains(text(), 'BYN')]");
    private By phoneNumberLocator = By.xpath("//div[contains(@class, 'pay-description__text')]/span");
    private By cardNumberFieldPlaceholder = By.xpath("(//div[@class='content ng-tns-c2312288139-1'])[1]");
    private By visaIconLocator = By.xpath("(//img[@src='assets/images/payment-icons/card-types/visa-system.svg'])[1]");
    private By masterCardIconLocator = By.xpath("(//img[@src='assets/images/payment-icons/card-types/mastercard-system.svg'])[1]");
    private By belCardIconLocator = By.xpath("(//img[@src='assets/images/payment-icons/card-types/belkart-system.svg'])[1]");
    private By mirCardIconLocator = By.xpath("(//img[@src='assets/images/payment-icons/card-types/mir-system.svg'])[1]");
    private By maestroCardIconLocator = By.xpath("(//img[@src='assets/images/payment-icons/card-types/maestro-system.svg'])[1]");
    private By cardValidityPeriodFieldPlaceholder = By.xpath("(//div[@class='content ng-tns-c2312288139-4'])[1]");
    private By cardCVCFieldPlaceholder = By.xpath("(//div[@class='content ng-tns-c2312288139-5'])[1]");
    private By cardHolderNameFieldPlaceholder = By.xpath("(//div[@class='content ng-tns-c2312288139-3'])[1]");



    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    private boolean isCardIconVisible(By iconLocator) {
        try {
            WebElement cardIcon = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(120))
                    .pollingEvery(Duration.ofMillis(100))
                    .ignoring(NoSuchElementException.class)
                    .until(driver -> {
                        WebElement element = driver.findElement(iconLocator);
                        return element != null && element.isDisplayed() ? element : null;
                    });
            return cardIcon != null && cardIcon.isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("Иконка не отображается для: " + iconLocator);
            return false;
        }
    }

    public void switchToIframe() {
        WebElement iframeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(iframeLocator));
        driver.switchTo().frame(iframeElement);
    }

    public boolean isPaymentFormVisible() {
        WebElement paymentForm = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentFormLocator));
        return paymentForm != null;
    }

    public String getDisplayedAmount() {
        WebElement amountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentAmount));
        return amountElement.getText().trim();
    }

    public String getPayButtonAmount() {
        WebElement payButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(payButtonAmount));
        return payButtonElement.getText().trim();  // Считываем текст с кнопки
    }

    public String getPhoneNumber() {
        WebElement phoneNumberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberLocator));
        return phoneNumberElement.getText().trim();
    }

    public String getCardNumberPlaceholder() {
        WebElement placeholderElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberFieldPlaceholder));
        return placeholderElement.getText().trim();
    }

    public boolean isVisaIconVisible() {
        return isCardIconVisible(visaIconLocator);
    }

    public boolean isMasterCardIconVisible() {
        return isCardIconVisible(masterCardIconLocator);
    }

    public boolean isBelCardIconVisible() {
        return isCardIconVisible(belCardIconLocator);
    }

    public boolean isMirCardIconVisible() {
        return isCardIconVisible(mirCardIconLocator);
    }

    public boolean isMaestroCardIconVisible() {
        return isCardIconVisible(maestroCardIconLocator);

    }

    public String getCardValidityPeriodFieldPlaceholder() {
        WebElement placeholderElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cardValidityPeriodFieldPlaceholder));
        return placeholderElement.getText().trim();
    }

    public String getCardCVCFieldPlaceholder() {
        WebElement placeholderElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cardCVCFieldPlaceholder));
        return placeholderElement.getText().trim();
    }

    public String getCardHolderNameFieldPlaceholder() {
        WebElement placeholderElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cardHolderNameFieldPlaceholder));
        return placeholderElement.getText().trim();
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }
}
