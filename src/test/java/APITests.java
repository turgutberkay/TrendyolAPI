import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class APITests {

    @BeforeClass
    public static void setup() {
        // API'nin base URL'si buraya yazılmalıdır
        RestAssured.baseURI = "http://api_url";
    }

    //Dogru parametreler verildiginde toplama isleminin sonucu ve 200 Ok testi
    @Test
    public void testAddition() {
        given()
                .queryParam("params", "1,2")
                .auth().preemptive().basic("username", "password")
                .when()
                .post("/add")
                .then()
                .statusCode(200)
                .body("result", equalTo(3));
    }

    //Dogru parametreler verildiginde cıkarma isleminin sonucu ve 200 Ok testi
    @Test
    public void testSubtraction() {
        given()
                .queryParam("params", "5,2")
                .auth().preemptive().basic("username", "password")
                .when()
                .post("/subtraction")
                .then()
                .statusCode(200)
                .body("result", equalTo(3));
    }

    //Dogru parametreler verildiginde carpma isleminin sonucu ve 200 Ok testi
    @Test
    public void testMultiplication() {
        given()
                .queryParam("params", "3,2,3")
                .auth().preemptive().basic("username", "password")
                .when()
                .post("/multiplication")
                .then()
                .statusCode(200)
                .body("result", equalTo(18));
    }

    //Dogru parametreler verildiginde bolme isleminin sonucu ve 200 Ok testi
    @Test
    public void testDivision() {
        given()
                .queryParam("params", "10,2")
                .auth().preemptive().basic("username", "password")
                .when()
                .post("/division")
                .then()
                .statusCode(200)
                .body("result", equalTo(5)); // 10 / 2 = 5 olmalı
    }

    //5 ten fazla parametre girildiginde 400 bad request testi
    @Test
    public void testInvalidParameterCount() {
        given()
                .queryParam("params", "1,2,3,4,5,6")
                .auth().preemptive().basic("username", "password")
                .when()
                .post("/add")
                .then()
                .statusCode(400);
    }

    //Auth parametreleri girilmedigi icin 400 bad request testi
    @Test
    public void testAuthenticationFailure() {
        given()
                .when()
                .post("/add?params=1,2")
                .then()
                .statusCode(400);
    }

    // Delete methodu kullanıldıgı icin 405 Method Not Allowed testi(put icinde yazilabilir)
    @Test
    public void testUnsupportedMethod() {
        given()
                .when()
                .delete("/add?params=1,2")
                .then()
                .statusCode(405);
    }


    // n sayısının 0 dan n e kadar toplamının sonucu veren ve 200 testi
    @Test
    public void testSum() {
        given()
                .queryParam("params", "5")
                .auth().preemptive().basic("username", "password")
                .when()
                .get("/sum")
                .then()
                .statusCode(200)
                .body("result", equalTo(15));
    }
}
