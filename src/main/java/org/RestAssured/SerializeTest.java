package org.RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.AddPlace;
import pojo.Location;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTest {

    @Test
    public void serializeTest(){

        AddPlace ap = new AddPlace();
        ap.setAccuracy(50);
        ap.setAddress("VN");
        ap.setPhone_number("+84 98 999 9999");
        ap.setWebsite("https://www.thiendia.com");
        ap.setName("Strip Club");
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        ap.setTypes(myList);
        Location lc = new Location();
        lc.setLat(51.111);
        lc.setLng(-12.165);

        ap.setLocation(lc);
        RestAssured.baseURI="https://rahulshettyacademy.com";

        Response res = given().log().all().queryParam("key","qaclick123")
                .body(ap)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .extract().response();
        System.out.println(res.asString());
    }

}
