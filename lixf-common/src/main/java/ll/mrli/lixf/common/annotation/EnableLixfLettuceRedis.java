package ll.mrli.lixf.common.annotation;

import ll.mrli.lixf.common.configure.LixfLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LixfLettuceRedisConfigure.class)
public @interface EnableLixfLettuceRedis {
}
