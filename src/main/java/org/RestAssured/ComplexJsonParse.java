package org.RestAssured;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args){
        JsonPath js = new JsonPath(payload.CoursePrice());
        int count = js.getInt("courses.size()");
        System.out.println(count);

        int amount = js.getInt("dashboard.purchaseAmount");
        System.out.println(amount);

        String title = js.getString("courses[0].title");
        System.out.println(title);

        //print all courses title and price
        for(int i=0; i<count; i++){
            System.out.println(js.getString("courses["+i+"].title"));
            System.out.println(js.getInt("courses["+i+"].price"));
        }

        System.out.println("Print no of copies sold by RPA course");
        for(int i=0; i<count; i++){
            String title1 = js.getString("courses["+i+"].title");
            if(title1.equalsIgnoreCase("RPA")){
                System.out.println(js.getString("courses["+i+"].copies"));
                break;
            }
        }

    }
}
