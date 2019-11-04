package ll.mrli.lixf.server.system.configure;


import ll.mrli.lixf.common.handler.LixfAccessDeniedHandler;
import ll.mrli.lixf.common.handler.LixfAuthExceptionEntryPoint;
import ll.mrli.lixf.server.system.properties.LixfServerSystemProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class LixfServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private LixfAccessDeniedHandler accessDeniedHandler;
    @Autowired
    private LixfAuthExceptionEntryPoint authExceptionEntryPoint;
    @Autowired
    private LixfServerSystemProperties properties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");
        http.csrf().disable().authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .and()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(authExceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }

}
