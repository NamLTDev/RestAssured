package pojo;

import lombok.Data;

import java.util.List;

@Data
public class CourseList {

    private List<CourseDetail> courseDetail;

    private List<Automation> webAutomation;
    private List<API> api;
    private List<Mobile> mobile;
}
