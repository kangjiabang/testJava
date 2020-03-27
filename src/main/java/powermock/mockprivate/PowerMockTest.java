package powermock.mockprivate;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DemoService.class)
public class PowerMockTest {

    private DemoService powerMockDemoSpy;

    @Before
    public void setUp() {
        powerMockDemoSpy = spy(new DemoService());
    }
    @Test
    public void testMockPriate() throws Exception {
        // use PowerMockito to set up your expectation
        doReturn("privateMock").when(powerMockDemoSpy, "getName");

        // execute your test
        String result = powerMockDemoSpy.execute();
        Assert.assertEquals(result,"privateMock");
    }
    @Test
    public void testMockPriateStatic() throws Exception {
        spy(DemoService.class);
        // use PowerMockito to set up your expectation
        doReturn("privateStaticMock").when(DemoService.class, "getNameStatic");

        // execute your test
        String result = DemoService.executePrivateStatic();
        Assert.assertEquals(result,"privateStaticMock");
    }

    @Test
    public void testMockPriateStatic2() throws Exception {
        spy(DemoService.class);
        // invoke real method,use do return instead
        when(DemoService.class, "getNameStatic").thenReturn("privateStaticMock");

        // execute your test
        String result = DemoService.executePrivateStatic();
        Assert.assertEquals(result,"privateStaticMock");
    }
    
    @Test
    public void testDoReturn() {

        List list = new LinkedList();
        List spy = spy(list);
//Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
       // when(spy.get(0)).thenReturn("foo");

//You have to use doReturn() for stubbing
        doReturn("foo").when(spy).get(0);
    }

}
