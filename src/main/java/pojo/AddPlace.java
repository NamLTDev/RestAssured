package pojo;

import lombok.Data;

import java.util.List;

@Data
public class AddPlace {

    private int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private String website;
    private String language;
    private Location location;
    private List<String> types;
}
