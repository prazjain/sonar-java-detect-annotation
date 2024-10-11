
import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Generated // Noncompliant {{Not allowed to create an annotation with name that contains 'Generated'}}
{


}