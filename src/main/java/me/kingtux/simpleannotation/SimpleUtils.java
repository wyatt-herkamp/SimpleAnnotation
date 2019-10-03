package me.kingtux.simpleannotation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.util.Objects.requireNonNull;

/**
 * As of 1.3  these methods are here to help SimpleAnnotation and this class shouldn't be used
 *
 * @author KingTux
 */
public class SimpleUtils {

    /**
     * Gets all the classes in a jar file. Source: https://stackoverflow.com/a/15720973
     *
     * @param file the file to  check
     * @return all the paths to classes in the jar
     * @throws IOException This can be thrown in a few different areas.
     */
    public static String[] getAllClassesInJar(File file) throws IOException {
        requireNonNull(file, "Null file passed");
        List<String> classNames = new ArrayList<>();
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(file))) {
            for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    String className = entry.getName().replace('/', '.');
                    classNames.add(className.substring(0, className.length() - ".class".length()));
                }
            }
        }
        return classNames.toArray(new String[0]);
    }

    public static String[] removeStringsThatDontStartWith(String[] strings, String starter) {
        if (starter == null) {
            return strings;
        }

        String starterLowerCase = starter.toLowerCase();
        return Arrays.stream(strings).filter(string -> starter.toLowerCase().startsWith(starterLowerCase)).toArray(String[]::new);
    }
}
