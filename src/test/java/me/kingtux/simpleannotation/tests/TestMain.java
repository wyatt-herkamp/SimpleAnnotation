
package me.kingtux.simpleannotation.tests;

import me.kingtux.simpleannotation.FieldFinder;
import me.kingtux.simpleannotation.MethodFinder;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("WeakerAccess")
public class TestMain {

    public static void main(String[] args) {
        new TestMain().testOne();
        System.out.println("Tests Completed");

    }

    @Test
    public void testOne() {
        assertEquals(MethodFinder.getNumberOfMethodsWithAnnotation(BasicClass.class, BasicAnnotation.class, false), 3);
        assertEquals(MethodFinder.getNumberOfMethodsWithAnnotation(BasicClass.class, BasicAnnotation.class, true), 4);
        assertEquals(FieldFinder.getNumberOfFieldsWithAnnotation(BasicClass.class, BasicAnnotation.class, true), 3);
    }

}
