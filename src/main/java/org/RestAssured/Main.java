package org.RestAssured;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Main {
    public static String placeId;
    public static String newAddress = "My Dinh-Tu Liem-Hanoi,Vietnam";
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");

//        given - all input details
//        when - submit the API - resource, http method
//        then - validate the response
//        content of the file to String -> content of file can convert into Byte -> Byte to data to String
        //Add place  -> Update Place with new address -> get Place to validate if new address is presented in response

        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Files.readAllBytes(Paths.get("src/main/resources/data/addPlace.json")))
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                        .body("scope",equalTo("APP"))
                        .header("server","Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response); //parsing json
        placeId = js.getString("place_id");
        System.out.println(placeId);


        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(payload.updatePlace())
                .when().put("/maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200)
                        .body("msg", equalTo("Address successfully updated"));

        //Get place
        String getPlace = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId)
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js1 = new JsonPath(getPlace);
        String actualAddress = js1.getString("address");

        //Cucumber Junit, TestNG
        Assert.assertEquals(actualAddress,newAddress);


    }
}