package org.RestAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;
import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SpecBuilderTest {

    @Test
    public void specBuilderTest(){

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

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                        .addQueryParam("key","qaclick123")
                        .setContentType(ContentType.JSON).build();

        ResponseSpecification rs = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        RequestSpecification res = given().spec(req).body(ap);

        Response response =res.when().post("/maps/api/place/add/json")
                .then().spec(rs)
                .extract().response();

        System.out.println(response.asString());
    }

}
