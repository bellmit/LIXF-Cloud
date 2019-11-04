package ll.mrli.lixf.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:lixf-auth.properties"})
@ConfigurationProperties(prefix = "lixf.auth")
public class LixfAuthProperties {

    private LixfClientsProperties[] clients = {};
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;
    // 免认证路径
    private String anonUrl;
    // 验证码配置类
    private LixfValidateCodeProperties code = new LixfValidateCodeProperties();
}
