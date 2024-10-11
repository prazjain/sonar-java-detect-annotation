
import java.lang.annotation.*;


@Documented // Noncompliant {{Not allowed to create an annotation with name that contains 'Generated'}}
@Target({ElementType.TYPE})
public @interface Generated
{


}