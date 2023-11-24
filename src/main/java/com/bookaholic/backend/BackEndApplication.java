package com.bookaholic.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;


@SpringBootApplication
public class BackEndApplication {

	public String EMAIL_USER = System.getenv("EMAIL_USER");
	public String EMAIL_CODE = System.getenv("EMAIL_CODE");
	public String CLIENT_SECRET = System.getenv("CLIENT_SECRET");
	public String DEBUG = System.getenv("DEBUG");
	public String CLIENT_ID = System.getenv("CLIENT_ID");
	public String SANDBOX = System.getenv("SANDBOX");
	public String JWT_SECRET = System.getenv("JWT_SECRET");
	public String MYSQLUSER = System.getenv("MYSQLUSER");
	public String MYSQL_ROOT_PASSWORD = System.getenv("MYSQL_ROOT_PASSWORD");
	public String MYSQL_URL = System.getenv("MYSQL_URL");


	
	public String PORT = System.getenv("PORT");


	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
		
	}

}
