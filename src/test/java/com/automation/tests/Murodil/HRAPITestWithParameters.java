package com.automation.tests.Murodil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

import com.automation.utilities.ConfigurationReader;

public class HRAPITestWithParameters {



    @BeforeAll
    public static void setUp() {

        baseURI = ConfigurationReader.getProperty("ORDS.URI");

    }
    @Test
      public void getCountries_using_queryParameter(){
      Response response =   given().accept(ContentType.JSON).
              and().queryParam("q", "{\"region_id\":2}").
                when().get("/countries");

        response.prettyPrint();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertTrue(response.body().asString().contains("United States of America"));


    }
    @Test
    public void getEmployees_using_queryParameter(){
        Response response =   given().accept(ContentType.JSON).
                and().queryParam("q", "{\"job_id\":\"IT_PROG\"}").
                when().get("/employees");

        response.prettyPrint();
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json", response.contentType());
        Assertions.assertTrue(response.body().asString().contains("IT_PROG"));


    }



}
