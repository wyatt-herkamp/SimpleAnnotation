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
        return ClassFinder.getClassesInsideFileWithAnnotation(fileToCheck, annotation);
    }

}
