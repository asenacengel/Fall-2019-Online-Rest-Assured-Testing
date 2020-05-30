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

public class BookItApiAuthorization {

    String accessToken ;
    @BeforeAll
    public static void setUp(){

        baseURI = "https://cybertek-reservation-api-qa3.herokuapp.com/api";

    }

    @Test
    public void getAllCampusesUsingAccessToken(){


        Response response = given().headers("Authorization",accessToken).accept(ContentType.JSON).
                when().get("/campuses");

        // Using JSONPath print name of room id 111 in light side

        JsonPath jsonPath = response.jsonPath();
        String roomName = jsonPath.getString("clusters[0].rooms[0].name[0]");
        System.out.println("roomName = " + roomName);

    }


}
