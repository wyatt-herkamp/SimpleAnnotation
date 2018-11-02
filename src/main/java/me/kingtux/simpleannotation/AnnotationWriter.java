package me.kingtux.simpleannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * This class is here to edit Annotations at runtime
 * @author KingTux
 * @since 1.2
 */
public class AnnotationWriter {
    /**
     * This edits an annotation on runtime. Warning it only supports classes
     *
     * @param classToLookAt     The class you want to edit
     * @param annotationToAlter Annotation to edit
     * @param newAnnotation     The new annotation. Look at class WritableAnnotation to see how to do it
     */
    public static void writeToAnnotation(Class classToLookAt, Class<? extends Annotation> annotationToAlter,
                                         Annotation newAnnotation) {
        try {
            Method method = Class.class.getDeclaredMethod("annotationData", null);
            method.setAccessible(true);
            Object annotationData = method.invoke(classToLookAt);
            Field annotations = annotationData.getClass().getDeclaredField("annotations");
            annotations.setAccessible(true);
            Map<Class<? extends Annotation>, Annotation> map = (Map<Class<? extends Annotation>, Annotation>) annotations.get(annotationData);
            map.put(annotationToAlter, newAnnotation);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
