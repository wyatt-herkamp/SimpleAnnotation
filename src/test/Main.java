import java.lang.reflect.Field;
import java.lang.reflect.Method;
import me.kingtux.simpleannotation.AnnotationFinder;

public class Main {
public static void main(String[] args){
  Method[] methods =AnnotationFinder.getMethodsWithAnnotation(TestClass.class,TestAnnotation.class) ;
  for(Method method : methods){
    System.out.println("Method " +method.getName());
  }

  Field[] fields =AnnotationFinder.getFieldsWithAnnotation(TestClass.class,TestAnnotation.class) ;
  for(Field field : fields){
    System.out.println("Field " +field.getName());
  }
}

}
