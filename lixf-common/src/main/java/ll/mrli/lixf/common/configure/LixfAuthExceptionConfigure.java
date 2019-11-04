package ll.mrli.lixf.common.configure;

import ll.mrli.lixf.common.handler.LixfAccessDeniedHandler;
import ll.mrli.lixf.common.handler.LixfAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class LixfAuthExceptionConfigure {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public LixfAccessDeniedHandler accessDeniedHandler() {
        return new LixfAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name="authenticationEntryPoint")
    public LixfAuthExceptionEntryPoint authExceptionEntryPoint() {
        return new LixfAuthExceptionEntryPoint();
    }
}
