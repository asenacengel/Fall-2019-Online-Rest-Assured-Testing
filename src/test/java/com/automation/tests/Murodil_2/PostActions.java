package com.automation.tests.Murodil_2;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class PostActions {

    @Test
    public void postNewSpartan(){

        Response response = given().accept(ContentType.JSON).
                and().contentType(ContentType.JSON).and().body("{\"gender\":\"Male\",\n +" +
                                                               "\"name\":\"Maximus\",\n"+
                                                                "\"phone\":8374777489}").
                when().post("http://54.164.195.86:8000/api/spartans/");

        assertEquals(201, response.statusCode());
        assertEquals("application/json", response.contentType());
        String message1 = response.path("success");
        // extract message using jsonpath
        JsonPath jsonPath = response.jsonPath();
        String message2 = jsonPath.getString("success");

        System.out.println("message1 = " + message1);
        System.out.println("message2 = " + message2);

        assertEquals("A Spartan is Born!", message1);
        assertEquals("A Spartan is Born!", message2);

        // assert name gender phone

        assertEquals("Male", jsonPath.getString("data.gender"));
        assertEquals("POST TEST", jsonPath.getString("data.name"));
        assertEquals(3782738738L, jsonPath.getLong("data.phone"));

        int spartanID = jsonPath.getInt("data.id");
        System.out.println("spartanID = " + spartanID);
        System.out.println("Sending Request With Spartan ID :: "+ spartanID);

        get("http://54.164.195.86:8000/api/spartans/" +spartanID).body().prettyPrint();


    }

    public void postANewSpartanWithPojoObject(){

        POJO_deserialization spartan = new POJO_deserialization();
        // Create a spartan object to used as body for post request
        spartan.setGender("Male");
        spartan.setName("PostSpartan");
        spartan.setPhone(7364789270L);

        Response response = given().accept(ContentType.JSON).
                and().contentType(ContentType.JSON).
                and().body(spartan).
                when().post("http://54.164.195.86:8000/api/spartans/");

    }
}
