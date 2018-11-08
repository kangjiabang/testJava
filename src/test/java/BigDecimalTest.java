
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @Author：zeqi
 * @Date: Created in 16:16 23/2/18.
 * @Description:
 */
public class BigDecimalTest {


    @Test
    public void testBigDecimal() {
        BigDecimal digital = new BigDecimal("234560.10");
        System.out.println(digital.stripTrailingZeros());
        System.out.println(digital.divide(new BigDecimal(100)));
    }

    @Test
    public void testBigDecimalLoanValue() {
        BigDecimal digital = new BigDecimal("235.34");
        System.out.println(digital.multiply(new BigDecimal(100)).longValue());
    }

    @Test
    public void testBigDecimalDivide() {
        BigDecimal digital = new BigDecimal("5.023423");

        System.out.println(digital.divide(new BigDecimal(100)).toString());
    }
}
