package filecopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/**
 * Created by Administrator on 2018/11/21.
 */
public class FileCopy {

    public static final String UNDER_LINE = "_";

    private Set<String> fileSet;

    private static  String source = "D:/download/A/";

    private static  String dst = "D:/download/B/";

    private  static Timer  timer = new Timer();
    public FileCopy() {

    }

    public static void main(String[] args) {
        FileCopy fileCopy = new FileCopy();
        timer.schedule(fileCopy.new CopyHandler(),5000,10*1000);
    }

    /**
     * 文件拷贝工作
     */
    private  void doFileCopy() {

        File sourceDir = new File(source);
        fileSet = getFileNameList(sourceDir);
        //创建目的目录
        createDirIfNecessary(dst);
        for (String filePath : fileSet) {
            try {
                String[] paths = filePath.split("\\\\");
                String year = paths[paths.length-4];
                String month = paths[paths.length-3];
                String day = paths[paths.length-2];
                String fileName = paths[paths.length-1];
                StringBuilder sb = new StringBuilder();
                sb.append(dst).append(year).append(UNDER_LINE).append(month).append(UNDER_LINE).append(day).append(UNDER_LINE).append(fileName);
                String newFullFileName = sb.toString();
                //拷贝到目的路径
                Files.copy(Paths.get(filePath),Paths.get(newFullFileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 目的目录创建
     * @param dst
     */
    private static void createDirIfNecessary(String dst) {
        if (!Files.exists(Paths.get(dst)) || !Files.isDirectory(Paths.get(dst))) {
            try {
                Files.createDirectory(Paths.get(dst));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //返回目录下面的所有文件
   public  Set<String> getFileNameList(File file) {
       Set<String> fileStrings = null;
       try {
           fileStrings = new HashSet<>();
           Files.walkFileTree(Paths.get(file.getCanonicalPath()), new PictureFileVisitor(fileStrings));
       } catch (IOException e) {
           e.printStackTrace();
       }
       return  fileStrings;
   }

    /**
     * 文件便利策略类
     */
   private static class PictureFileVisitor extends SimpleFileVisitor<Path> {

       private Set<String> result;

       public PictureFileVisitor(Set<String> result) {
           this.result = result;
       }

       @Override
       public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
           if (file.toString().endsWith(".jpg")) {
               result.add(file.toString());
           }
           return FileVisitResult.CONTINUE;
       }
   }

   private  class CopyHandler extends TimerTask {

       @Override
       public void run() {

           Set<String> newFileSet = getFileNameList(new File(source));
           //如果集合发生改变，重新拷贝
           if (!newFileSet.equals(fileSet)) {
               doFileCopy();
           }
       }
   }

}
