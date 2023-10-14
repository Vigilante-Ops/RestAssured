package MockingSection6;

import java.util.ArrayList;
import java.util.List;

public class MockModel
{

    private Dashboard dashboard;
    private List<Course> courses=new ArrayList<>();

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(Course course) {
        this.courses.add(course);
    }
}
