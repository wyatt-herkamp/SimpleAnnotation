package me.kingtux.simpleannotation;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class finder!
 *
 * @author KingTux
 */
public class ClassFinder {
    /**
     * This will find all classes with an Annotation
     *
     * @param fileToCheck the file to read
     * @param annotation  The annotation to check for
     * @return The classes found with that Annotation
     */
    public static String[] getClassesInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation) {
        return getClassesInsideFileWithAnnotation(fileToCheck, annotation, Object.class);
    }

    /**
     * This will find all classes with an Annotation
     *
     * @param fileToCheck   the file to read
     * @param annotation    The annotation to check for
     * @param classToExtend the class you want the class to extend
     * @return The classes found with that Annotation
     */
    public static String[] getClassesInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class classToExtend) {
        return getClassesInsideFileWithAnnotation(fileToCheck, annotation, classToExtend, null);
    }

    /**
     * This will find all classes with an Annotation
     *
     * @param fileToCheck        the file to read
     * @param annotation         The annotation to check for
     * @param packageToStartWith the package's beggining
     * @return The classes found with that Annotation
     */
    public static String[] getClassesInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, String packageToStartWith) {
        return getClassesInsideFileWithAnnotation(fileToCheck, annotation, Object.class, packageToStartWith);

    }

    /**
     * This will find all classes with an Annotation
     *
     * @param fileToCheck        the file to read
     * @param annotation         The annotation to check for
     * @param classToExtend      the class you want the class to extend
     * @param packageToStartWith the package's beggining
     * @return The classes found with that Annotation
     */
    public static String[] getClassesInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class classToExtend, final String packageToStartWith) {

        String[] classes;
        try {
            classes = SimpleUtils.removeStringsThatDontStartWith(SimpleUtils.getAllClassesInJar(fileToCheck), packageToStartWith);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        URLClassLoader urlClassLoader = null;
        try {
            urlClassLoader = new URLClassLoader(new URL[]{fileToCheck.toURI().toURL()},
                    ClassFinder.class.getClassLoader());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        List<String> classesFound = new ArrayList<>();
        for (String clazzToCheck : classes) {
            Class clazz = null;
            try {
                clazz = urlClassLoader.loadClass(clazzToCheck);
                if (clazz == null) {
                    continue;
                }
                for (Annotation annotation1 : clazz.getDeclaredAnnotations()) {
                    if (annotation.equals(annotation1.annotationType())) {
                        if (clazz.getSuperclass() == classToExtend) {
                            classesFound.add(clazzToCheck);
                        }
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            urlClassLoader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classesFound.toArray(new String[0]);
    }

    /**
     * This will find the first class with an Annotation
     *
     * @param fileToCheck the file to read
     * @param annotation  The annotation to check for
     * @return The first found with that Annotation
     */
    public static String getFirstClassInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation) {
        return getFirstClassInsideFileWithAnnotation(fileToCheck, annotation, Object.class);
    }

    /**
     * This will find the first class with an Annotation
     *
     * @param fileToCheck   the file to read
     * @param annotation    The annotation to check for
     * @param classToExtend the class you want the found class to extend
     * @return The first found with that Annotation
     */
    public static String getFirstClassInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class classToExtend) {
        return getFirstClassInsideFileWithAnnotation(fileToCheck, annotation, classToExtend, null);
    }

    /**
     * This will find the first class with an Annotation
     *
     * @param fileToCheck        the file to read
     * @param annotation         The annotation to check for
     * @param packageToStartWith the package's beggining
     * @return The first found with that Annotation
     */
    public static String getFirstClassInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, String packageToStartWith) {
        return getFirstClassInsideFileWithAnnotation(fileToCheck, annotation, Object.class, packageToStartWith);

    }

    /**
     * This will find the first class with an Annotation
     *
     * @param fileToCheck        the file to read
     * @param annotation         The annotation to check for
     * @param packageToStartWith the package's beggining
     * @param classToExtend      the class you want the found class to extend
     * @return The first found with that Annotation
     */
    public static String getFirstClassInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class classToExtend, final String packageToStartWith) {

        String[] classes;
        try {
            classes = SimpleUtils.removeStringsThatDontStartWith(SimpleUtils.getAllClassesInJar(fileToCheck), packageToStartWith);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        URLClassLoader urlClassLoader = null;
        try {
            urlClassLoader = new URLClassLoader(new URL[]{fileToCheck.toURI().toURL()},
                    ClassFinder.class.getClassLoader());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        for (String clazzToCheck : classes) {
            Class clazz = null;
            try {
                clazz = urlClassLoader.loadClass(clazzToCheck);
                if (clazz == null) {
                    continue;
                }
                for (Annotation annotation1 : clazz.getDeclaredAnnotations()) {
                    if (annotation.equals(annotation1.annotationType())) {
                        if (clazz.getSuperclass() == classToExtend) {
                            try {
                                urlClassLoader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return clazzToCheck;
                        }
                        break;
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            urlClassLoader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * The number of classes with that Annotation
     *
     * @param fileToCheck the file to read
     * @param annotation  The annotation to check for
     * @return The number classes found with that Annotation
     */
    public static int getNumberOfClassesWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation) {
        return getNumberOfClassesWithAnnotation(fileToCheck, annotation, Object.class);
    }

    /**
     * The number of classes with that Annotation
     *
     * @param fileToCheck   the file to read
     * @param annotation    The annotation to check for
     * @param classToExtend the class you want the class to extend
     * @return The number classes found with that Annotation
     */
    public static int getNumberOfClassesWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class classToExtend) {
        return getNumberOfClassesWithAnnotation(fileToCheck, annotation, classToExtend, null);
    }

    /**
     * The number of classes with that Annotation
     *
     * @param fileToCheck        the file to read
     * @param annotation         The annotation to check for
     * @param packageToStartWith the package's beggining
     * @return The number classes found with that Annotation
     */
    public static int getNumberOfClassesWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, String packageToStartWith) {
        return getNumberOfClassesWithAnnotation(fileToCheck, annotation, Object.class, packageToStartWith);

    }

    /**
     * The number of classes with that Annotation
     *
     * @param fileToCheck        the file to read
     * @param annotation         The annotation to check for
     * @param classToExtend      the class you want the class to extend
     * @param packageToStartWith the package's beggining
     * @return The number classes found with that Annotation
     */
    public static int getNumberOfClassesWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class classToExtend, final String packageToStartWith) {
        return getClassesInsideFileWithAnnotation(fileToCheck, annotation, classToExtend, packageToStartWith).length;
    }
}
