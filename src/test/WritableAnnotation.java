import java.lang.annotation.Annotation;

public class WritableAnnotation implements TestAnnotation{
    private String hey;
private TestAnnotation testAnnotation;

    public WritableAnnotation(String hey, TestAnnotation testAnnotation) {
        this.hey = hey;
        this.testAnnotation = testAnnotation;
    }

    @Override
    public String hey() {
        return hey;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return testAnnotation.annotationType();
    }
}
