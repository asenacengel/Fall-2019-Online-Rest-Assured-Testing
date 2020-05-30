package com.automation.tests.Murodil;
import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class HRApiTestsPathVerification {

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = ConfigurationReader.getProperty("ORDS.URI");


    }
    @Test
    public void verifyCountryDetailsUsing_path_method(){

        Response response = given().accept("application/json").
                and().pathParam("country_id", "US").
                when().get("/countries/{country_id}");

        int statusCode =  response.statusCode();
        String countryID = response.path("country_id" );
        String countryName = response.path("country_name" );
        int regionID = response.path("region_id" );

        Assertions.assertEquals(200, statusCode);
        Assertions.assertEquals("US", countryID);
        Assertions.assertEquals("United States of America", countryName);
        assertEquals(2, regionID);


    }
    @Test
    public void verify_multiple_employee_details_using_path(){

        Response response = given().accept("application/json").
                and().queryParams("q","{\"department_id\":80}").when().get("/employees");

        // validations
        Assertions.assertEquals(200, response.statusCode());

        List<String> jobIDs = response.path("items.job_id" );
        List<Integer> deptIDs = response.path("items.department_id" );
        int count = response.path("count" );

        for(String jobID : jobIDs){
            assertTrue(jobID.startsWith("SA"));
            assertEquals("SA", jobID.substring(0,2));
        }

        deptIDs.forEach(id ->assertEquals(new Integer(80), id));
        assertEquals(25,count );

    }
}
