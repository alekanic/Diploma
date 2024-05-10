package helpers;

import lombok.Value;

// Класс с данными для заполнения анкеты и методом заполнения анкеты
public class CardInformation {

    // Структура, содержащая всю информацию с карты
    @Value
    public static class CardData {
        private final String number;
        private final String month;
        private final String year;
        private final String holder;
        private final String cvc;
    }
}

