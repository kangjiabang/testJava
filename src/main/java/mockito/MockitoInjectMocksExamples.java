/*
package mockito;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MockitoInjectMocksExamples extends BaseTestCase {

    @Mock
    EmailService emailService;


    @InjectMocks
    AppServices appServicesConstructorInjectionMock;



    @Test
    public void test_constructor_injection_mock() {
        when(appServicesConstructorInjectionMock.sendEmail("Email")).thenReturn(true);

        assertTrue(appServicesConstructorInjectionMock.sendEmail("Email"));
        assertFalse(appServicesConstructorInjectionMock.sendEmail("Unstubbed Email"));


    }
}*/
