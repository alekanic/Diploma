import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {


    // генерация рандомных данных карты
    public static String randomCardNumber() {
        Faker faker = new Faker();
        String cardNumber = faker.finance().creditCard(CreditCardType.MASTERCARD);
        return cardNumber;
    }

    public static String validCardNumber() {
        String validNumber = "4444 4444 4444 4441";
        return validNumber;
    }

    // генерация валидного номера месяца
    public static String getMonth() {
        var months = new String[] {
                "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"
        };
        return months[new Random().nextInt(months.length)];
    }

    public static String getInvalidFormatMonth() {
        Random random = new Random();
        var month = random.nextInt(10);
        return String.valueOf(month);
    }

    public static String getYear() {
        // генерация валидного номера года
        // текущий год + 6 лет максимум

        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        int currentYear = Integer.parseInt(currentDate);
        Random random = new Random();
        var validYear = currentYear + random.nextInt(6);
        return String.valueOf(validYear);
    }

    public static String getInvalidFormatYear() {
        Random random = new Random();
        var year = random.nextInt(10);
        return String.valueOf(year);
    }

    // генерация имени
    public static String getName() {
        Faker faker = new Faker(new Locale("ru"));
        String name = faker.name().fullName();
        return name;
    }

    // генерация CVC/CVV
    public static String getCVV() {
        double CVV = Math.random() * 1000;
        return String.valueOf(CVV);
    }

    public static String getInvalidFormatCVV() {
        Random random = new Random();
        var CVV = random.nextInt(99);
        return String.valueOf(CVV);
    }


}
