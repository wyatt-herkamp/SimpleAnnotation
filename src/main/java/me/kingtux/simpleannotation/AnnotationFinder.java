package me.kingtux.simpleannotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;

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
    Validate.notNull(clazz, "The clazz to check cannot be null");
    Validate.notNull(annotation, "The annotation cannot be null");
    Validate.isTrue(annotation.isAnnotation(), "The class must be an annotation");
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
    Validate.notNull(clazz, "The clazz to check cannot be null");
    Validate.notNull(annotation, "The annotation cannot be null");
    Validate.isTrue(annotation.isAnnotation(), "The class must be an annotation");
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
}
