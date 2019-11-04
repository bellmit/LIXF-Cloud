### LIXF Cloud 微服务权限系统
![https://img.shields.io/badge/license-Apache%202.0-blue.svg?longCache=true&style=flat-square](https://img.shields.io/badge/license-Apache%202.0-blue.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/springcloud-Greenwich.SR3-yellow.svg?style=flat-square](https://img.shields.io/badge/springcloud-Greenwich.SR3-yellow.svg?style=flat-square)
![https://img.shields.io/badge/SpringCloudAlibaba-0.9.0.RELEASE-blueviolet.svg?style=flat-square](https://img.shields.io/badge/SpringCloudAlibaba-0.9.0.RELEASE-blueviolet.svg?style=flat-square)
![https://img.shields.io/badge/springboot-2.1.8.RELEASE-brightgreen.svg?style=flat-square](https://img.shields.io/badge/springboot-2.1.8.RELEASE-brightgreen.svg?style=flat-square)
![https://img.shields.io/badge/vue-2.6.10-orange.svg?style=flat-square](https://img.shields.io/badge/vue-2.6.10-orange.svg?style=flat-square)

LIXF Cloud是一款使用Spring Cloud Greenwich.SR3、Spring Cloud OAuth2 & Spring Cloud Alibaba构建的低耦合权限管理系统，前端（LIXF Cloud Web）采用vue element admin构建。该系统具有如下特点：

1. 前后端分离架构，客户端和服务端纯Token交互；
 
2. 认证服务器与资源服务器分离，方便接入自己的微服务系统；

3. 微服务防护，客户端请求资源只能通过微服务网关获取；

4. 集成Spring Boot Admin，多维度监控微服务；

5. 集成Spring Cloud Alibaba Nacos服务治理和集中配置管理；

6. 网关集成Sentinel流控；

7. 集成Zipkin，方便跟踪Feign调用链；

8. 集成ELK，集中管理日志，便于问题分析；

9. 微服务Docker化，使用Docker Compose一键部署；

10. 提供详细的使用文档和搭建教程；

11. 前后端请求参数校验，Excel导入导出，代码生成等。