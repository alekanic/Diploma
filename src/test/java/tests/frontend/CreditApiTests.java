package tests.frontend;

import helpers.CardInformation;
import helpers.MonthAndYear;
import io.qameta.allure.Description;
import io.qameta.allure.Epics;
import pages.PageObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import helpers.DataGeneration;
import pages.Specification;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CreditApiTests {

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    @Description(value = "Проверяем открытие анкеты после нажатия на кнопку КУПИТЬ В КРЕДИТ")
    
    public void openFormAfterClickOnButtonCredit() {
        PageObject page = new PageObject();
        page.openFormAfterClickOnButtonCredit();
    }

    @Test
    @Description(value = "проверяем отправку валидных данных через кредитный сервис (Credit Gate)")

    public void successfulTestWithCreditGate() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataGeneration.validCardNumber());
        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.successNotification();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с пустыми полями")

    public void testWithEmptyFields() {
        PageObject page = new PageObject();
        page.fillWithEmptyFields();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с незаполненным полем НОМЕР КАРТЫ")

    public void fillWithEmptyFieldOfCardNumber() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFieldOfMonth(DataGeneration.getMonth());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с данными в неверном формате в поле МЕСЯЦ")

    public void testWithEmptyFieldOfMonth() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с незаполненным полем ГОД")

    public void testWithEmptyFieldOfYear() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getMonth());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с незаполненным полем ВЛАДЕЛЕЦ")

    public void testWithEmptyFieldOfOwner() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getMonth());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.essentialField();

    }

    @Test
    @Description(value = "проверяем отправку анкеты с незаполненным полем CVC/CVV")

    public void testWithEmptyFieldOfCvv() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getMonth());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test
    @Description(value = "Проверяем отправку анкеты с невалидным номером карты (падает если равен карте DECLINED)")

    public void testWithInvalidCardNumber() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataGeneration.invalidCardNumber());
        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.failNotification();
    }

    @Test
    @Description(value = "Проверяем отправку анкеты с картой со статусом DECLINED (падает)")

    public void testWithDeclinedCard() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber("4444 4444 4444 4442");
        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.failNotification();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с данными в неверном формате в поле МЕСЯЦ")

    public void testWithInvalidFormatMonth() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getInvalidFormatMonth());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с данными в неверном формате в поле МЕСЯЦ")

    public void testWithNonExistentFormatMonth() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getNonExistentFormatMonth());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с закончившимся в текущем году сроком действия карты")

    public void testWithExpiredValidityPeriod() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillTheExpiredInThisYearValidityPeriod();
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidExpirationDateMessage();
    }

    @Test
    @Description(value = "проверяем отправку анкеты, когда в поле ГОД значение меньше текущего года")

    public void testWithPreviousYear() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillTheExpiredValidPeriod();
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.ExpirationDateMessage();
    }

    @Test
    @Description(value = "проверяем отправку анкеты, когда в поле ГОД значение больше текущего на 6 и более лет")

    public void testWitFutureYear() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillWithFutureDate();
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidExpirationDateMessage();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с данными в неверном формате в поле ГОД")

    public void testWithInvalidFormatYear() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getMonth());
        page.fillTheFieldOfYear(DataGeneration.getInvalidFormatYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с данными в неверном формате в поле ГОД")

    public void testWithInvalidFormatCvv() {
        PageObject page = new PageObject();
        page.pressTheCreditButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getMonth());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getInvalidFormatCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

}
