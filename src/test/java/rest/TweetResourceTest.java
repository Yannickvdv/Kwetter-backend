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
public class TweetResourceTest {
    
    public TweetResourceTest() {
    }
    
    @Before
    public void setUp() {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/kwetter/api";
    }
    
    @Test
    public void getTweets() {
        given().when().get("/tweets").then().statusCode(200);
    }

//    @Test
//    public void addUser() {
//        User user = new User("Henk", "test", Language.Dutch);
//        
//        given()
//            .contentType("application/json")
//            .body(user)
//        .when()
//            .post("/users")
//        .then()
//            .statusCode(200);
//    }
}
