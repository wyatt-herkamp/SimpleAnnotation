import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import me.kingtux.simpleannotation.AnnotationFinder;
import me.kingtux.simpleannotation.AnnotationWriter;
import me.kingtux.simpleannotation.MethodFinder;

public class Main {

    public static void main(String[] args) {
        System.out.println(MethodFinder.getNumberOfMethodsWithAnnotation(TestClass.class, TestAnnotation.class,true));
    }

}
