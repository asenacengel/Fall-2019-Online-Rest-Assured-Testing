package com.automation.tests.day04;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class ORDSTestDay4 {

    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("ORDS.URI");


    }

    @Test
    @DisplayName("Verify status code , content type and response time")
    public void employeesTest1(){
        given().accept(ContentType.JSON).
                when().get("/employees").prettyPeek().
                then().assertThat().statusCode(200).contentType(ContentType.JSON).time(lessThan(5L),TimeUnit.SECONDS);

    }
    @Test
    @DisplayName("Verify country name,content type,and status code or country with ID US")
    public void verifyCountries(){
        given().accept(ContentType.JSON).queryParam("q","{\"country_id\":\"US\"}").
                when().get("/countries").
                then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("items[0].country_name",is("United States of America"));

        Response response = given().accept(ContentType.JSON).when().get("/countries").prettyPeek();

        String countryName = response.jsonPath().getString("items.find {it.country_id == 'US'}.country_name");
        Map<String,Object> countryUS = response.jsonPath().get("items.find {it.country_id == 'US'}");
        // find all country names from region 2
        // collectionName.findall{it.propertyName=='Value'}-- to get collection objects where property equals to some value
        //collectionName.find{it.propertyName == 'Value'}-- to object where property equals to some value
        // to get collection properties where property equals to some value
        //collectionName.findAll{it.propertyName=='Value'}.propertyName

        System.out.println("Country name: "+countryName);
        System.out.println(countryUS);

        for(Map.Entry<String ,Object> entry : countryUS.entrySet()){
            System.out.printf("key = %s, value = %s\n",entry.getKey(),entry.getValue());
        }

    }
    @Test
    //@DisplayName("")
    public void getEmployeeTest(){
        Response response = when().get("/employees").prettyPeek();

        Map<String,?> bestEmployee = response.jsonPath().get("items.max{it.salary}");
        Map<String,?>poorGuy = response.jsonPath().get("items.min{it.salary}");

        int companiesPayRoll = response.jsonPath().get("items.collect{it.salary}.sum()");

        System.out.println(bestEmployee);
        System.out.println(poorGuy);
        System.out.println("Companies payroll: " + companiesPayRoll);
    }
    @Test
    @DisplayName("Verify that every employee has positive salary")
    public void testSalary(){
        when().get("/employees").then().assertThat().statusCode(200).
                body("items.salary",everyItem(greaterThan(0))).
                log().ifError();
    }
    @Test
    public void verifyPhoneNumber(){
        Response response = when().get("/employees/{id}",101).prettyPeek();

        String expected = "515-123-4568";
        String actual = response.jsonPath().getString("phone_number").replace(".", "-");

        assertEquals(200, response.statusCode());
        assertEquals(expected, actual);
    }
}
