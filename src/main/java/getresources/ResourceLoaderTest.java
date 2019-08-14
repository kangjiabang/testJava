package getresources;

public class ResourceLoaderTest {

    public static void main(String[] args) {

        System.out.println("ResourceLoaderTest.class.getResource(\"/\") :" + ResourceLoaderTest.class.getResource("/"));
        System.out.println("ResourceLoaderTest.class.getResource(\".\") :" + ResourceLoaderTest.class.getResource("."));

        System.out.println("ResourceLoaderTest.class.getClassLoader.getResource(\"/\") :" + ResourceLoaderTest.class.getClassLoader().getResource("/log4j.properties"));
        System.out.println("ResourceLoaderTest.class.getClassLoader.getResource(\".\") :" + ResourceLoaderTest.class.getClassLoader().getResource("."));


        System.out.println("ResourceLoaderTest.class.getResource(\"/abc/scd/1.txt\") :" + ResourceLoaderTest.class.getResource("/abc/scd/1.txt"));
        System.out.println("ResourceLoaderTest.class.getResource(\"abc/scd/1.txt\") :" + ResourceLoaderTest.class.getResource("abc/scd/1.txt"));

        System.out.println("ResourceLoaderTest.class.getClassLoader.getResource(\"/abc/scd/1.txt\") :" + ResourceLoaderTest.class.getClassLoader().getResource("/abc/scd/1.txt"));
        System.out.println("ResourceLoaderTest.class.getClassLoader.getResource(\"abc/scd/1.txt\") :" + ResourceLoaderTest.class.getClassLoader().getResource("abc/scd/1.txt"));

    }
}
