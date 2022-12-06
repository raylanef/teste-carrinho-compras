package br.com.geofusion.cart;


import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;


import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BaseApi {

    private static final int PORT = 8080;
    private static final String HOST = "http://localhost";

    @BeforeAll
    public static void setup() {
        baseURI = HOST;
        port = PORT;
    }
}
