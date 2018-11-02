package me.danny.rabbitmqdemo;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * swagger 配置：http://localhost:8080/swagger-ui.html
 * https://github.com/SpringForAll/spring-boot-starter-swagger
 *
 * 可以从parent一路点进去
 <parent>
	 <groupId>org.springframework.boot</groupId>
	 <artifactId>spring-boot-dependencies</artifactId>
	 <version>2.1.0.RELEASE</version>
	 <relativePath>../../spring-boot-dependencies</relativePath>
 </parent>
 * ~/.m2/repository/org/springframework/boot/spring-boot-dependencies/2.1.0.RELEASE/spring-boot-dependencies-2.1.0.RELEASE.pom
 */
@EnableSwagger2Doc
@SpringBootApplication
public class RabbitmqDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqDemoApplication.class, args);
	}

}
