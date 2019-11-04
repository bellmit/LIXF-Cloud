package ll.mrli.lixf.server.system;

import ll.mrli.lixf.common.annotation.EnableLixfExceptionHandler;
import ll.mrli.lixf.common.annotation.EnableServerProtect;
import ll.mrli.lixf.common.annotation.LixfCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@LixfCloudApplication
@EnableTransactionManagement
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan("ll.mrli.lixf.server.system.mapper")
public class LixfServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(LixfServerSystemApplication.class, args);
    }

}
