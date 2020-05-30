package com.automation.tests.day06;
import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;



public class POJOPracticeWithSpartanApp {

    @BeforeAll
    public static void beforeAll(){
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");

    }
    @Test
    public void addSpartanTest(){

        Map<String,String> spartan = new HashMap<>();

        spartan.put("gender", "Male");
        spartan.put("name", "Nursultan");
        spartan.put("phone", "1922828473");

        RequestSpecification requestSpecification = given().
                auth().basic("admin", "admin").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(spartan);
        Response response = given().
                auth().basic("admin", "admin").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(spartan).
                when().
                post("/spartans").prettyPeek();

        response.then().statusCode(201);
        response.then().body("success",is("A spartan is Born!"));

        // deserialization
        Spartan spartanResponse = response.jsonPath().getObject("data", Spartan.class);
        Map<String, Object> spartanResponseMap = response.jsonPath().getObject("data", Map.class);
        System.out.println(spartanResponse);
        System.out.println(spartanResponseMap);
        //spartanResponse is a Spartan
        System.out.println(spartanResponse instanceof Spartan);// must be true
    }
    @Test
    public void updateSpartanTest(){

        int userToUpdate = 101;
        String name = "Nurs";

        // HTTP PUT request to update exiting record , for example exiting spartan.
        // PUT - requires to provide ALL parameters in body.

        Spartan spartan = new Spartan(name, "Male", 37432843287L);

        // get spartan from web service
        Spartan spartanToUpdate = given().auth().basic("admin", "admin").
               accept(ContentType.JSON).when().get("/spartan/{id}",userToUpdate).as(Spartan.class);

        spartanToUpdate.set_name(name);


        Response response = given().auth().basic("admin", "admin").
                contentType(ContentType.JSON).body(spartan).when().put("/spartan/{id}",userToUpdate).prettyPeek();

        response.then().statusCode(204);
        System.out.println("###########################");
        // to get user id with 101 the one that we've just updated
        given().auth().basic("admin","admin").
                when().get("/spartan/{id}",userToUpdate).prettyPeek().
                then().statusCode(200).body("name",is(name));

    }
    @Test
    @DisplayName("Verify that user can perform PATCH request")
    public void patchUserTest1(){

        // PATCH - partial update of existing record
        int userId =21; // user to update make user with this id exist

        // lets put the code to take random user
        // get all spartans
        Response response1 = given().accept(ContentType.JSON).when().get("/spartans");
        // I can save them all in the array list
        List<Spartan> allSpartans = response1.jsonPath().getList("",Spartan.class);

        Random random = new Random();

        int randomNum = random.nextInt(allSpartans.size());
        int randomId = allSpartans.get(randomNum).get_id();
        System.out.println("allSpartans = " + allSpartans);



        Map<String , String> update = new HashMap<>();
        update.put("name", "Aidar");

        Response response = given().contentType(ContentType.JSON).
                body(update).when().patch("/spartans/{id}",userId);
                response.then().assertThat().statusCode(204).body("name",is("Aidar"));


    }

}
