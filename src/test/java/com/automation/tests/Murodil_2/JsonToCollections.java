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

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class JsonToCollections {

    @Test
    public void hrApiEmployee_jsondata_to_java_object(){

        Response response  = given().accept(ContentType.JSON).pathParam("employee_id", 105).
                when().get("http://3.85.41.58:1000/ords/hr/employees/{employee_id}");

        response.prettyPrint();
        Map<String ,?> employeeMap = response.body().as(Map.class);
        System.out.println("employeeMap = " + employeeMap.toString());

        String firstName = employeeMap.get("first_name").toString();
        System.out.println("firstName = " + firstName);

        assertEquals("David", firstName);

      double salary = (Double) employeeMap.get("salary");
        System.out.println("salary = " + salary);

        assertEquals(4800.5, salary,0.5);
        //delta amount. if there difference by delta amount or less
        //value will be considered equal

    }
    @Test
    public void convertAll_spartans_to_list_of_maps(){

        Response response = given().accept(ContentType.JSON).
                when().get("http://54.164.195.86:8000/api/spartans/");

        List<Map<String,?>> jsonListOfMap =response.body().as(List.class);

        // print all data of first spartan
        System.out.println(jsonListOfMap.get(0));

        Map<String ,?> first = jsonListOfMap.get(0);
        System.out.println("first = " + first);

        int counter = 1;
        for(Map <String , ?> spartan : jsonListOfMap){

            System.out.println(counter + "spartan = " + spartan);
            counter++;

        }


    }
    @Test
    public void regions_data_to_collections(){

        Response response = given().accept(ContentType.JSON).when().get("http://54.164.195.86:8000/api/regions/");

        Map<String ,?> dataMap = response.body().as(Map.class);
        System.out.println("dataMap = " + dataMap);

        // extract Europe Americas Asia from above map

        System.out.println(dataMap.get("items"));
        List<Map<String , ?>> regionList = (List<Map<String, ?>>)dataMap.get("items");

        System.out.println("regionList = " + regionList.get(0).get("region_name"));
        System.out.println("regionList = " + regionList.get(1).get("region_name"));
        System.out.println("regionList = " + regionList.get(2).get("region_name"));


    }

}
