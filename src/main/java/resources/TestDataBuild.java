package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayLoad(String name, String language, String address){
        AddPlace ap = new AddPlace();
        ap.setAccuracy(50);
        ap.setAddress(address);
        ap.setLanguage(language);
        ap.setPhone_number("+84 98 999 9999");
        ap.setWebsite("https://www.thiendia.com");
        ap.setName(name);
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        ap.setTypes(myList);
        Location lc = new Location();
        lc.setLat(51.111);
        lc.setLng(-12.165);
        ap.setLocation(lc);

        return ap;
    }

    public String deletePlacePayload(String placeId){
        return "{\r\n   \"place_id\":\"" + placeId + "\"\r\n}";
    }
}
