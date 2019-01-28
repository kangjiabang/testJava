package serviceloader;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ServiceLoaderTest {

    public static void main(String[] args) {
        try {
            DriverManager.getConnection("fff");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
