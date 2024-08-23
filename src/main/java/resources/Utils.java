package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Properties;

public class Utils {

    static RequestSpecification req;

    public RequestSpecification requestSpecification() throws IOException {
        if(req == null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getProperty("url"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();
        }
        return req;
    }

    public String getProperty(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("src/main/resources/application.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response, String key){
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key);
    }

}
