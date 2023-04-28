package com.example.spring_sql_cassandra_clickhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableSwagger2
@EnableWebMvc
public class SpringSqlCassandraClickhouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSqlCassandraClickhouseApplication.class, args);
	}

}
