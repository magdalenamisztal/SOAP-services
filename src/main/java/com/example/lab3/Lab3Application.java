package com.example.lab3;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

@SpringBootApplication
@Configuration
@EnableWs
public class Lab3Application extends WsConfigurerAdapter {

	public static void main(String[] args) {
		Application.launch(JavaFxApplication.class, args);
	}

}
