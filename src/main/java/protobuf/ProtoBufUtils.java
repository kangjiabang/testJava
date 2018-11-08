package protobuf;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * @Author：zeqi
 * @Date: Created in 15:14 10/1/18.
 * @Description:
 */
public class ProtoBufUtils {

    /**
     * 序列化
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> byte[]  serialize(T obj) {

        Schema<T> schema = RuntimeSchema.getSchema((Class<T>)obj.getClass());

        LinkedBuffer buffer =  LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

        //ser
        byte[] protostuff = null;
        try
        {
            protostuff = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        finally
        {
            buffer.clear();
        }
        return protostuff;
    }

    /**
     * 反序列化
     * @param cls
     * @param data
     * @param <T>
     * @return
     */
    public static <T> T  deserialize(Class<T> cls,byte[] data) {

        //本身有缓存，无用再次缓存
        Schema<T> schema = RuntimeSchema.getSchema(cls);

        T f = schema.newMessage();

        ProtostuffIOUtil.mergeFrom(data, f, schema);

        return f;
    }
}
