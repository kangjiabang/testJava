package minimock;

import org.junit.Test;

public class TestMiniMock {

    @Test
    public void testMock() {
        Target target = MiniMock.mock(Target.class);
        MiniMock.when(target.getA()).thenReturn("1000");
        MiniMock.when(target.getXXX("")).thenReturn("xxx");
        System.out.println(target.getA());
        System.out.println(target.getA());
        System.out.println(target.getA());
        System.out.println(target.getXXX("a"));
    }
}