package ll.mrli.lixf.server.system.configure;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import ll.mrli.lixf.server.system.properties.LixfServerSystemProperties;
import ll.mrli.lixf.server.system.properties.LixfSwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class LixfWebConfigure {

    @Autowired
    private LixfServerSystemProperties properties;

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

    @Bean
    public Docket swaggerApi() {
        LixfSwaggerProperties lixfSwaggerProperties = properties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(lixfSwaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(lixfSwaggerProperties))
                .securitySchemes(Collections.singletonList(securityScheme(lixfSwaggerProperties)))
                .securityContexts(Collections.singletonList(securityContext(lixfSwaggerProperties)));
    }

    private ApiInfo apiInfo(LixfSwaggerProperties lixfSwaggerProperties) {
        return new ApiInfo(
                lixfSwaggerProperties.getTitle(),
                lixfSwaggerProperties.getDescription(),
                lixfSwaggerProperties.getVersion(),
                null,
                new Contact(lixfSwaggerProperties.getAuthor(), lixfSwaggerProperties.getUrl(), lixfSwaggerProperties.getEmail()),
                lixfSwaggerProperties.getLicense(), lixfSwaggerProperties.getLicenseUrl(), Collections.emptyList());
    }

    private SecurityScheme securityScheme(LixfSwaggerProperties swaggerProperties) {
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(swaggerProperties.getGrantUrl());

        return new OAuthBuilder()
                .name(swaggerProperties.getName())
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes(swaggerProperties)))
                .build();
    }

    private SecurityContext securityContext(LixfSwaggerProperties swaggerProperties) {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference(swaggerProperties.getName(), scopes(swaggerProperties))))
                .forPaths(PathSelectors.any())
                .build();
    }

    private AuthorizationScope[] scopes(LixfSwaggerProperties lixfSwaggerProperties) {
        return new AuthorizationScope[]{
                new AuthorizationScope(lixfSwaggerProperties.getScope(), "")
        };
    }
}
