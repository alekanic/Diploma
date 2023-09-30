import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestWIthInvalidData {

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
    //Сценарий 5 - Проверяем отправку анкеты с невалидным номером карты

    public void testWithInvalidCardNumber() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.randomCardNumber());

        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();

        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        failNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    //Сценарий 6 - Проверяем отправку анкеты с картой со статусом DECLINED

    public void testWithDeclinedCard() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.invalidCardNumber());

        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();

        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        failNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    //Сценарий 7 - проверяем отправку анкеты с данными в неверном формате в поле "Месяц"

    public void testWithInvalidFormatMonth() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.randomCardNumber());
        filedOfMonth.setValue(DataHelper.getInvalidFormatMonth());
        filedOfYear.setValue(DataHelper.getYear());
        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        $(By.xpath("//span[text()='Неверный формат']")).shouldBe(Condition.visible);
    }

    @Test
    //Сценарий 8 - проверяем отправку анкеты с данными в неверном формате в поле "Месяц"

    public void testWithNonExistentFormatMonth() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.randomCardNumber());
        filedOfMonth.setValue(DataHelper.getNonExistentFormatMonth());
        filedOfYear.setValue(DataHelper.getYear());
        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        $(By.xpath("//span[text()='Неверный формат']")).shouldBe(Condition.visible);
    }

    @Test
    //Сценарий 9 - проверяем отправку анкеты с закончившимся в текущем году сроком действия карты

    public void testWithExpiredValidityPeriod() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.randomCardNumber());

        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillTheExpiredInThisYearValidityPeriod();

        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        $(By.xpath("//span[text()='Неверно указан срок действия карты']")).shouldBe(Condition.visible);
    }

    @Test
    //Сценарий 10 - проверяем отправку анкеты, когда в поле "Год" значение меньше текущего года

    public void testWithPreviousYear() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.randomCardNumber());

        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillTheExpiredValidPeriod();

        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        $(By.xpath("//span[text()='Истёк срок действия карты']")).shouldBe(Condition.visible);
    }

    @Test
    //Сценарий 11 - проверяем отправку анкеты, когда в поле "Год" значение больше текущего на 6 и более лет

    public void testWitFutureYear() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.randomCardNumber());

        MonthAndYear expiredMonth = new MonthAndYear();
        expiredMonth.fillWithFutureDate();

        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        $(By.xpath("//span[text()='Неверно указан срок действия карты']")).shouldBe(Condition.visible);
    }

    @Test
    //Сценарий 12 - проверяем отправку анкеты с данными в неверном формате в поле "Год"

    public void testWithInvalidFormatYear() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.randomCardNumber());
        filedOfMonth.setValue(DataHelper.getMonth());
        filedOfYear.setValue(DataHelper.getInvalidFormatYear());
        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

        enterButton.click();
        $(By.xpath("//span[text()='Неверный формат']")).shouldBe(Condition.visible);
    }

    @Test
    //Сценарий 13 - проверяем отправку анкеты с данными в неверном формате в поле "Год"

    public void testWithInvalidFormatCvv() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.randomCardNumber());

        MonthAndYear fill = new MonthAndYear();
        fill.validFillTheMonthAndYearFields();

        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getInvalidFormatCVV());

        enterButton.click();
        $(By.xpath("//span[text()='Неверный формат']")).shouldBe(Condition.visible);
    }

}


