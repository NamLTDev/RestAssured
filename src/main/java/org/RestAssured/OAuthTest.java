package org.RestAssured;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.Automation;
import pojo.Course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

public class OAuthTest {

    @Test
    public void oauth_test(){

        String response = given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type","client_credentials")
                .formParams("scope","trust")
        .when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
                .asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String token = js.getString("access_token");

        Course course = given().queryParam("access_token",token)
                .when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .as(Course.class);
        System.out.println("LinkedIn: " + course.getLinkedIn());
        System.out.println("\n Instructor: " + course.getInstructor());

        System.out.println(course.getCourses().getWebAutomation().get(0).getCourseTitle());

        List<Automation> aut = course.getCourses().getWebAutomation();
        ArrayList<String> a = new ArrayList<>();
        for(int i=0; i<aut.size(); i++){
            a.add(aut.get(i).getCourseTitle());
        }
        String[] courseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};

        Assert.assertTrue(a.equals(Arrays.asList(courseTitles)));

    }
}
