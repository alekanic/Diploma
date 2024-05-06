package tests;

import pages.PageObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import helpers.DataGeneration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class EmptyTest {

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    //Сценарий 14 - проверяем отправку анкеты с пустыми полями

    public void testWithEmptyFields() {
        PageObject page = new PageObject();
        page.fillWithEmptyFields();
    }

    @Test
    //Сценарий 15 - проверяем отправку анкеты с незаполненным полем "Номер карты"

    public void fillWithEmptyFieldOfCardNumber() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFieldOfMonth(DataGeneration.getMonth());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test
    //Сценарий 16 - проверяем отправку анкеты с данными в неверном формате в поле "Месяц"

    public void testWithEmptyFieldOfMonth() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test
    //Сценарий 17 - проверяем отправку анкеты с незаполненным полем "Год"

    public void testWithEmptyFieldOfYear() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getMonth());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

    @Test
    //Сценарий 18 - проверяем отправку анкеты с незаполненным полем "Владелец"

    public void testWithEmptyFieldOfOwner() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getMonth());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfCvv(DataGeneration.getCVV());
        page.pressTheEnterButton();
        page.essentialField();

    }

    @Test
    //Сценарий 19 - проверяем отправку анкеты с незаполненным полем "CVC/CVV"

    public void testWithEmptyFieldOfCvv() {
        PageObject page = new PageObject();
        page.pressTheBuyButton();
        page.fillTheFiledOfCardNumber(DataGeneration.generateCardNumber());
        page.fillTheFieldOfMonth(DataGeneration.getMonth());
        page.fillTheFieldOfYear(DataGeneration.getYear());
        page.fillTheFieldOfName(DataGeneration.getName());
        page.pressTheEnterButton();
        page.invalidFormat();
    }

}
