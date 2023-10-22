package ui_tests.tests;

import apiTest.pages.PageObject;
import ui_tests.test_data.DataHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ui_tests.test_data.MonthAndYear;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestWIthInvalidData {

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    //Сценарий 5 - Проверяем отправку анкеты с невалидным номером карты (падает если равен карте DECLINED)

    public void testWithInvalidCardNumber() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.invalidCardNumber());
        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.failNotification();
    }

    @Test
    //Сценарий 6 - Проверяем отправку анкеты с картой со статусом DECLINED (падает)

    public void testWithDeclinedCard() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber("4444 4444 4444 4442");
        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.failNotification();
    }

    @Test
    //Сценарий 7 - проверяем отправку анкеты с данными в неверном формате в поле "Месяц"

    public void testWithInvalidFormatMonth() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        page.fillTheFieldOfMonth(DataHelper.getInvalidFormatMonth());
        page.fillTheFieldOfYear(DataHelper.getYear());
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test
    //Сценарий 8 - проверяем отправку анкеты с данными в неверном формате в поле "Месяц"

    public void testWithNonExistentFormatMonth() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        page.fillTheFieldOfMonth(DataHelper.getNonExistentFormatMonth());
        page.fillTheFieldOfYear(DataHelper.getYear());
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test
    //Сценарий 9 - проверяем отправку анкеты с закончившимся в текущем году сроком действия карты

    public void testWithExpiredValidityPeriod() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillTheExpiredInThisYearValidityPeriod();
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.invalidExpirationDateMessage();
    }

    @Test
    //Сценарий 10 - проверяем отправку анкеты, когда в поле "Год" значение меньше текущего года

    public void testWithPreviousYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillTheExpiredValidPeriod();
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.ExpirationDateMessage();
    }

    @Test
    //Сценарий 11 - проверяем отправку анкеты, когда в поле "Год" значение больше текущего на 6 и более лет

    public void testWitFutureYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillWithFutureDate();
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.invalidExpirationDateMessage();
    }

    @Test
    //Сценарий 12 - проверяем отправку анкеты с данными в неверном формате в поле "Год"

    public void testWithInvalidFormatYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        page.fillTheFieldOfMonth(DataHelper.getMonth());
        page.fillTheFieldOfYear(DataHelper.getInvalidFormatYear());
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test
    //Сценарий 13 - проверяем отправку анкеты с данными в неверном формате в поле "Год"

    public void testWithInvalidFormatCvv() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataHelper.randomCardNumber());
        page.fillTheFieldOfMonth(DataHelper.getMonth());
        page.fillTheFieldOfYear(DataHelper.getYear());
        page.fillTheFieldOfName(DataHelper.getName());
        page.fillTheFieldOfCvv(DataHelper.getInvalidFormatCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

}


