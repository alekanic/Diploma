package helpers;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Calendar;
import java.util.Random;

/**
 * Генерация валидных и невалидных данных для карты
 */
public class DataGeneration {

    private static final Faker ruFaker = new Faker(new Locale("ru", "RU"));
    Random random = new Random();

    /**
     * Возвращает рандомный номер карты
     * Возвращаемый тип: String
     */
    public static String generateCardNumber() { // генерирует рандомный номер карты
        String cardNumber = ruFaker.finance().creditCard(CreditCardType.MASTERCARD);
        return cardNumber;
    }

    /**
     * Возвращает захардкоженный номер карты со статусом APPROVED
     * Возвращаемый тип: String
     */
    public static String validCardNumber() {
        String validNumber = "4444 4444 4444 4441";
        return validNumber;
    }

    /**
     * Возвращает захардкоженный номер карты со статусом DECLINED
     * Возвращаемый тип: String
     */
    public static String invalidCardNumber() {
        String invalidNumber = "4444 4444 4444 4442";
        return invalidNumber;
    }

    /**
     * Возвращает значение месяца в неверном формате
     * Возвращаемый тип: String
     */
    public static String getInvalidFormatMonth() {
        int month = random.nextInt(10);
        return String.valueOf(month);
    }

    /**
     * Возвращает несуществующее значение месяца (от 13 до 99)
     * Возвращаемый тип: String
     */
    public static String getNonExistingMonth() {
        int min = 13;
        int max = 100;
        int dif = max - min;
        int month = random.nextInt(dif) + min;
        return String.valueOf(month);
    }

    /**
     * Возвращает текущий месяц в формате ММ
     * Возвращаемый тип: String
     */
    public static String getCurrentMonth() {
        String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern(("MM"))); 
        return currentMonth;
    }

    /**
     * Возвращает неверный формат года
     * Генерирует число от 0 до 9
     * Возвращаемый тип: String
     */
    public static String getInvalidFormatYear() {
        
        var year = random.nextInt(10);
        return String.valueOf(year);
    }

    /**
     * Возвращает текущий год в формате ГГ 
     * Возвращаемый тип: String
     */
    public static String getCurrentYear() {
        String currentYear = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        return currentYear;
    }

   /**
    * Возвращает рандомное полное имя RU
    * Возвращаемый тип: String
    */
    public static String getName() {
        String name = ruFaker.name().fullName();
        return name;
    }

    /**
     * Возвращает рандомный CVV
     * Возвращаемый тип: String
     */
    public static String getCVV() {
        String randomCVV = ruFaker.creditCardCVV();
        return randomCVV;
    }

    /**
     * Возвращает неверный формат СVV
     * Рандомное значение от 0 до 99
     * Возвращаемый тип: String
     */
    public static String getInvalidFormatCVV() {
        int CVV = random.nextInt(99);
        return String.valueOf(CVV);
    }
}
