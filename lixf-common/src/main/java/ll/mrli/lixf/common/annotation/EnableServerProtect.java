package ll.mrli.lixf.common.annotation;

import ll.mrli.lixf.common.configure.LixfServerProtectConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LixfServerProtectConfigure.class)
public @interface EnableServerProtect {
}
