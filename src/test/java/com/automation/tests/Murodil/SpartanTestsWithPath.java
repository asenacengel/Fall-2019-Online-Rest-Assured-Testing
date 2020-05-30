package com.automation.tests.Murodil;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class SpartanTestsWithPath {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
    }
    @Test
    public void spartanGetWithID_Test_using_path(){

        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", 10).
                when().get("/spartans/{id}");

        System.out.println(response.body().path("id" ).toString());
        System.out.println(response.body().path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone" ).toString());

        int id = response.body().path("id");
        String firstName = response.body().path("name" );
        String spartanGender = response.path("gender");
        long phoneNumber = response.path("phone");

        Assertions.assertEquals(10, id);
        Assertions.assertEquals("Lorenza", firstName);
        Assertions.assertEquals("Female", spartanGender);
        Assertions.assertEquals(839787487328L, phoneNumber);


    }
    @Test
    public void getAllSpartans_using_path_with_list(){
        Response response = get("/spartans/");

        int firstID =  response.path("iid[0]");
        System.out.println("First ID ::" + firstID);

        String firstName = response.path("name[0]" );
        System.out.println("Name ::" + firstName);

        String lastFirstName = response.path("name[-1]" );
        System.out.println("Last first Name ::" +lastFirstName);

        List <String> names = response.path("name" );
        System.out.println("Number of Names :: " + names.size());

        List<Long> phoneNumbers = response.path("phone" );

        phoneNumbers.forEach(eachPhone -> System.out.println());

    }

    @Test
    public void getCountries_and_extract_key_values_using_path(){

        RestAssured.baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
        Response response = given().queryParams("q","/countries?q={\"region_id\":2}").
                when().get("/countries");

        String firstCountryID = response.path("items.country_id[0]" );
        String firstCountryName = response.path("items.country_name[0]" );

        System.out.println("First Country Name :: "+firstCountryName);
        System.out.println("First Country ID :: "+firstCountryID);

        // Get all country names and print them out
        List<String> countries = response.path("items.country_name" );
        System.out.println("Country Name :: "+countries);

        // assert that all the region id's equal to 2
        List<Integer> regionID = response.path("items.region_id" );
        regionID.forEach(eachRegion -> Assertions.assertEquals(2, regionID));

    }


}
