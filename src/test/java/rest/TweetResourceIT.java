/* 
 * Copyright (C) 2019 Yannick
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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