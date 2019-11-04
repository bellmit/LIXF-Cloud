package ll.mrli.lixf.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class LixfRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(LixfRegisterApplication.class, args);
    }

}
