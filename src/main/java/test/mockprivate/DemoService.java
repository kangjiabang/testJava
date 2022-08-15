package test.mockprivate;

public class DemoService {

    private String getName() {
        return "private";
    }

    private static String getNameStatic() {
        System.out.println("getNameStatic invoked");
        return "privateStatic";
    }
    public String execute() {
        return getName();
    }

    public static String executePrivateStatic() {
        return getNameStatic();
    }
}
