package com.automation.tests.Murodil_2;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class SpartanDeletePut {

    @BeforeAll
    public static void setUp(){

        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
    }

    @Test
    public void updateExistingSpartan_PUT_request_test(){

        Map<String , Object> requestMap = new HashMap<>();
        requestMap.put("name", "Marufjon");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 3747463789L);

        given().contentType(ContentType.JSON).
                and().body(requestMap).and().pathParam("id", 6).
                when().put("/spartans/{id}").
                then().assertThat().statusCode(204);

    }
    @Test
    public void deleteExistingSpartan_DELETE_request_test(){

        Random random = new Random();
        int idToDelete = random.nextInt(351) +4 ;

        given().pathParam("id", idToDelete).
                when().delete("/spartans/{id}").
                then().assertThat().statusCode(204);

        expect().that().statusCode(204).given().pathParam("id", idToDelete+1).
                when().delete("/spartans/{id}");
    }

}
