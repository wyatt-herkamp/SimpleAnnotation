package me.kingtux.simpleannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * A field finder!
 * @since 1.3
 * @author KingTux
 */
public class FieldFinder {
    public static boolean checkPrivateByDefault = false;

    /**
     * All Fields found with that annotation
     * @param classToCheck class to check
     * @param annotationClass the annotation class
     * @return The Fields found
     */
    public static Field[] getAllFieldsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass) {
        return getAllFieldsWithAnnotation(classToCheck, annotationClass, checkPrivateByDefault);
    }

    /**
     * Finds all Fields with that Annotation
     * @param classToCheck class to check
     * @param annotationClass The annotation class
     * @param checkPrivate do I check private
     * @return the Fields found
     */
    public static Field[] getAllFieldsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass, boolean checkPrivate) {
        Field[] fieldsToCheck;
        if (checkPrivate) {
            fieldsToCheck = classToCheck.getDeclaredFields();
        } else {
            fieldsToCheck = classToCheck.getFields();
        }
        List<Field> Fields = new ArrayList<>();
        for (Field Field : fieldsToCheck) {
            for (Annotation annotation : Field.getDeclaredAnnotations()) {
                if (annotation.annotationType() == annotationClass) {
                    Fields.add(Field);
                    break;
                }
            }
        }
        return Fields.toArray(new Field[0]);
    }

    /**
     * The number of Fields found
     * @param classToCheck the class to check
     * @param annotationClass the Annotation class
     * @return the number of Fields found
     */
    public static int getNumberOfFieldsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass) {
        return getNumberOfFieldsWithAnnotation(classToCheck, annotationClass, checkPrivateByDefault);
    }

    /**
     * The number of Fields found
     * @param classToCheck the class to check
     * @param annotationClass the annotation class
     * @param checkPrivate check private Fields
     * @return the number of Fields found
     */
    public static int getNumberOfFieldsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass, boolean checkPrivate) {
        return getAllFieldsWithAnnotation(classToCheck, annotationClass, checkPrivate).length;
    }

    /**
     * The first Field found with that Annotation
     * @param classToCheck class to check
     * @param annotationClass The annotation class
     * @return the Field found
     */
    public static Field getFirstFieldWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass) {
        return getFirstFieldWithAnnotation(classToCheck, annotationClass, checkPrivateByDefault);
    }
    /**
     * The first Field found with that Annotation
     * @param classToCheck class to check
     * @param annotationClass The annotation class
     * @param checkPrivate check private Fields
     * @return the Field found
     */
    public static Field getFirstFieldWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass, boolean checkPrivate) {
        Field[] fieldsToCheck;
        if (checkPrivate) {
            fieldsToCheck = classToCheck.getDeclaredFields();
        } else {
            fieldsToCheck = classToCheck.getFields();
        }
        for (Field Field : fieldsToCheck) {
            for (Annotation annotation : Field.getAnnotations()) {
                if (annotation.annotationType() == annotationClass) {
                    return Field;
                }
            }
        }
        return null;
    }
}
