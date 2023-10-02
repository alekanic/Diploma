package ui_tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class EmpyTest {

    private final SelenideElement buyButton = $(byText("Купить"));
    private final SelenideElement filedOfCardNumber = $(By.xpath("//input[@placeholder='0000 0000 0000 0000']"));
    private final SelenideElement filedOfMonth = $(By.xpath("//input[@placeholder='08']"));
    private final SelenideElement filedOfYear = $(By.xpath("//input[@placeholder='22']"));
    private final SelenideElement filedOfOwner = $(By.xpath("//span[text()='Владелец']/../span[@class='input__box']/input"));
    private final SelenideElement filedOfCvv = $(By.xpath("//input[@placeholder='999']"));
    private final SelenideElement enterButton = $(By.xpath("//span[text()='Продолжить']/../.."));
    private final SelenideElement failNotification = $(By.xpath("//div[text()='Ошибка']"));

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    //Сценарий 14 - проверяем отправку анкеты с пустыми полями

    public void testWithEmptyFields() {
        buyButton.click();
        enterButton.click();
        $(By.xpath("(//*[text()='Неверный формат'])[1]")).shouldBe(Condition.visible);
        $(By.xpath("(//*[text()='Неверный формат'])[2]")).shouldBe(Condition.visible);
        $(By.xpath("(//*[text()='Неверный формат'])[3]")).shouldBe(Condition.visible);
        $(By.xpath("(//*[text()='Неверный формат'])[4]")).shouldBe(Condition.visible);
        $(By.xpath("//span[text()='Поле обязательно для заполнения']")).shouldBe(Condition.visible);
    }

    @Test
    //Сценарий 15 - проверяем отправку анкеты с незаполненным полем "Номер карты"

    public void testWithEmptyFieldOfCardNumber() {
        buyButton.click();

        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();

        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        $(By.xpath("(//*[text()='Неверный формат'])")).shouldBe(Condition.visible);
    }

    @Test
    //Сценарий 16 - проверяем отправку анкеты с данными в неверном формате в поле "Месяц"

    public void testWithEmptyFieldOfMonth() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.randomCardNumber());
        filedOfYear.setValue(DataHelper.getYear());
        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        $(By.xpath("(//*[text()='Неверный формат'])")).shouldBe(Condition.visible);
    }

    @Test
    //Сценарий 17 - проверяем отправку анкеты с незаполненным полем "Год"

    public void testWithEmptyFieldOfYear() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.randomCardNumber());
        filedOfMonth.setValue(DataHelper.getMonth());
        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        $(By.xpath("(//*[text()='Неверный формат'])")).shouldBe(Condition.visible);
    }

    @Test
    //Сценарий 18 - проверяем отправку анкеты с незаполненным полем "Владелец"

    public void testWithEmptyFieldOfOwner() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.randomCardNumber());

        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();

        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        $(By.xpath("//span[text()='Поле обязательно для заполнения']")).shouldBe(Condition.visible);
    }

    @Test
    //Сценарий 19 - проверяем отправку анкеты с незаполненным полем "CVC/CVV"

    public void testWithEmptyFieldOfCvv() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.randomCardNumber());

        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();

        filedOfOwner.setValue(DataHelper.getName());

        enterButton.click();
        $(By.xpath("(//*[text()='Неверный формат'])")).shouldBe(Condition.visible);
    }

}
