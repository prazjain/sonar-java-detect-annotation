
import java.lang.annotation.*;


@Documented
@Target({ElementType.TYPE})
public @interface IgnoreGeneratedJacocoCoverage // Noncompliant {{Not allowed to create an annotation with name that contains 'Generated'}}
{


}