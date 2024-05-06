package tests;

import pages.PageObject;
import helpers.DataGeneration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import helpers.MonthAndYear;

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
        page.fillTheFiledOfCardNumber(DataGeneration.invalidCardNumber());
        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
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
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.failNotification();
    }

    @Test
    //Сценарий 7 - проверяем отправку анкеты с данными в неверном формате в поле "Месяц"

    public void testWithInvalidFormatMonth() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getInvalidFormatMonth());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test
    //Сценарий 8 - проверяем отправку анкеты с данными в неверном формате в поле "Месяц"

    public void testWithNonExistentFormatMonth() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getNonExistentFormatMonth());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test
    //Сценарий 9 - проверяем отправку анкеты с закончившимся в текущем году сроком действия карты

    public void testWithExpiredValidityPeriod() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillTheExpiredInThisYearValidityPeriod();
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidExpirationDateMessage();
    }

    @Test
    //Сценарий 10 - проверяем отправку анкеты, когда в поле "Год" значение меньше текущего года

    public void testWithPreviousYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillTheExpiredValidPeriod();
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.ExpirationDateMessage();
    }

    @Test
    //Сценарий 11 - проверяем отправку анкеты, когда в поле "Год" значение больше текущего на 6 и более лет

    public void testWitFutureYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillWithFutureDate();
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidExpirationDateMessage();
    }

    @Test
    //Сценарий 12 - проверяем отправку анкеты с данными в неверном формате в поле "Год"

    public void testWithInvalidFormatYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getMonth());
        page.fillTheFieldOfYear(DataGeneration.getInvalidFormatYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test
    //Сценарий 13 - проверяем отправку анкеты с данными в неверном формате в поле "Год"

    public void testWithInvalidFormatCvv() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getMonth());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getInvalidFormatCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

}


