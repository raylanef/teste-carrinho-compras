package br.com.geofusion.cart;

import org.junit.Before;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.port;

public class BaseApi {
    @LocalServerPort
    private int porta;

    @Before
    public void setup() {
        baseURI = "http://localhost";
        port = porta;
    }
}
