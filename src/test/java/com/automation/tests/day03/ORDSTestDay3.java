package com.automation.tests.day03;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class ORDSTestDay3 {

    @Test
    public static void setUp() {
        baseURI = "http://54.224.118.38:1000/ords/hr";

    }
    @Test
    public void verifyFirstRegion(){
        given().pathParam("id",1).
                when().get("/regions/{id}").prettyPeek().
                then().assertThat().statusCode(200).body("region_name",is("Europe")).
                body("region_id",is(1)).time(lessThan(5L), TimeUnit.SECONDS);
                // verify that respoinse time is less than 5 seconds

    }
    @Test
    public void verifyEmployee(){
        Response response = given().accept(ContentType.JSON).
                when().get("/employees");

        JsonPath jsonPath = response.jsonPath();
        String nameFirstEmployee = jsonPath.getString("items[0].first_name");// 0 to get first item in the list
        String nameOfLastEmployee = jsonPath.getString("items[-1].first_name"); //-1 to get last item in the list

        System.out.println("first name of 1st employee "+nameFirstEmployee);
        System.out.println("first name of last employee "+nameOfLastEmployee);

        Map<String,Object> fistEmployee = jsonPath.get("items[0]");

    }


}
