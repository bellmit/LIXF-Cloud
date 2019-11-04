package ll.mrli.lixf.common.annotation;

import ll.mrli.lixf.common.selector.LixfCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(LixfCloudApplicationSelector.class)
public @interface LixfCloudApplication {
}
