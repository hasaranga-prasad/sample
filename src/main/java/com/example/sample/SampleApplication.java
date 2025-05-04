package com.example.sample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class SampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SampleApplication.class, args);


	}
	@Override
	public void run(String... args) throws Exception {

		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			URI uri = new URI("http://localhost:8080/swagger-ui.html");
			desktop.browse(uri);
		}
	}
}
