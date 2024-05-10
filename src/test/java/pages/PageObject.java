package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PageObject {

    // адреса элементов на странице
    private static final SelenideElement buyButton = $(byText("Купить"));
    private static final SelenideElement creditButton = $(byText("Купить в кредит"));
    private static final SelenideElement filedOfCardNumber = $(By.xpath("//input[@placeholder='0000 0000 0000 0000']"));
    private static final SelenideElement filedOfOwner = $(By.xpath("//span[text()='Владелец']/../span[@class='input__box']/input"));
    private static final SelenideElement filedOfCvv = $(By.xpath("//input[@placeholder='999']"));
    private static final SelenideElement enterButton = $(By.xpath("//span[text()='Продолжить']/../.."));
    private static final SelenideElement successNotification = $(By.xpath("//div[text()='Успешно']"));
    private static final SelenideElement failNotification = $(By.xpath("//div[text()='Ошибка']"));
    private static final SelenideElement filedOfMonth = $(By.xpath("//input[@placeholder='08']"));
    private static final SelenideElement filedOfYear = $(By.xpath("//input[@placeholder='22']"));

    /**
     * Открывает анкету КУПИТЬ
     */
    public void openFormAfterClickOnButtonBuy() {

        buyButton.click();
        final SelenideElement name = $(By.xpath("//h3[text()='Оплата по карте']"));
        name.shouldBe(Condition.visible);

    }

    /**
     * Открывает анкету КУПИТЬ В КРЕДИТ
     */
    public void openFormAfterClickOnButtonCredit() {
        creditButton.click();
        final SelenideElement name = $(By.xpath("//h3[text()='Кредит по данным карты']"));
        name.shouldBe(Condition.visible);
    }

    /**
     * Проверяет, есть ли success notification
     */
    public void successNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    /**
     * Проверяет, есть ли fail notification
     */
    public void failNotification() {
        failNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    /**
     * Ищет наличие сообщения "Неверный формат" на странице
     */
    public void invalidFormatMessage() {
        $(By.xpath("//span[text()='Неверный формат']")).shouldBe(Condition.visible);
    }

    /**
     * Ищет наличие сообщения "Неверный формат"
     */
    public void invalidFormat() {
        $(By.xpath("(//*[text()='Неверный формат'])")).shouldBe(Condition.visible);
    }

    /**
     * Ищет наличие сообщения "Неверно указан срок действия карты"
     */
    public void invalidExpirationDateMessage() {
        $(By.xpath("//span[text()='Неверно указан срок действия карты']")).shouldBe(Condition.visible);
    }

    /**
     * Ищет наличие сообщения "Истек срок действия карты"
     */
    public void ExpirationDateMessage() {
        $(By.xpath("//span[text()='Истёк срок действия карты']")).shouldBe(Condition.visible);
    }


    /**
     * Ищет наличие сообщения "Поле обязательно для заполнения"
     */
    public void essentialField() {
        $(By.xpath("//span[text()='Поле обязательно для заполнения']")).shouldBe(Condition.visible);
    }

    /**
     * Сравнивает наличие нескольких сообщений об ошибках
     * Используется при отправке пустой формы
     */
    public void fillWithEmptyFields() {
        buyButton.click();
        enterButton.click();
        $(By.xpath("(//*[text()='Неверный формат'])[1]")).shouldBe(Condition.visible);
        $(By.xpath("(//*[text()='Неверный формат'])[2]")).shouldBe(Condition.visible);
        $(By.xpath("(//*[text()='Неверный формат'])[3]")).shouldBe(Condition.visible);
        $(By.xpath("(//*[text()='Неверный формат'])[4]")).shouldBe(Condition.visible);
        $(By.xpath("//span[text()='Поле обязательно для заполнения']")).shouldBe(Condition.visible);
    }

    /**
     * Нажимает на кнопку КУПИТЬ
     */
    public void pressTheBuyButton() {
        buyButton.click();
    }

    /**
     * Нажимает на кнопку КУПИТЬ В КРЕДИТ
     */
    public void pressTheCreditButton() {
        creditButton.click();
    }

    /**
     * Нажимает на кнопку ПРОДОЛЖИТЬ
     */
    public void pressTheEnterButton() {
        enterButton.click();
    }

    /**
     * Заполняет поле МЕСЯЦ значением, переданным в аргументах
     */
    public void fillTheFieldOfMonth(String month) {
        filedOfMonth.setValue(month);
    }

    /**
     * Заполняет поле ГОД значением, переданным в аргументах
     */
    public void fillTheFieldOfYear(String year) {
        filedOfMonth.setValue(year);
    }

    /**
     * Заполняет поле НОМЕР КАРТЫ значением, переданным в аргументах
     */
    public void fillTheFiledOfCardNumber(String cardNumber) {
        filedOfCardNumber.setValue(cardNumber);
    }

    /**
     * Заполняет поле ИМЯ значением, переданным в аргументах
     */
    public void fillTheFieldOfName(String name) {
        filedOfOwner.setValue(name);
    }

    /**
     * Заполняет поле CVV значением, переданным в аргументах
     */
    public void fillTheFieldOfCvv(String cvv) {
        filedOfCvv.setValue(cvv);
    }

}
