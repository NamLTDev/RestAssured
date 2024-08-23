package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils {
    RequestSpecification res;
    ResponseSpecification rs;
    Response response;
    TestDataBuild data = new TestDataBuild();
    public static String place_id;
//    @Given("^Add Place Payload$")
//    public void add_Place_Payload() throws IOException {
//        rs = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
//        res = given().spec(requestSpecification()).body(data.addPlacePayLoad());
//    }

    @Given("Add Place Payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {
//        rs = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
    }

    @When("user calls {string} with {string} http request")
    public void userCallsWithHttpRequest(String resource,String method) {
        APIResources apiResources = APIResources.valueOf(resource);
        if(method.equalsIgnoreCase("POST")){
            response =res.when().post(apiResources.getResource());
        } else if (method.equalsIgnoreCase("GET")) {
            response =res.when().get(apiResources.getResource());
        }
    }

    @Then("the API call is success with status code {int}")
    public void theAPICallIsSuccessWithStatusCode(int statusCode) {
        Assert.assertEquals(response.statusCode(),statusCode);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String keyValue, String expectedValue) {
        Assert.assertEquals(getJsonPath(response,keyValue),expectedValue);
    }

    @And("verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsingGetPlaceAPI(String name, String resource) throws IOException {
        place_id = getJsonPath(response,"place_id");
        res = given().spec(requestSpecification()).queryParam("place_id",place_id);
        userCallsWithHttpRequest(resource,"GET");
        String actualName = getJsonPath(response,"name");
        Assert.assertEquals(actualName,name);
    }

    @Given("DeletePlace Payload")
    public void deletePlacePayload() throws IOException {
        res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }
}
