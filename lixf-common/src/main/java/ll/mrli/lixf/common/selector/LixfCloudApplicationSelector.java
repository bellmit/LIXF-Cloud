package ll.mrli.lixf.common.selector;

import ll.mrli.lixf.common.configure.LixfAuthExceptionConfigure;
import ll.mrli.lixf.common.configure.LixfOAuth2FeignConfigure;
import ll.mrli.lixf.common.configure.LixfServerProtectConfigure;
import ll.mrli.lixf.common.exception.LixfAuthException;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class LixfCloudApplicationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                LixfAuthExceptionConfigure.class.getName(),
                LixfOAuth2FeignConfigure.class.getName(),
                LixfServerProtectConfigure.class.getName()
        };
    }
}
