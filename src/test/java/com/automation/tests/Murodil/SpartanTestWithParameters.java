package com.automation.tests.Murodil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;


public class SpartanTestWithParameters {

    @BeforeAll
    public static void setUp(){
        //set Base URI so that we dont have to type this everytime
        baseURI = "http://3.93.177.58:8000/api";

    }
    @Test
    public void helloTest(){
        //request
      Response response =  get("/hello");
      //response
        Assertions.assertEquals(200, response.statusCode());
        //verify headers
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());
        System.out.println(response.getHeader("Date"));
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        Assertions.assertEquals("17", "Content-Length");
        Assertions.assertEquals("Hello from Sparta", response.body().asString());

    }
    @Test
    public void getSpartanById_positive_Path_Param_test(){
        // request
        Response response  = given().accept(ContentType.JSON).
                and().pathParam("id", 5).
                when().get("/spartans/{id}");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json;charset=UTF-8", response.contentType());
        Assertions.assertTrue(response.body().asString().contains("Blythe"));
    }
    @Test
    public void negativeFindById_PathParamTest(){
        //request
        Response response  = given().accept(ContentType.JSON).
                and().pathParam("id", 500).
                when().get("/spartans/{id}");
        //response validations
        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("application/json;charset=UTF-8", response.contentType());
        Assertions.assertTrue(response.body().asString().contains("Spartan Not Found"));


    }
    @Test
    public void positiveTestWithQueryParams_search(){
        Response response = given().accept(ContentType.JSON).
                queryParam("gender", "Female","nameContains", "e").
                when().get("/spartans/search");

        //Different version

        Response response2 = given().accept(ContentType.JSON).
                when().get("/spartans/search?gender=Female&nameContains=e");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(200, response.statusCode());

        Assertions.assertEquals("application/json;charset=UTF-8", response.contentType());
        Assertions.assertEquals("application/json;charset=UTF-8", response.contentType());

        Assertions.assertTrue(response.body().asString().contains("Female"));
        Assertions.assertTrue(response.body().asString().contains("Janette"));

        Assertions.assertTrue(response.body().asString().contains("Female"));
        Assertions.assertTrue(response.body().asString().contains("Janette"));


    }

    @Test
    public void invalid_header_test_with_parameters(){

        Response response = given().accept(ContentType.XML).
                queryParams("Gender", "Female", "nameContains","e").
                when().get("/spartans/search");

        Response response1 = given().accept(ContentType.XML).queryParam("gender", "Female").
                queryParam("nameContains", "e").
                when().get("/spartans/search");

        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("gender", "Female");
        paramsMap.put("nameContains", "e");

        Response response2 = given().accept(ContentType.XML).queryParams(paramsMap).
                when().get("/spartans/search");

        Assertions.assertEquals(406, response.statusCode());
        Assertions.assertEquals(406, response.statusCode());
        Assertions.assertEquals(406, response.statusCode());







    }




}
