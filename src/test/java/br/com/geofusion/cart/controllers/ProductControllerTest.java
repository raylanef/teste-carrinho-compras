package br.com.geofusion.cart.controllers;

import br.com.geofusion.cart.BaseApi;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class ProductControllerTest extends BaseApi {

    @Test
    public void postValidProduct(){
        given()
                .body("{\"code\":1345,\"description\":\"producttest\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/products")
                .then()
                .statusCode(201);
    }

    @Test
    public void postInvalidProduct(){
        given()
                .body("{\"description\":\"producttest\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/products")
                .then()
                .statusCode(400);
    }

    @Test
    public void getProducts(){
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteValidProductByIndex(){
        given()
                .when()
                .delete("/products/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void deleteInvalidProductByIndex(){
        given()
                .when()
                .delete("/products/100")
                .then()
                .statusCode(404);
    }
}
