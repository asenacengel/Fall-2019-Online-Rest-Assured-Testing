package com.automation.tests.Murodil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class SpartanTest {
    String spartanURL = "http://ec2-34-234-65-159.compute-1.amazonaws.com:8000/api/spartans/";


    @Test
    public void viewAllSpartans(){
        Response response =   RestAssured.get(spartanURL);
        System.out.println(response.statusCode());
      //  System.out.println(response.body().asString());
        response.body().prettyPrint();
        /*
        Given accept type is JSON
        When user sends a request to spartanURL
        Then response status code is 200
        And response body should json
        And response should contain "name" : "Chipotle"
         */

    }
    @Test
    public void viewAllSpartansTest2(){

        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(spartanURL);

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json;charset=UTF-8", response.contentType());

    }
    @Test
    public void viewAllSpartansTest3(){

       given().accept(ContentType.JSON).
                when().get(spartanURL)
       .then().statusCode(200).and().contentType("application/json;charset=UTF-8");

    }
    @Test
    public void getASpartanTest(){
        Response response = when().get(spartanURL+4);
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json;charset=UTF-8", response.contentType());
        Assertions.assertTrue(response.body().asString().contains("John Wick"));
    }
}
