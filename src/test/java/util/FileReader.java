package util;

import java.io.InputStream;

/**
 * Created by chandanjavaregowda on 04/04/17.
 */
public class FileReader {
    public static InputStream read(String filePath) {
        ClassLoader classLoader = FileReader.class.getClassLoader();
        return classLoader.getResourceAsStream(filePath);
    }
}
