package test.minimock;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class MiniMock {

    public static <T> T mock(Class<T> type) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(type);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            // 根据入参匹配container的备选
            String key = obj.getClass().getName() + "_" + method.getName();
            Object value = RetValueContainer.match(key);
            if (null == value) {
                RetValueContainer.setMatcherKey(key);
            }
            return value;
        });
        return type.cast(enhancer.create());
    }

    public static <T> MiniStubbing when(T call) {
        return new MiniStubbing();
    }
}