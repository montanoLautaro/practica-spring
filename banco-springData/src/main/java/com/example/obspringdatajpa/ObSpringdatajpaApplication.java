package com.example.obspringdatajpa;

import com.example.obspringdatajpa.servicios.PrincipalServicio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class ObSpringdatajpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ObSpringdatajpaApplication.class, args);
		ApplicationContext contextBeans = new ClassPathXmlApplicationContext("beans.xml");
		PrincipalServicio principalServicio = (PrincipalServicio) contextBeans.getBean("principalServicio");
		try{
			principalServicio.inicializarRepositorios(context);
			principalServicio.inicializarBeans(contextBeans);
			principalServicio.menu();
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("No se pudo ejecutar el programa.");
		}
	}

}
