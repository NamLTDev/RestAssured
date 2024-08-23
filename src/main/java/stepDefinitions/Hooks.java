package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinition stepDefinition = new StepDefinition();
        if(StepDefinition.place_id ==null) {
            stepDefinition.addPlacePayloadWith("Shetty", "French", "Asia");
            stepDefinition.userCallsWithHttpRequest("AddPlaceAPI", "POST");
            stepDefinition.verifyPlace_IdCreatedMapsToUsingGetPlaceAPI("Shetty", "getPlaceAPI");
            System.out.println("Done");
        }
    }
}
