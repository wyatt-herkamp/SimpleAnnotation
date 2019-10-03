package me.kingtux.simpleannotation;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

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
    public static String[] getClassesInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class<?> classToExtend) {
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
    public static String[] getClassesInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class<?> classToExtend, final String packageToStartWith) {
        try {
            String[] classes = SimpleUtils.removeStringsThatDontStartWith(SimpleUtils.getAllClassesInJar(fileToCheck), packageToStartWith);

            try (URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{fileToCheck.toURI().toURL()}, ClassFinder.class.getClassLoader())) {
                return Arrays.stream(classes).map(checking -> {
                    try {
                        return new AbstractMap.SimpleEntry<>(urlClassLoader.loadClass(checking), checking);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        return null;
                    }
                }).filter(Objects::nonNull)
                        .flatMap(entry -> Arrays.stream(entry.getKey().getDeclaredAnnotations())
                                .filter(annotation1 -> annotation.equals(annotation1.annotationType()))
                                .filter($ -> entry.getKey().getSuperclass().equals(classToExtend))
                                .map($ -> entry.getValue()))
                        .toArray(String[]::new);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    /**
     * This will find the first class with an Annotation
     *
     * @param fileToCheck the file to read
     * @param annotation  The annotation to check for
     * @return The first found with that Annotation
     */
    public static Optional<String> getFirstClassInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation) {
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
    public static Optional<String> getFirstClassInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class<?> classToExtend) {
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
    public static Optional<String> getFirstClassInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, String packageToStartWith) {
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
    public static Optional<String> getFirstClassInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class<?> classToExtend, final String packageToStartWith) {
        String[] classes = getClassesInsideFileWithAnnotation(fileToCheck, annotation, classToExtend, packageToStartWith);
        return Optional.ofNullable(classes.length > 0 ? classes[0] : null);
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
    public static int getNumberOfClassesWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class<?> classToExtend) {
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
    public static int getNumberOfClassesWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class<?> classToExtend, final String packageToStartWith) {
        return getClassesInsideFileWithAnnotation(fileToCheck, annotation, classToExtend, packageToStartWith).length;
    }
}
