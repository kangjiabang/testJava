package errorcode;

public class ErrorCodeManagerTest {

    public static void main(String[] args) {
        ErrorCodeManager errorCodeManager = ErrorCodeManager.getErrorCodeManager("spring.errorcode");
        System.out.println(errorCodeManager.getString("LY001"));
    }
}
