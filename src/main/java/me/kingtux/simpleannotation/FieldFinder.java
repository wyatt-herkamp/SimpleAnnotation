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
    //getAllFieldsWithAnnotation(Class class, Annotation annotation)
//getAllFieldsWithAnnotation(Class class, Annotation annotation, checkPrivate)
//getFirstFieldWithAnnotation(Class class, Annotation annotation{
//getFirstFieldWithAnnotation(Class class, Annotation annotation, boolean checkPrivate}
    public static boolean checkPrivateByDefault = false;

    public static Field[] getAllFieldsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass) {
        return getAllFieldsWithAnnotation(classToCheck, annotationClass, checkPrivateByDefault);
    }

    public static Field[] getAllFieldsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass, boolean checkPrivate) {
        Field[] FieldsToCheck;
        if (checkPrivate) {
            FieldsToCheck = classToCheck.getDeclaredFields();
        } else {
            FieldsToCheck = classToCheck.getFields();
        }
        List<Field> Fields = new ArrayList<>();
        for (Field Field : FieldsToCheck) {
            for (Annotation annotation : Field.getDeclaredAnnotations()) {
                if (annotation.annotationType() == annotationClass) {
                    Fields.add(Field);
                    break;
                }
            }
        }
        return Fields.toArray(new Field[0]);
    }

    public static int getNumberOfFieldsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass) {
        return getNumberOfFieldsWithAnnotation(classToCheck, annotationClass, checkPrivateByDefault);
    }

    public static int getNumberOfFieldsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass, boolean checkPrivate) {
        return getAllFieldsWithAnnotation(classToCheck, annotationClass, checkPrivate).length;
    }

    public static Field getFirstFieldWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass) {
        return getFirstFieldWithAnnotation(classToCheck, annotationClass, checkPrivateByDefault);
    }

    public static Field getFirstFieldWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass, boolean checkPrivate) {
        Field[] FieldsToCheck;
        if (checkPrivate) {
            FieldsToCheck = classToCheck.getDeclaredFields();
        } else {
            FieldsToCheck = classToCheck.getFields();
        }
        for (Field Field : FieldsToCheck) {
            for (Annotation annotation : Field.getAnnotations()) {
                if (annotation.annotationType() == annotationClass) {
                    return Field;
                }
            }
        }
        return null;
    }
}
