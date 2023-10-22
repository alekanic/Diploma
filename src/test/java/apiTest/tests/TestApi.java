package apiTest.tests;

import apiTest.pages.Specification;
import apiTest.test_data.CardInformation;
import org.junit.jupiter.api.Test;
import ui_tests.test_data.DataHelper;

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
        CardInformation reg = new CardInformation(DataHelper.validCardNumber(), DataHelper.getYear(), DataHelper.getMonth(), DataHelper.getName(), DataHelper.getCVV());
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
        CardInformation reg = new CardInformation(DataHelper.validCardNumber(), DataHelper.getYear(), DataHelper.getMonth(), DataHelper.getName(), DataHelper.getCVV());
        given()
                .baseUri(RegresTest.URL)
                .body(reg)
                .when()
                .post("/api/v1/credit")
                .then()
                .statusCode(200);

    }

}
