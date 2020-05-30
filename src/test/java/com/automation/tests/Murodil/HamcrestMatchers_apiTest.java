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

public class HamcrestMatchers_apiTest {

    @Test
    public void getSpartanAnd_assertUsing_hamcrest(){

        given().accept(ContentType.JSON).
                and().pathParam("id", 15).
                when().get("http://54.164.195.86:8000/api/spartans/[id}").
                then().assertThat().statusCode(200).
                and().assertThat().contentType("application/json;charset=UTF-8").
                and().assertThat().body("id", equalTo(15),
                "name",equalTo("Meta"),"gender",
                        equalTo("Female"),"phone",
                        equalTo(1584985933));






    }
}
