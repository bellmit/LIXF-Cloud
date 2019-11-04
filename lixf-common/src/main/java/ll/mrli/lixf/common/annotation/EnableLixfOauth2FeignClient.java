package ll.mrli.lixf.common.annotation;

import ll.mrli.lixf.common.configure.LixfOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = LixfOAuth2FeignConfigure.class)
public @interface EnableLixfOauth2FeignClient {
}
