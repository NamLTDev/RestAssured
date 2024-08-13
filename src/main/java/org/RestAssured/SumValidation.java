package org.RestAssured;

import files.payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void sumOfCoursesValidation(){
        JsonPath js = new JsonPath(payload.CoursePrice());
        int count = js.getInt("courses.size()");

        int sum = 0;
        for(int i=0; i<count; i++){
            int price = js.getInt("courses["+i+"].price");
            int copies = js.getInt("courses["+i+"].copies");
            int amount = price*copies;
            System.out.println(amount);
            sum += amount;
        }
        int actualSum = js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(actualSum,sum);
    }
}
