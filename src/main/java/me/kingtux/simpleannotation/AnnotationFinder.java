package me.kingtux.simpleannotation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
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
 */
public class AnnotationFinder {

  /**
   * This finds all the methods with an annotation clazz
   * It will throw an exception if annotation is not an annotation
   * @param clazz The class to check!
   * @param annotation the annotation to use to check MUST BE AN ANNOTATION
   * @return All the methods found that has that annotation
   */
  public static Method[] getMethodsWithAnnotation(Class clazz, Class annotation){
    if (clazz == null) {
      throw new IllegalArgumentException("Null clazz passed");
    }
    if(annotation == null){
    throw new IllegalArgumentException("Null annotation passed");
    }
    Method[] methods = clazz.getDeclaredMethods();
    List<Method> methodList = new ArrayList<>();
    for(Method method : methods){
      for(Annotation annotation1 : method.getDeclaredAnnotations()){
        if(annotation.equals(annotation1.annotationType())){
         methodList.add(method);
         break;
       }
      }
    }
    return methodList.toArray(new Method[0]);
  }
  /**
   * This finds all the fields with an annotation clazz
   * It will throw an exception if annotation is not an annotation
   * @param clazz The class to check!
   * @param annotation the annotation to use to check MUST BE AN ANNOTATION
   * @return All the fields found that has that annotation
   */
  public static Field[] getFieldsWithAnnotation(Class clazz, Class annotation){

    if (clazz == null) {
      throw new IllegalArgumentException("Null clazz passed");
    }
    if(annotation == null){
    throw new IllegalArgumentException("Null annotation passed");
    }
    Field[] fields = clazz.getDeclaredFields();
    List<Field> fieldList = new ArrayList<>();
    for(Field field : fields){
      for(Annotation annotation1 : field.getDeclaredAnnotations()){
        if(annotation.equals(annotation1.annotationType())){
          fieldList.add(field);
         break;
       }
      }
    }
    return fieldList.toArray(new Field[0]);
  }

  /**
   * This finds all classses with an annotation and doesn't return the loaded class
   *
   * @param fileToCheck the file to check
   * @param annotation the annotation to look for
   * @return The paths of the classes found
   * @throws IOException Its an IOException
   */
  public static String[] getClassesWithAnnotation(File fileToCheck, Class annotation)
      throws IOException {
    if (fileToCheck == null) {
      throw new IllegalArgumentException("Null fileToCheck passed");
    }
    if(annotation == null){
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
   * This gets all classes with the annotation specified and loads it
   *
   * @param fileToCheck The file you want to check
   * @param annotation The annotation to look  for
   * @return all the classes found
   * @throws IOException This can be caused a few things
   */
  public static Class[] getClassesWithAnnotationAndLoad(File fileToCheck, Class annotation)
      throws IOException {
        if (fileToCheck == null) {
      throw new IllegalArgumentException("Null fileToCheck passed");
    }
    if(annotation == null){
    throw new IllegalArgumentException("Null annotation passed");
    }
    URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{fileToCheck.toURI().toURL()},
        AnnotationFinder.class.getClassLoader());
    List<Class> classesFound = new ArrayList<>();
    for (String clazzToCheck : getAllClassesInJar(fileToCheck)) {
      Class clazz;
      try {
        clazz = urlClassLoader.loadClass(clazzToCheck);
        for (Annotation annotation1 : clazz.getDeclaredAnnotations()) {
          if (annotation.equals(annotation1.annotationType())) {
            classesFound.add(clazz);
            break;
          }
        }
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    urlClassLoader.close();
    return classesFound.toArray(new Class[0]);
  }

  /**
   * Gets all the classes in a jar file. Source: https://stackoverflow.com/a/15720973
   *
   * @param file the file to  check
   * @return all the paths to classes in the jar
   * @throws IOException This can be thrown in a few different areas.
   */
  public  static String[] getAllClassesInJar(File file) throws IOException {
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
