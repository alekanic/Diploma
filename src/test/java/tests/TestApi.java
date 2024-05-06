package tests;

import pages.Specification;
import helpers.CardInformation;
import org.junit.jupiter.api.Test;
import helpers.DataGeneration;

import static io.restassured.RestAssured.given;


public class TestApi {

    public class RegresTest {
        private final static String URL = "http://localhost:8080";
    }

    @Test
    void successfulTestWithPaymentGate() {
        // Given - When - Then
        // Предусловия
        Specification.installSpecification(Specification.requestSpec(RegresTest.URL), Specification.responseSpecOK200());
        CardInformation reg = new CardInformation(DataGeneration.validCardNumber(), DataGeneration.getYear(), DataGeneration.getMonth(), DataGeneration.getName(), DataGeneration.getCVV());
        given()
                .baseUri(RegresTest.URL)
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
        CardInformation reg = new CardInformation(DataGeneration.validCardNumber(), DataGeneration.getYear(), DataGeneration.getMonth(), DataGeneration.getName(), DataGeneration.getCVV());
        given()
                .baseUri(RegresTest.URL)
                .body(reg)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200);

    }

}
