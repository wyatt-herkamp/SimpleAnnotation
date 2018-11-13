package me.kingtux.simpleannotation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * The SimpleAnnotation Core at the moment 1.0 this is all we have
 *
 * @author KingTux
 * @deprecated This class is being deprecated to move to a most clean and oranized way of doing this. Will be removed in 1.4. Deprecated in 1.3
 */
@Deprecated
public class AnnotationFinder {


    /**
     * This finds all the methods with an annotation clazz
     * It will throw an exception if annotation is not an annotation
     *
     * @param clazz      The class to check!
     * @param annotation the annotation to use to check MUST BE AN ANNOTATION
     * @return All the methods found that has that annotation
     */
    public static Method[] getMethodsWithAnnotation(Class clazz, Class<? extends Annotation> annotation) {
        return MethodFinder.getAllMethodsWithAnnotation(clazz, annotation, true);
    }

    /**
     * This finds all the fields with an annotation clazz
     * It will throw an exception if annotation is not an annotation
     *
     * @param clazz      The class to check!
     * @param annotation the annotation to use to check MUST BE AN ANNOTATION
     * @return All the fields found that has that annotation
     */
    public static Field[] getFieldsWithAnnotation(Class clazz, Class<? extends Annotation> annotation) {
return FieldFinder.getAllFieldsWithAnnotation(clazz, annotation, true);
    }

    /**
     * This finds all classses with an annotation and doesn't return the loaded class
     *
     * @param fileToCheck the file to check
     * @param annotation  the annotation to look for
     * @return The paths of the classes found
     * @throws IOException Its an IOException
     */
    public static String[] getClassesWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation)
            throws IOException {
        if (fileToCheck == null) {
            throw new IllegalArgumentException("Null fileToCheck passed");
        }
        if (annotation == null) {
            throw new IllegalArgumentException("Null annotation passed");
        }
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{fileToCheck.toURI().toURL()},
                AnnotationFinder.class.getClassLoader());
        List<String> classesFound = new ArrayList<>();
        for (String clazzToCheck : getAllClassesInJar(fileToCheck)) {
            Class clazz;
            try {
                clazz = urlClassLoader.loadClass(clazzToCheck);
                for (Annotation annotation1 : clazz.getDeclaredAnnotations()) {
                    if (annotation.equals(annotation1.annotationType())) {
                        classesFound.add(clazzToCheck);
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        urlClassLoader.close();
        return classesFound.toArray(new String[0]);
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
            throw new IllegalArgumentException("Null file passed");
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

}
