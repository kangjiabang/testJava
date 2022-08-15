package cache.redis;

import java.io.IOException;
import java.net.Socket;

/**
 * @Author：zeqi
 * @Date: Created in 13:53 23/4/18.
 * @Description:
 */
public class RedisClient {

    Socket socket = null;
    RedisClient() {
        try {
            socket = new Socket("127.0.0.1",6379);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String set(String key,String value) {
        try {
            /*//参考
            try {
                os.write(ASTERISK_BYTE);
                os.writeIntCrLf(args.length + 1);
                os.write(DOLLAR_BYTE);
                os.writeIntCrLf(command.length);
                os.write(command);
                os.writeCrLf();

                for (final byte[] arg : args) {
                    os.write(DOLLAR_BYTE);
                    os.writeIntCrLf(arg.length);
                    os.write(arg);
                    os.writeCrLf();
                }
            } catch (IOException e) {
                throw new JedisConnectionException(e);
            }*/
            StringBuilder sb = new StringBuilder();
            sb.append("*3").append("\r\n");
            sb.append("$").append(Command.SET.name().length()).append("\r\n");
            sb.append(Command.SET.name()).append("\r\n");
            // 注意中文汉字。一个汉字两个字节，长度为2
            sb.append("$").append(key.getBytes().length).append("\r\n");
            sb.append(key).append("\r\n");
            sb.append("$").append(value.getBytes().length).append("\r\n");
            sb.append(value).append("\r\n");
            System.out.println(sb.toString());

            socket.getOutputStream().write(sb.toString().getBytes());
            byte[] b = new byte[2048];
            socket.getInputStream().read(b);
            return new String(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        RedisClient redisClient = new RedisClient();
        System.out.println(redisClient.set("mytest","6"));
    }

}
