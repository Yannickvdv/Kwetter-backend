/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Yannick
 */
public class TweetResourceIT {
    

    @Before
    public void setUp() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/kwetter/api/";
    }
    
    @Test
    public void getTweets() {
        given()
        .when()
            .get("/tweets")
        .then()
            .statusCode(200)
        .log().all();
    }
}