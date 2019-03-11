/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.github.tomakehurst.wiremock.client.WireMock;
import static com.jayway.restassured.RestAssured.given;
import java.io.File;
import java.net.URL;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Yannick
 */
@RunWith(Arquillian.class)
public class TweetResourceTest {
    
    private final WireMock wiremock = new WireMock(8080);
    
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        File[] files = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .importRuntimeDependencies()
                .resolve()
                .withTransitivity()
                .asFile();
        return ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "nl.fhict.jea")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/beans.xml"))
                .addAsLibraries(files);
    }

    @ArquillianResource
    private URL contextPath;
    
    
    private String getPath() {
        return this.contextPath.toString() + "kwetter/api";
    }
    
    @Test
    @InSequence(1)
    public void testGetTweets() {
        given()
                .when()
                .get("localhost:8080/kwetter/api/tweets")
                .then()
                .statusCode(200);
    }
    
    @Test
    public void getTweets() {
       
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
