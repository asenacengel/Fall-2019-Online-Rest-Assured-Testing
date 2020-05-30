package com.automation.tests.Murodil_2;
import com.google.gson.Gson;
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

public class Spartan {

    @Test
    public void spartan_to_pojo_object_deserialization(){

        Response response = given().accept(ContentType.JSON).when().get("http://54.164.195.86:8000/api/spartans/15");

        POJO_deserialization pojo_deserialization  = response.body().as(POJO_deserialization.class);

        System.out.println(pojo_deserialization.getName());
        System.out.println(pojo_deserialization.getGender());
        System.out.println(pojo_deserialization.getId());
        System.out.println(pojo_deserialization.getPhone());

        System.out.println("pojo_deserialization = " + pojo_deserialization.toString());

        assertEquals("Meta", pojo_deserialization.getName());
        assertEquals("Female", pojo_deserialization.getGender());
        assertEquals(15, pojo_deserialization.getId());
        assertEquals(1928373366, pojo_deserialization.getPhone());


    }
    @Test
    public void gSonExample(){

        POJO_deserialization pojoDeserialization = new POJO_deserialization(20, "Vlad", "male", 7373666728993L);

        Gson gson = new Gson();
        // Seriliaze spartan object to JSON format using Gson
        String json = gson.toJson(pojoDeserialization);
        System.out.println("json = " + json);

        String myJson = "{\"id\":25,\"name\":\"Roman\",\"gender\":\"male\",\"phone\":7033964165}";
        // Deserialization. Convert JSON to Java POJO_deserialization object
        POJO_deserialization Roman = gson.fromJson(myJson,POJO_deserialization.class);
        System.out.println(Roman.toString());

        // fromJson(String json , Which.class)--> it will convert the json object of the class
        //toJson (java object) --> it will take the java object and create json and return it

    }
    @Test
    public void list_of_spartans_pojo_deserialization(){

        Response response = given().accept(ContentType.JSON).
                when().get("http://54.164.195.86:8000/api/spartans/15");

        List<POJO_deserialization> allSpartans = response.body().as(List.class);

        System.out.println(allSpartans.get(0));

        POJO_deserialization first = allSpartans.get(0);
        System.out.println("first = " + first.toString());

        for(POJO_deserialization sp : allSpartans){

            System.out.println("sp = " + sp.toString());


        }

        AllSpartans allSpartans1 = response.body().as(AllSpartans.class);

        System.out.println(allSpartans1.getSpartanList().get(0).toString());

        //TODO: fix the desere

    }
}
