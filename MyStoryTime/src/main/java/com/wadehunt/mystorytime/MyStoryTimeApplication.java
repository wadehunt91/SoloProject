package com.wadehunt.mystorytime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class MyStoryTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyStoryTimeApplication.class, args);
	}

}
