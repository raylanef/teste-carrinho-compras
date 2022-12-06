package br.com.geofusion.cart.controllers;

import br.com.geofusion.cart.BaseApi;

import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;



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
