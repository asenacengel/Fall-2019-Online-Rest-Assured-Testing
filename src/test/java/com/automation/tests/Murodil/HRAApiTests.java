package com.automation.tests.Murodil;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HRAApiTests {

    String spartanURL = ConfigurationReader.getProperty("ORDS.URI");

    @Test
    public void getAllRegionTest(){

        Response response = RestAssured.get(spartanURL);
        System.out.println("Status Code: " +response.statusCode());
        System.out.println("content Type: "+response.contentType());
        response.body().prettyPrint();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertTrue(response.body().asString().contains("Americas"));
        Assertions.assertTrue(response.body().asString().contains("Europe"));
    }
}
