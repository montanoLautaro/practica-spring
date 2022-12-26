package com.banco;

import com.banco.servicios.PrincipalServicio;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext( "beans.xml");
        PrincipalServicio principalSv = (PrincipalServicio) context.getBean("principalServicio");
        principalSv.menu();
    }
}
