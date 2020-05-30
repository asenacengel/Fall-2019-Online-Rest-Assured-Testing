package com.automation.tests.day05;
import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class POJOPractice {


    @BeforeAll
    public static void beforeAll(){

        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");

    }
    @Test
    public void getUser(){
        Response response = given().auth().basic("admin", "admin").
                when().get("/spartans/{id}",393).prettyPeek();

        // get the body and map it to a Java Object.
        //For JSON responses this requires that you have either or Gson
        // this is a deserialization

        Spartan spartan = response.as(Spartan.class);
        System.out.println(spartan);

        assertEquals(393, spartan.get_id());
        assertEquals("Michael Scott",spartan.get_name());

        // deserialization : POJO <- JSON
        //serialization:     POJO -> JSON
        //both operations are done with a help of Gson
        //RestAssured automatically calls Gson for these operations


        Map<String , ?> spartanAsMap = response.as(Map.class);
        System.out.println(spartanAsMap);
    }
    @Test
    public void addUser(){
        Spartan spartan = new Spartan("Hasan Jan ", "Male", 928173647378L);
        Gson gson = new Gson();
        String pojoAsJson = gson.toJson(spartan);
        System.out.println(pojoAsJson);

        Response response = given().auth().basic("admin", "admin").
                contentType(ContentType.JSON).body(spartan).
                when().post("/spartans").prettyPeek();

        int usersID = response.jsonPath().getInt("data.id");
        System.out.println("Users id :: " +usersID);

        System.out.println("##### DELETE USER ######");
        given().auth().basic("admin","admin").
                when().delete("/spartans/{id}",usersID).prettyPeek().then().assertThat().statusCode(204);
    }

}

