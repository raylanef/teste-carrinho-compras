package br.com.geofusion.cart.controllers;

import br.com.geofusion.cart.BaseApi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingCartFactoryControllerTest extends BaseApi {


    @Test
    public void postValidCart(){
        given()
                .when()
                .post("/carts/clientXptO")
                .then()
                .statusCode(201);
    }
}
