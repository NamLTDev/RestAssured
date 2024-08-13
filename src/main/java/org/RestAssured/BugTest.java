package org.RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class BugTest {

    @Test
    public void createBugOnJira(){
        RestAssured.baseURI="https://namltarmy.atlassian.net/";

        //Create bug
        String response = given().header("Content-Type","application/json")
                .header("Authorization","Basic bmFtYzQ4NTJAZ21haWwuY29tOkFUQVRUM3hGZkdGMHF6aW1FdnZubW9vNHM1WnBGeXl5cVRpcWpwOFRpMHYydW1KZVpkRGdPM3EzdDVFUmwzc3l5ZEw2Uy1zdmY2Mnlrbm1YNVFDaWgtcDBORkxENUlGRUpjZnAwcnpncjhkTDl5aVdaVlVIb2JDYnF6S0w1TDdjSnp4WW9XV1hVVVhFUkdZdzF6YkQ0eXR2VV9jWVJoN3RQSXlHR1Z4TEZhaUFwYTVxX19RYjNYZz03MDhBOUJCRg==")
                .body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"SCRUM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"Links is not working - automation\",\n" +
                        "       \n" +
                        "       \"issuetype\": {\n" +
                        "          \"name\": \"Bug\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}")
                .log().all()
                .when().post("rest/api/3/issue")
                .then().log().all().assertThat().statusCode(201)
                .extract().response().asString();

        JsonPath js = new JsonPath(response);
        String issueId = js.getString("id");

        //Add attachment
        given().pathParam("key", issueId)
                .header("Authorization","Basic bmFtYzQ4NTJAZ21haWwuY29tOkFUQVRUM3hGZkdGMHF6aW1FdnZubW9vNHM1WnBGeXl5cVRpcWpwOFRpMHYydW1KZVpkRGdPM3EzdDVFUmwzc3l5ZEw2Uy1zdmY2Mnlrbm1YNVFDaWgtcDBORkxENUlGRUpjZnAwcnpncjhkTDl5aVdaVlVIb2JDYnF6S0w1TDdjSnp4WW9XV1hVVVhFUkdZdzF6YkQ0eXR2VV9jWVJoN3RQSXlHR1Z4TEZhaUFwYTVxX19RYjNYZz03MDhBOUJCRg==")
                .header("X-Atlassian-Token","no-check")
                .multiPart("file",new File("src/main/resources/data/addPlace.json")).log().all()
                .when().post("rest/api/3/issue/{key}/attachments")
                .then().log().all().statusCode(200);
    }
}
