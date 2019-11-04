package ll.mrli.lixf.gateway.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource({"classpath:lixf-gateway.properties"})
@ConfigurationProperties(prefix = "lixf.gateway")
public class LixfGatewayProperties {

    private String forbidRequestUri;
}
