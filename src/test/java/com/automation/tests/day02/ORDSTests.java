package com.automation.tests.day02;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


public class ORDSTests {

    String BASE_URL = "http://54.224.118.38:1000/ords/hr";

    @Test
   @DisplayName("Gel list of all employees")
    public void getAllEmployees(){

        // response can be saved in the Response object
        //PrettyPeek () - method that prints response info in nice format
        // also this method returns Respond object
        // response contains body , header and status line
        //body (payload) - contains content that we request from the web service
        // header - contains meta deta
        Response response =
            given().baseUri(BASE_URL).when().get("/employees").prettyPeek();

        /*
        RestAssured request has similar structure to BDD scenarios:
        give() - used for request setup and authentication
        when() - to specify type of HTTP request : get put post delete patch head etc...
        then() - to verify response perform assertions
         */

    }
    @Test
    @DisplayName("Get employee under specific ID")
    public void getOneEmployee(){
        // in URL we can specify path and query parameters
        // path parameters are used to retrive specific resource for example 1 employee not all of them
        //{id} - path variable, that will be replace with a value after comma

        Response response = given().baseUri(BASE_URL).when().get("/employee/{id}",100).prettyPeek();

        // how to verify respsonse? use assertion
        response.then().statusCode(200); // to verify that status is 200


        int statusCode = response.statusCode(); //to save status code in variable
        Assertions.assertEquals(200,statusCode );

        /**
         * given base URI =  "http://54.224.118.38:1000/ords/hr";
         * when user sends get request to "/countries"
         * then user verifies that status code is 200
         */

    }
    @Test
    @DisplayName("Get list of all countries and verify that status code is 200")
    public void getAllCountries(){

        given().baseUri(BASE_URL).when().get("/countries").prettyPeek().then().statusCode(200).statusLine("OK");
    }



    }
