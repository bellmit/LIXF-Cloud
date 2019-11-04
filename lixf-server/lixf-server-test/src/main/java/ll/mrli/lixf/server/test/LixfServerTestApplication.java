package ll.mrli.lixf.server.test;

import ll.mrli.lixf.common.annotation.EnableLixfExceptionHandler;
import ll.mrli.lixf.common.annotation.EnableLixfOauth2FeignClient;
import ll.mrli.lixf.common.annotation.EnableServerProtect;
import ll.mrli.lixf.common.annotation.LixfCloudApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@LixfCloudApplication
public class LixfServerTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LixfServerTestApplication.class, args);
    }

}
