package minimock;


/**
 * stubbing类
 */
public class MiniStubbing {

    public MiniStubbing thenReturn(Object result) {
        if (RetValueContainer.getKey() != null) {
            RetValueContainer.add(RetValueContainer.getKey(), result);
        } else {
            throw new UnsupportedOperationException();
        }
        return this;
    }
}