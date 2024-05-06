package helpers;

// Класс с данными для заполнения анкеты и методом заполнения анкеты
public class CardInformation {
    private String number, year, month, holder, cvc;

    // Структура, содержащая всю информацию с карты
    public CardInformation(String number, String year, String month, String holder, String cvc) {
        this.number = number;
        this.month = month;
        this.year = year;
        this.holder = holder;
        this.cvc = cvc;
    }

    // Далее геттеры
    public String getNumber() {
        return number;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getHolder() {
        return holder;
    }

    public String getCvc() {
        return cvc;
    }
}


