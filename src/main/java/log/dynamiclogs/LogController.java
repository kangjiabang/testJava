package log.dynamiclogs;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

@RestController
public class LogController {

    @RequestMapping(value = "/queryLogConfig",method = RequestMethod.GET)
    public String queryLogConfig() {
        InputStream inputStream = null;
        try {
            ClassLoader cls = getClassLoader();
            URL url = cls.getResource("logback.xml");
            inputStream = url.openStream();
            //testAi code review
            return IOUtils.toString(inputStream, Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return "";
    }

    private ClassLoader getClassLoader() {
        ClassLoader cls = this.getClass().getClassLoader();
        if (cls == null) {
            cls = ClassLoader.getSystemClassLoader();
        }
        return cls;
    }

    @RequestMapping(value = "/modifyLogConfig",method = RequestMethod.POST)
    public void modifyLogConfig(@RequestBody String logConfig) {
        //logConfig
        FileChannel fileChannel = null;
        RandomAccessFile randomAccessFile = null;
        try {
            ClassLoader cls = getClassLoader();
            URL url = cls.getResource("logback.xml");
            String file = url.getFile();
            randomAccessFile = new RandomAccessFile(file,"rw");
            fileChannel = randomAccessFile.getChannel();
            //clear file
            fileChannel.truncate(0);
            ByteBuffer byteBuffer = ByteBuffer.wrap(logConfig.getBytes(Charset.defaultCharset()));

            fileChannel.write(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fileChannel);
            IOUtils.closeQuietly(randomAccessFile);
        }
    }
}
