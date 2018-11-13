package me.kingtux.simpleannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
/**
 * A Method finder!
 * @since 1.3
 * @author KingTux
 */
public class MethodFinder {
    //getAllMethodsWithAnnotation(Class class, Annotation annotation)
//getAllMethodsWithAnnotation(Class class, Annotation annotation, checkPrivate)
//getFirstMethodWithAnnotation(Class class, Annotation annotation{
//getFirstMethodWithAnnotation(Class class, Annotation annotation, boolean checkPrivate}
    public static boolean checkPrivateByDefault = false;

    public static Method[] getAllMethodsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass) {
        return getAllMethodsWithAnnotation(classToCheck, annotationClass, checkPrivateByDefault);
    }

    public static Method[] getAllMethodsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass, boolean checkPrivate) {
        Method[] methodsToCheck;
        if (checkPrivate) {
            methodsToCheck = classToCheck.getDeclaredMethods();
        } else {
            methodsToCheck = classToCheck.getMethods();
        }
        List<Method> methods = new ArrayList<>();
        for (Method method : methodsToCheck) {
            for (Annotation annotation : method.getAnnotations()) {
                if (annotation.getClass() == annotationClass) {
                    methods.add(method);
                }
            }
        }
        return methods.toArray(new Method[0]);
    }

    public static int getNumberOfMethodsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass) {
        return getNumberOfMethodsWithAnnotation(classToCheck, annotationClass, checkPrivateByDefault);
    }

    public static int getNumberOfMethodsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass, boolean checkPrivate) {
        return getAllMethodsWithAnnotation(classToCheck, annotationClass, checkPrivate).length;
    }

    public static Method getFirstMethodWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass) {
        return getFirstMethodWithAnnotation(classToCheck, annotationClass, checkPrivateByDefault);
    }

    public static Method getFirstMethodWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass, boolean checkPrivate) {
        Method[] methodsToCheck;
        if (checkPrivate) {
            methodsToCheck = classToCheck.getDeclaredMethods();
        } else {
            methodsToCheck = classToCheck.getMethods();
        }
        for (Method method : methodsToCheck) {
            for (Annotation annotation : method.getAnnotations()) {
                if (annotation.getClass() == annotationClass) {
                    return method;
                }
            }
        }
        return null;
    }
}
