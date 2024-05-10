package tests.frontend;

import com.google.gson.Gson;
import helpers.CardInformation;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;
import pages.PageObject;
import helpers.DataGeneration;
import pages.Specification;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Epic("Backend тестирование функционала Путешествие дня")
@Feature("Покупка тура по карте")
public class PaymentApiTests {
    private static CardInformation.CardData CardInformation;
    private static final RequestSpecification spec = new RequestSpecBuilder().setBaseUri("http://localhost").setPort(9999)
            .setAccept(ContentType.JSON).setContentType(ContentType.JSON).log(LogDetail.ALL).build();
    private static final String paymentUrl = "/payment";

  @BeforeEach
  void setup() {
      open("http://localhost:8080");
  }


@Test
@Description(value = "Проверяем открытие анкеты после нажатия на кнопку КУПИТЬ")

public void openFormAfterClickOnButtonBuy() {
    open("http://localhost:8080");
    PageObject page = new PageObject();
    page.openFormAfterClickOnButtonBuy();
}


@Test
@Description(value = "проверяем отправку валидных данных через сервис платежей (Payment Gate)")

public void successfulTestWithPaymentGate() {
    PageObject page = new PageObject();
    page.pressTheBuyButton();
    page.fillTheFiledOfCardNumber(DataGeneration.validCardNumber());
    // добавить дату
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
        page.pressTheBuyButton();
        // добавить дату
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с данными в неверном формате в поле МЕСЯЦ")

    public void testWithEmptyFieldOfMonth() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        // добавить дату
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с незаполненным полем ГОД")

    public void testWithEmptyFieldOfYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        // добавить дату
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с незаполненным полем ВЛАДЕЛЕЦ")

    public void testWithEmptyFieldOfOwner() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        // добавить дату
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.essentialField();

    }

    @Test
    @Description(value = "проверяем отправку анкеты с незаполненным полем CVC/CVV")

    public void testWithEmptyFieldOfCvv() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        // добавить дату
        page.fillTheFieldOfName(DataGeneration.getName());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test
    @Description(value = "Проверяем отправку анкеты с невалидным номером карты (падает если равен карте DECLINED)")

    public void testWithInvalidCardNumber() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.invalidCardNumber());
        // добавить дату
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.failNotification();
    }

    @Test
    @Description(value = "Проверяем отправку анкеты с картой со статусом DECLINED (падает)")

    public void testWithDeclinedCard() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber("4444 4444 4444 4442");
        // добавить дату
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.failNotification();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с данными в неверном формате в поле МЕСЯЦ")

    public void testWithInvalidFormatMonth() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getInvalidFormatMonth());
        // добавить дату
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с данными в неверном формате в поле МЕСЯЦ")

    public void testWithNonExistentFormatMonth() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        // добавить дату
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с закончившимся в текущем году сроком действия карты")

    public void testWithExpiredValidityPeriod() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        // добавить дату
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidExpirationDateMessage();
    }

    @Test
    @Description(value = "проверяем отправку анкеты, когда в поле ГОД значение меньше текущего года")

    public void testWithPreviousYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        // добавить дату
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.ExpirationDateMessage();
    }

    @Test
    @Description(value = "проверяем отправку анкеты, когда в поле ГОД значение больше текущего на 6 и более лет")

    public void testWitFutureYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        // добавить дату
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidExpirationDateMessage();
    }

    @Test
    @Description(value = "проверяем отправку анкеты с данными в неверном формате в поле ГОД")

    public void testWithInvalidFormatYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        // добавить дату
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
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        // добавить дату
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getInvalidFormatCVV());
        page.pressTheEnterButton();
        page.invalidFormatMessage();
    }

}