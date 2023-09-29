import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import javax.xml.crypto.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.SetValueOptions.withText;

public class Tests {

    private final SelenideElement buyButton = $(byText("Купить"));
    private final SelenideElement creditButton = $(byText("Купить в кредит"));
    private final SelenideElement filedOfCardNumber = $(By.xpath("//input[@placeholder='0000 0000 0000 0000']"));
    private final SelenideElement filedOfMonth = $(By.xpath("//input[@placeholder='08']"));
    private final SelenideElement filedOfYear = $(By.xpath("//input[@placeholder='22']"));
    private final SelenideElement filedOfOwner = $(By.xpath("//span[text()='Владелец']/../span[@class='input__box']/input"));
    private final SelenideElement filedOfCvv = $(By.xpath("//input[@placeholder='999']"));

    String currentYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));


    @BeforeEach

    void setup() { open("http://localhost:8080"); }

    //Сценарий успешной отправки валидных данных
    @Test

    public void test() {
        buyButton.click();
        filedOfCardNumber.setValue(DataHelper.validCardNumber());

        // Получить год с помощью функции, которая выдает рандомное число от текущего года до + 6 лет вперед
        // Проверить, равен ли он текущему году
        // Если равен, то месяц должен быть больше текущего на 1
        // Если текущий месяц = 12, то год должен быть +1

        var getYear = DataHelper.getYear();
        var getMonth = DataHelper.getMonth();

        if (getYear == currentYear) {                            // если полученное значение равно текущему году
            if (getMonth == "12") {                              // если полученный месяц = 12
                filedOfMonth.setValue(getMonth);                 // установить полученный месяц
                var finalYear = Integer.parseInt(getYear) + 1;   // прибавить к текущему году + 1
                filedOfYear.setValue(String.valueOf(finalYear)); // установить полученное значение
            } else {
                filedOfYear.setValue(getYear);
                var finalMonth = Integer.parseInt(getMonth) + 1;
                filedOfMonth.setValue(String.valueOf(finalMonth));
            }
        } else {
            filedOfYear.setValue(getYear);                        // если полученный год не равен текущему,
            filedOfMonth.setValue(getMonth);                      // то просто выставляем полученные значения
        }

        filedOfOwner.setValue(DataHelper.getName());
        filedOfCvv.setValue(DataHelper.getCVV());

    }

}
