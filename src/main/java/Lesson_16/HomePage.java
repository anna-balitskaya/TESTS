package Lesson_16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By payButton = By.cssSelector("button.button.button__default");

    private By dropdownButton = By.cssSelector(".select__header");
    private By dropdownList = By.cssSelector(".select__list");

    private By phoneField = By.id("connection-phone");
    private By sumField = By.id("connection-sum");
    private By emailField = By.id("connection-email");

    private By phoneFieldInternet = By.id("internet-phone");
    private By sumFieldInternet = By.id("internet-sum");
    private By emailFieldInternet = By.id("internet-email");

    private By accountNumieldInstallment = By.id("score-instalment");
    private By sumFieldInstallment = By.id("instalment-sum");
    private By emailFieldInstallment = By.id("instalment-email");

    private By accountNumFieldDebt = By.id("score-arrears");
    private By sumFielDdebt = By.id("arrears-sum");
    private By emailFieldDebt = By.id("arrears-email");

    private By homeInternetOption = By.xpath("//li[contains(@class, 'select__item')]//p[text()='Домашний интернет']");
    private By installmentOption = By.xpath("//li[contains(@class, 'select__item')]//p[text()='Рассрочка']");
    private By debtOption = By.xpath("//li[contains(@class, 'select__item')]//p[contains(text(),'Задолженность')]");


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    public void clickInternetOption() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownButton));
        dropdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownList));

        WebElement homeInternet = wait.until(ExpectedConditions.visibilityOfElementLocated(homeInternetOption));
        homeInternet.click();
    }

    public void clickInstallmentOption() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownButton));
        dropdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownList));

        WebElement installment = wait.until(ExpectedConditions.visibilityOfElementLocated(installmentOption));
        installment.click();
    }

    public void clickDebtOption() {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownButton));
        dropdown.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownList));

        WebElement debt = wait.until(ExpectedConditions.visibilityOfElementLocated(debtOption));
        debt.click();
    }


    public void acceptCookies() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        try {
            WebElement acceptButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            acceptButton.click();
        } catch (Exception e) {
            System.out.println("Кнопка \"Принять куки\" не отобразилась");
        }
    }

    public void enterPhone(String phoneNumber) {
        WebElement phoneFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField));
        phoneFieldElement.click();
        phoneFieldElement.sendKeys(phoneNumber);
    }

    public void enterSum(String sum) {
        WebElement sumFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sumField));
        sumFieldElement.click();
        sumFieldElement.sendKeys(sum);
    }

    public void enterEmail(String email) {
        WebElement emailFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        emailFieldElement.click();
        emailFieldElement.sendKeys(email);
    }

    public PaymentPage clickPayButton() {
        WebElement payButtonElement = wait.until(ExpectedConditions.elementToBeClickable(payButton));
        payButtonElement.click();
        return new PaymentPage(driver);
    }

    public String getPhoneFieldPlaceholder() {
        WebElement phoneFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneField));
        return phoneFieldElement.getAttribute("placeholder");
    }

    public String getSumFieldPlaceholder() {
        WebElement sumFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sumField));
        return sumFieldElement.getAttribute("placeholder");
    }

    public String getEmailFieldPlaceholder() {
        WebElement emailFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        return emailFieldElement.getAttribute("placeholder");
    }

    public String getPhoneFieldInternetPlaceholder() {
        WebElement phoneFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneFieldInternet));
        return phoneFieldElement.getAttribute("placeholder");
    }

    public String getSumFieldInternetPlaceholder() {
        WebElement sumFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sumFieldInternet));
        return sumFieldElement.getAttribute("placeholder");
    }

    public String getEmailFieldInternetPlaceholder() {
        WebElement emailFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldInternet));
        return emailFieldElement.getAttribute("placeholder");
    }

    public String getAccountNumFieldInstallmentPlaceholder() {
        WebElement accountNumFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(accountNumieldInstallment));
        return accountNumFieldElement.getAttribute("placeholder");
    }

    public String getSumFieldInstallmentPlaceholder() {
        WebElement sumFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sumFieldInstallment));
        return sumFieldElement.getAttribute("placeholder");
    }

    public String getEmailFieldInstallmentPlaceholder() {
        WebElement emailFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldInstallment));
        return emailFieldElement.getAttribute("placeholder");
    }

    public String getAccountNumFieldDebtPlaceholder() {
        WebElement accountNumFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(accountNumFieldDebt));
        return accountNumFieldElement.getAttribute("placeholder");
    }

    public String getSumFieldDebtPlaceholder() {
        WebElement sumFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sumFielDdebt));
        return sumFieldElement.getAttribute("placeholder");
    }

    public String getEmailFieldDebtPlaceholder() {
        WebElement emailFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailFieldDebt));
        return emailFieldElement.getAttribute("placeholder");
    }
}
