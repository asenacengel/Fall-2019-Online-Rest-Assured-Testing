package com.automation.tests.day02;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SpartanTests {

    String BASE_URL = "http://3.90.112.152:8000";

    //URI (uniform resource Identifier) = url + urn
    //URL (uniform resource locator) = http// www.google.com
    //URN (uniform resource name) = /index.html

    @Test
    @DisplayName("Get list of all spartans")
    public void getAllSpartans(){

        // 401 - unouthorized
        // how to provide credentials
        // there are different types of authentication: basic auth 1.0 outh 2.0 api key bearer token etc..
        // spartan app requires basic authentication : username and password

        given().auth().basic("admin","admin").baseUri(BASE_URL)
                .when().get("/api/spartans").prettyPeek().then().statusCode(200);


    }

    // add new spartan
    @Test
    @DisplayName("Add new spartan")
    public void addNewSpartan(){

        //JSON supports different data types: string integer boolean
        String body = "{\"gender\": \"Male\" \"name\": \"SDET\",\"phone\" :273645647839}";
        //Instead of string variable we can use external JSON file
        //use file class to read JSON and pass it into body
        //provide path to the JSON as a parameter
        File jsonFile = new File(System.getProperty("user.dir")+"/spartan.json");

        // to create new item , we perform POST request
        given().
                contentType(ContentType.JSON).
                auth().basic("admin","admin").body(body).baseUri(BASE_URL).when()
                .post("/api/spartans")
                .prettyPeek().then().statusCode(200);
    }
    // you can not delete something twice
    //204 No content most common status code for successful delete action
    //201 always for after successful POST request
    //200 always after successful GET request
    //4XX always after unsuccessful request and it was your FAULT
    @Test
    @DisplayName("Delete some spartan")
    public void deleteSpartanTest(){
        given().auth().basic("admin","admin").baseUri(BASE_URL)
                .when().delete("/api/spartans/{id}",792)
                .prettyPeek().
                then().statusCode(204);

    }
}
