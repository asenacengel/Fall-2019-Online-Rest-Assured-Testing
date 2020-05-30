package com.automation.tests.Murodil;
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

public class JsonToJavaDataStructures {

    @Test
    public void convertSpartanData_to_Map() {


        Response response = given().accept(ContentType.JSON).
                when().get("http://54.164.195.86:8000/api/spartan/33");

        Map<String, Object> spartanMap = response.body().as(Map.class);
        System.out.println("spartanMap = " + spartanMap.toString());
        System.out.println(spartanMap.get("id"));

    }
}
