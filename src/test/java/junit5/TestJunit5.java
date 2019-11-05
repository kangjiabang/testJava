/*
package junit5;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class TestJunit5 {


    @Test
    @Order(2)
    void testAssertThrows() {
        assertThrows(RuntimeException.class, () -> invoke());
    }

    @Test
    @Order(1)
    void timeoutNotExceeded() {
        assertTimeout(Duration.ofSeconds(5), () -> timeout());
    }

    private void timeout() {
        try {
            Thread.sleep(4*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void invoke() {
        throw new RuntimeException();
    }

}
*/
