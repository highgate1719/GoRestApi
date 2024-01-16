package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase {
    static ValidatableResponse response;

    @Test
    public void userExtract() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get("/public/v2/users")
                .then()
                .statusCode(200);

//        1. Extract the All Ids
        List<Integer> id = response.extract().path("id");
        System.out.println("All Id: " + id);
//        2. Extract the all Names
        List<String> name = response.extract().path("name");
        System.out.println("Get All name: " + name);
//        3. Extract the name of 5th object
        String getNameOfFive = response.extract().path("[4].name");
        System.out.println("Name of 5th object:" + getNameOfFive);
//        4. Extract the names of all object whose status = inactive
        List<String> nameOfStatusInactive = response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("Get the name of all object with inactive status: " + nameOfStatusInactive);
//        5. Extract the gender of all the object whose status = active
        List<String> genderOfStatusActive = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("The gender of all object with active status: " + genderOfStatusActive);
//        6. Print the names of the object whose gender = female
        List<String> nameFemale = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("Name of all object with gender is female: " + nameFemale);
//        7. Get all the emails of the object where status = inactive
        List<String> emailOfInactive = response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("Email of the object with inactive status: " + emailOfInactive);
//        8. Get the ids of the object where gender = male
        List<String> idOfMale = response.extract().path("findAll{it.gender=='male'}.id");
        System.out.println("Id of the object with gender is male: " + idOfMale);
//        9. Get all the status
        List<String> status = response.extract().path("status");
        System.out.println("Get all status: " + status);
//        10. Get email of the object where name = Somu Pillai
        String email = response.extract().path("[4].email");
//        List<String> email = response.extract().path("findAll{it.name == 'Somu Pillai'}.email");
        System.out.println("Get email for the name is 'Somu Pillai': " + email);
//        11. Get gender of id = 5914154
        String genderOdId = response.extract().path("[1].gender");
        System.out.println("Get gender of Id: " + genderOdId);
    }
}
