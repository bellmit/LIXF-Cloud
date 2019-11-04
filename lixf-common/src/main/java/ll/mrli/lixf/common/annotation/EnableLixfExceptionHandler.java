package ll.mrli.lixf.common.annotation;

import ll.mrli.lixf.common.configure.LixfAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LixfAuthExceptionConfigure.class)
public @interface EnableLixfExceptionHandler {

}
