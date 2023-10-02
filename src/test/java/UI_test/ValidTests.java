package UI_test;

import UI_test.DataHelper;
import UI_test.MonthAndYear;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ValidTests {

    private final SelenideElement buyButton = $(byText("Купить"));
    private final SelenideElement creditButton = $(byText("Купить в кредит"));
    private final SelenideElement filedOfCardNumber = $(By.xpath("//input[@placeholder='0000 0000 0000 0000']"));
    private final SelenideElement filedOfOwner = $(By.xpath("//span[text()='Владелец']/../span[@class='input__box']/input"));
    private final SelenideElement filedOfCvv = $(By.xpath("//input[@placeholder='999']"));
    private final SelenideElement enterButton = $(By.xpath("//span[text()='Продолжить']/../.."));
    private final SelenideElement successNotification = $(By.xpath("//div[text()='Успешно']"));
    private final SelenideElement failNotification = $(By.xpath("//div[text()='Ошибка']"));


    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }


    @Test
    // Сценарий 1 - Проверяем открытие анкеты после нажатия на кнопку "Купить"

    public void openFormAfterClickOnButtonBuy() {
        buyButton.click();
        final SelenideElement name = $(By.xpath("//h3[text()='Оплата по карте']"));
        name.shouldBe(Condition.visible);
    }

    @Test
    // Сценарий 2 - Проверяем открытие анкеты после нажатия на кнопку "Купить в кредит"

    public void openFormAfterClickOnButtonCredit() {
        creditButton.click();
        final SelenideElement name = $(By.xpath("//h3[text()='Кредит по данным карты']"));
        name.shouldBe(Condition.visible);
    }


    @Test
    //Сценарий 3 - проверяем отправку валидных данных через сервис платежей (Payment Gate)

    public void successfulTestWithPaymentGate() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.validCardNumber());

        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();

        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    //Сценарий 4 - проверяем отправку валидных данных через кредитный сервис (Credit Gate)

    public void successfulTestWithCreditGate() {
        creditButton.click();
        filedOfCardNumber.setValue(DataHelper.validCardNumber());

        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();

        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}


