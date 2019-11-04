package ll.mrli.lixf.auth;

import ll.mrli.lixf.common.annotation.EnableLixfExceptionHandler;
import ll.mrli.lixf.common.annotation.EnableLixfLettuceRedis;
import ll.mrli.lixf.common.annotation.EnableServerProtect;
import ll.mrli.lixf.common.annotation.LixfCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@LixfCloudApplication
@EnableLixfLettuceRedis
@MapperScan("ll.mrli.lixf.auth.mapper")
public class LixfAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(LixfAuthApplication.class, args);
    }

}
