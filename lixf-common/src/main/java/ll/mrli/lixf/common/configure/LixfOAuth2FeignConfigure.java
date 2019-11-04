package ll.mrli.lixf.common.configure;

import feign.RequestInterceptor;
import ll.mrli.lixf.common.entity.LixfConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.Base64Utils;

public class LixfOAuth2FeignConfigure {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(){
        return requestTemplate -> {

            String zuulToken = new String(Base64Utils.encode(LixfConstant.ZUUL_TOKEN_VALUE.getBytes()));
            requestTemplate.header(LixfConstant.ZUUL_TOKEN_HEADER,zuulToken);

            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if(details instanceof OAuth2AuthenticationDetails) {
                String tokenValue = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION,"bearer " +tokenValue);
            }
        };
    }
}
