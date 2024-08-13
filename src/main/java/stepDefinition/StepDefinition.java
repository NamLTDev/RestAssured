package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
    @Given("^Add Place Payload$")
    public void add_Place_Payload(){
        
    }

    @When("user calls {string} with Post http request")
    public void userCallsWithPostHttpRequest(String arg0) {
            
    }

    @Then("the API call is success with status code {int}")
    public void theAPICallIsSuccessWithStatusCode(int arg0) {
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String arg0, String arg1) {
    }

}
