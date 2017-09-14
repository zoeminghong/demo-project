package com.example.demo.activiti;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class SpringBootActivitiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActivitiDemoApplication.class, args);
	}
//	@Bean
//	public ProcessEngineConfiguration processEngineConfiguration(){
//		return new StandaloneProcessEngineConfiguration().setDatabaseType("h2")
//				.setDatabaseSchemaUpdate("true").setHistory("full");
//	}

	@Bean
	public CommandLineRunner init(final RepositoryService repositoryService,
								  final RuntimeService runtimeService,
								  final TaskService taskService) {

		return strings -> {
            System.out.println("Number of process definitions : "
                    + repositoryService.createProcessDefinitionQuery().count());
            System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
            runtimeService.startProcessInstanceByKey("oneTaskProcess");
            System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
        };

	}
	@Bean
	public DataSource database() {
		return DataSourceBuilder.create()
				.url("jdbc:h2:mem:test")
				.username("root")
				.password("123456")
				.driverClassName("org.h2.Driver")
				.build();
	}
}
