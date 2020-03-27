package basic.file;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.dubbo.common.utils.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class AccessRandomFileTest {

    public static void main(String[] args) {

        try {
            File file = new File(AccessRandomFileTest.class.getClassLoader().getResource("test.txt").getFile());

            System.out.println(readLastLine(file, 10));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readLastLineManully(File file, int numLastLineToRead) {
        try {
            RandomAccessFile randomAccessFile =
                    new RandomAccessFile(AccessRandomFileTest.class.getClassLoader().getResource("test.txt").getFile(), "r");

            long filePointer = randomAccessFile.getFilePointer();
            long length = randomAccessFile.length();


            randomAccessFile.seek(10);

            while (randomAccessFile.read() != -1) {
                System.out.println(randomAccessFile.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String readLastLine(File file, int numLastLineToRead) {

        List<String> result = null;
        try {
            result = new ArrayList<>();

            try (ReversedLinesFileReader reader = new ReversedLinesFileReader(file, 4096, UTF_8)) {

                String line = "";
                while ((line = reader.readLine()) != null && result.size() < numLastLineToRead) {
                    result.add(line);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.reverse(result);
        String resultString = null;
        if (CollectionUtils.isNotEmpty(result)) {
            resultString = String.join("\n",result);
            System.out.println(resultString);
        }
        return resultString;

    }
}
