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

    public static boolean checkPrivateByDefault = false;

    /**
     * All methods found with that annotation
     * @param classToCheck class to check
     * @param annotationClass the annotation class
     * @return The methods found
     */
    public static Method[] getAllMethodsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass) {
        return getAllMethodsWithAnnotation(classToCheck, annotationClass, checkPrivateByDefault);
    }

    /**
     * Finds all methods with that Annotation
     * @param classToCheck class to check
     * @param annotationClass The annotation class
     * @param checkPrivate do I check private
     * @return the methods found
     */
    public static Method[] getAllMethodsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass, boolean checkPrivate) {
        Method[] methodsToCheck;
        if (checkPrivate) {
            methodsToCheck = classToCheck.getDeclaredMethods();
        } else {
            methodsToCheck = classToCheck.getMethods();
        }
        List<Method> methods = new ArrayList<>();
        for (Method method : methodsToCheck) {
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                if (annotation.annotationType() == annotationClass) {
                    methods.add(method);
                    break;
                }
            }
        }
        return methods.toArray(new Method[0]);
    }

    /**
     * The number of methods found
     * @param classToCheck the class to check
     * @param annotationClass the Annotation class
     * @return the number of methods found
     */
    public static int getNumberOfMethodsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass) {
        return getNumberOfMethodsWithAnnotation(classToCheck, annotationClass, checkPrivateByDefault);
    }

    /**
     * The number of methods found
     * @param classToCheck the class to check
     * @param annotationClass the annotation class
     * @param checkPrivate check private methods
     * @return the number of methods found
     */
    public static int getNumberOfMethodsWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass, boolean checkPrivate) {
        return getAllMethodsWithAnnotation(classToCheck, annotationClass, checkPrivate).length;
    }

    /**
     * The first method found with that Annotation
     * @param classToCheck class to check
     * @param annotationClass The annotation class
     * @return the method found
     */
    public static Method getFirstMethodWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass) {
        return getFirstMethodWithAnnotation(classToCheck, annotationClass, checkPrivateByDefault);
    }
    /**
     * The first method found with that Annotation
     * @param classToCheck class to check
     * @param annotationClass The annotation class
     * @param checkPrivate check private methods
     * @return the method found
     */
    public static Method getFirstMethodWithAnnotation(Class classToCheck, Class<? extends Annotation> annotationClass, boolean checkPrivate) {
        Method[] methodsToCheck;
        if (checkPrivate) {
            methodsToCheck = classToCheck.getDeclaredMethods();
        } else {
            methodsToCheck = classToCheck.getMethods();
        }
        for (Method method : methodsToCheck) {
            for (Annotation annotation : method.getAnnotations()) {
                if (annotation.annotationType() == annotationClass) {
                    return method;
                }
            }
        }
        return null;
    }
}
