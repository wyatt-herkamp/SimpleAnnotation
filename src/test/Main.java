import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import me.kingtux.simpleannotation.AnnotationFinder;
import me.kingtux.simpleannotation.AnnotationWriter;

public class Main {

    public static void main(String[] args) {
      //  Method method = AnnotationFinder.getMethodsWithAnnotation(TestClass.class, TestAnnotation.class)[0];
        AnnotationWriter.writeToAnnotation(TestClass.class, TestAnnotation.class,
                new WritableAnnotation("The Ninja King", TestClass.class.getAnnotation(TestAnnotation.class)));
        System.out.println(TestClass.class.getAnnotation(TestAnnotation.class).hey());

         Method method = AnnotationFinder.getMethodsWithAnnotation(TestClass.class, TestAnnotation.class)[0];

        AnnotationWriter.writeToAnnotation(method, TestAnnotation.class,
                new WritableAnnotation("The Ninja King", TestClass.class.getAnnotation(TestAnnotation.class)));
        System.out.println(method.getAnnotation(TestAnnotation.class).hey());
    }

}
