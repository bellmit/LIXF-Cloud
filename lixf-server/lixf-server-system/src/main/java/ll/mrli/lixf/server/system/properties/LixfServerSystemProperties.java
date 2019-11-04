package ll.mrli.lixf.server.system.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:lixf-server-system.properties"})
@ConfigurationProperties(prefix = "lixf.server.system")
public class LixfServerSystemProperties {
    /**
     * 免认证 URI，多个值的话以逗号分隔
     */
    private String anonUrl;

    private LixfSwaggerProperties swagger = new LixfSwaggerProperties();
}
