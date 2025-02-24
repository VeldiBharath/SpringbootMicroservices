package com.bharath.catalog_service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	//when we add test containers in spring initializer, this helps in spin up a docker container locally using the
	//current configuration
	//The postgres:16-alpine Docker image is an official lightweight PostgresSQL 16 container based on Alpine Linux.
	//For Development & Testing: You can skip docker-compose.yml since Testcontainers will handle the database
		// dynamically. Just ensure Docker Desktop is running.
		//For Deployment & Running in Docker: Use Dockerfile to package your app and docker-compose.yml to manage services.
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));
	}

}
