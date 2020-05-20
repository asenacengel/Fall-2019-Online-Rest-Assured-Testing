package com.automation.tests.day03;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class ExchangeRatesAPITests {

    @BeforeAll
    public static void setUp(){
        // for every single request this is a base URI
        baseURI = "http://api.openrates.io";


    }
    // get latest curenncy rates
    @Test
    public void getLatestRates(){
        // q query parameter
        // zip another query parameter
        // with rest assured we provide query parameters into given() part.
        // give() request preparation
        //you can specify query parameters in URL explicity : http://api.openrates.io/latest?base=USD


        Response response = given().queryParam("base","USD").
                when().get("/latest").prettyPeek();

        // verify that GET request to the endpoint was successful
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("base",equalTo("USD"));
        // is same as equals


        Headers headers = response.getHeaders();
        String contentType = headers.getValue("Content-type");
        System.out.println(contentType);

        // verify that GET request to the endpoint was successful
        response.then().statusCode(200);
        response.then().assertThat().body("base",is("USD"));
        // lets verify that response contains today's date
        // this line returns todays date in the required format : yyyy-mm-dd
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        response.then().assertThat().body("date",containsString("2020-05-19"));
        // is - same as equals


    }
    // get history of rates
    @Test
    public void getHistoryOfRates(){
        Response response = given().queryParam("base","USD").
                when().get("/2008-01-02").prettyPeek();

        Headers headers = response.getHeaders();  // response header
        response.then().assertThat().statusCode(200)
                .and().body("date",is("2008-01-02"));
        //and() doesnt have a functional role its just a syntax sugar
        // we can chain validations
        // how we can retrive

        // rates its an object
        // all currencies are like instance variables
        // to get only instance variable (property) objectName.propertyName

        String param = response.jsonPath().get("rates.EUR").toString();


    }
}
