package com.sathiya.jasperreport;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class JasperreportApplication {

	public static void main(String[] args) {
		SpringApplication.run(JasperreportApplication.class, args);
		Path root = FileSystems.getDefault().getPath("").toAbsolutePath();
	}

}
