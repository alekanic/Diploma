package apiTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class TestApi {

    public class RegresTest {
        private final static String URL = "https://localhost:8080";
    }

    @Test
    void successfulTestWithPaymentGate() {
        // Given - When - Then
        // Предусловия
        Specification.installSpecification(Specification.requestSpec(RegresTest.URL), Specification.responseSpecOK200());
        CardInformation reg = new CardInformation("4444 4444 4444 4441", "25", "12", "Ivanov", "123");
        given()
                .baseUri("http://localhost:8080")
                .body(reg)
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200);

    }

    @Test
    void successfulTestWithCreditGate() {
        // Given - When - Then
        // Предусловия
        Specification.installSpecification(Specification.requestSpec(RegresTest.URL), Specification.responseSpecOK200());
        CardInformation reg = new CardInformation("4444 4444 4444 4441", "25", "12", "Ivanov", "123");
        given()
                .baseUri("http://localhost:8080")
                .body(reg)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200);

    }

}
