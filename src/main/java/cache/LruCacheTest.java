package cache;

import okhttp3.internal.cache.DiskLruCache;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2018/12/4.
 */
public class LruCacheTest {

    public static void main(String[] args) {
        Path fileDir = Paths.get("D://work/cache");
        if (!Files.exists(fileDir)) {
            try {
                Files.createFile(fileDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
