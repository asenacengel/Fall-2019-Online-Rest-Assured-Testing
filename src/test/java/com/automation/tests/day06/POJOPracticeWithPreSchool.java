package com.automation.tests.day06;

import com.automation.pojos.Student;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class POJOPracticeWithPreSchool {

    @BeforeAll
    public static void setUp(){

        baseURI = ConfigurationReader.getProperty("PRESCHOOL.URI");
    }

    @Test
    public void addStudentTest(){

        File file  = new File("student.json");

        Response response = given().contentType(ContentType.JSON).body(file).
                when().post("/student/create").prettyPeek();

        int studentID = response.jsonPath().getInt(sessionId);
        System.out.println("studentID = " + studentID);

    }

    @Test
    public void getStudentTest(){

        Response response = get("/student/{id}", 12163).prettyPeek();
        Student student = response.jsonPath().getObject("student[0]", Student.class);
        System.out.println("student = " + student);
        System.out.println(response.getStatusCode());
    }




}
