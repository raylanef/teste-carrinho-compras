package br.com.geofusion.cart.controllers;


import br.com.geofusion.cart.BaseApi;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest  extends BaseApi {

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
