package me.kingtux.simpleannotation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * As of 1.3  these methods are here to help SimpleAnnotation and this class shouldn't be used
 *
 * @author KingTux
 */
public class SimpleUtils {
    public static <T> T notNull(final T object, final String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }

    /**
     * Gets all the classes in a jar file. Source: https://stackoverflow.com/a/15720973
     *
     * @param file the file to  check
     * @return all the paths to classes in the jar
     * @throws IOException This can be thrown in a few different areas.
     */
    public static String[] getAllClassesInJar(File file) throws IOException {
        if (file == null) {
            notNull(file, "Null File Passed");
        }
        List<String> classNames = new ArrayList<>();
        ZipInputStream zip = new ZipInputStream(new FileInputStream(file));
        for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
            if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                String className = entry.getName().replace('/', '.');
                classNames.add(className.substring(0, className.length() - ".class".length()));
            }
        }
        return classNames.toArray(new String[0]);
    }

    public static String[] removeStringsThatDontStartWith(String[] strings, String starter) {
        if (starter == null) {
            return strings;
        }
        List<String> lists = new ArrayList<>();
        for (String s : strings) {
            if (s.toLowerCase().startsWith(starter.toLowerCase())) {
                lists.add(s);
            }
        }
        return lists.toArray(new String[0]);
    }
}
