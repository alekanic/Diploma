import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestForTest {

    @Test

    public void test() {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
        System.out.println(currentDate);
    }

}
