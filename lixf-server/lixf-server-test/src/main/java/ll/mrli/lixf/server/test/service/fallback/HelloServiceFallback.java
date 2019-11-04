package ll.mrli.lixf.server.test.service.fallback;

import feign.hystrix.FallbackFactory;
import ll.mrli.lixf.common.entity.LixfServerConstant;
import ll.mrli.lixf.server.test.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloServiceFallback implements FallbackFactory<IHelloService> {
    @Override
    public IHelloService create(Throwable throwable) {
        return name -> {
            log.error("调用"+ LixfServerConstant.LIXF_SERVER_SYSTEM +"服务出错",throwable);
            return "调用出错";
        };
    }
}
