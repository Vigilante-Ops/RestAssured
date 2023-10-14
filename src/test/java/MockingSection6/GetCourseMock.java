package MockingSection6;

public class GetCourseMock {
    private static MockModel getMockModel()
    {
        Dashboard dashboard=new Dashboard();
        dashboard.setPurchaseAmount(45000);
        dashboard.setWebsite("www.tecxman.com");

        Course c1=new Course();
        c1.setTitle("title A");
        c1.setCopies(5);
        c1.setPrice(1000);

        Course c2=new Course();
        c2.setTitle("title B");
        c2.setCopies(10);
        c2.setPrice(4000);

        MockModel mockModel=new MockModel();
        mockModel.setDashboard(dashboard);
        mockModel.setCourses(c1);
        mockModel.setCourses(c2);

        return mockModel;

    }
}
