package me.kingtux.simpleannotation;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ClassFinder {
    public static String[] getClassesInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation) {
        return getClassesInsideFileWithAnnotation(fileToCheck, annotation, Object.class);
    }

    public static String[] getClassesInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class classToExtend) {
        return getClassesInsideFileWithAnnotation(fileToCheck, annotation, classToExtend, null);
    }

    public static String[] getClassesInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, String packageToStartWith) {
        return getClassesInsideFileWithAnnotation(fileToCheck, annotation, Object.class, packageToStartWith);

    }

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


    public static String getFirstClassInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation) {
        return getFirstClassInsideFileWithAnnotation(fileToCheck, annotation, Object.class);
    }

    public static String getFirstClassInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, Class classToExtend) {
        return getFirstClassInsideFileWithAnnotation(fileToCheck, annotation, classToExtend, null);
    }

    public static String getFirstClassInsideFileWithAnnotation(File fileToCheck, Class<? extends Annotation> annotation, String packageToStartWith) {
        return getFirstClassInsideFileWithAnnotation(fileToCheck, annotation, Object.class, packageToStartWith);

    }

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
}
