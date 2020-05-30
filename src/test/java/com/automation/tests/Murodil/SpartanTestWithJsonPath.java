package com.automation.tests.Murodil;
import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class SpartanTestWithJsonPath {

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = ConfigurationReader.getProperty("SPARTAN.URI");

    }

    @Test
    public void verifySpartanUsingJsonPath(){

      Response response =   given().accept(ContentType.JSON).
                and().pathParam("id", 11).
                when().get("/spartan/{id}");

        assertEquals(200,response );
        assertEquals("application/json;charset=UTF-8", response.contentType());

        // Store response json body/payload into JsonPath object
        JsonPath json = response.jsonPath();
        JsonPath json2 = new JsonPath(response.body().asString());

        int id = json.getInt("id");
        String name = json.getString("name");
        String gender = json.getString("Female");
        Long phone = json.getLong("phone");


    }
    @Test
    public void getAStudent_cbtraining_api_jsonpath(){

       JsonPath jsonPath = get("http://api.cybertektraining.com/student/58201").
               jsonPath();

       String firstName = jsonPath.getString("students.firstName");
       String lastName = jsonPath.getString("students.lastName");

       String phone = jsonPath.getString("students.contact.phone");
       String email = jsonPath.getString("students.contact.emailAddress");

       String companyName = jsonPath.getString("students.company.companyName");
       String companyCity = jsonPath.getString("students.company.address.city");


        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("phone = " + phone);
        System.out.println("email = " + email);
        System.out.println("companyName = " + companyName);
        System.out.println("companyCity = " + companyCity);


    }
    @Test
    public void hr_api_countries_jsonpath_list(){

       JsonPath jsonPath = get("http://54.164.195.86:1000/ords/hr/countries").jsonPath();
     //  jsonPath.prettyPrint();
        List<String> countryNames = jsonPath.getList("items.country_name");
        System.out.println("countryNames = " + countryNames);

        List<String> countryID = jsonPath.getList("items.country_id");
        System.out.println("countryID = " + countryID);

        List<String > countriesInRegion = jsonPath.getList("items.findAll {it.region_id == 2}.country_name");
        System.out.println("countriesInRegion = " + countriesInRegion);


    }
}
