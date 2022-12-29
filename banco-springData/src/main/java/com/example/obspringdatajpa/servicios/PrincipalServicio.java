package com.example.obspringdatajpa.servicios;


import com.example.obspringdatajpa.entidades.Cuenta;
import com.example.obspringdatajpa.entidades.Usuario;
import com.example.obspringdatajpa.repositorios.CuentaRepository;
import com.example.obspringdatajpa.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component

public class PrincipalServicio {
    private static UsuarioServicio usuarioServicio;
    private static CuentaServicio cuentaServicio;
    private static UsuarioRepository usuarioRepository;
    private static CuentaRepository cuentaRepository;
    private ApplicationContext applicationContext;

    public PrincipalServicio(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static UsuarioServicio getUsuarioServicio() {
        return usuarioServicio;
    }

    public static CuentaServicio getCuentaServicio() {
        return cuentaServicio;
    }

    public static UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public static CuentaRepository getCuentaRepository() {
        return cuentaRepository;
    }

    public void inicializarRepositoriosServicios() throws Exception{
        try{
            usuarioRepository = applicationContext.getBean(UsuarioRepository.class);
            cuentaRepository = applicationContext.getBean(CuentaRepository.class);
            usuarioServicio = applicationContext.getBean(UsuarioServicio.class);
            cuentaServicio = applicationContext.getBean(CuentaServicio.class);
        }catch (Exception e){
            System.out.println("Error al cargar los repositorios");
            throw  e;
        }
    }

    public  void menu(){
        int opcion;
        do{
            System.out.println("\t\t MENÚ PRINCIPAL");
            System.out.println("1. Ingresar un nuevo usuario.");
            System.out.println("2. Ingresar a la cuenta");
            System.out.println("3. Salir");
            System.out.println("Elíja una opción: ");
            opcion = Validador.validarIngresoEnteroPositivo();
            switch (opcion){
                case 1:
                    usuarioServicio.agregarNuevoUsuario();
                    break;
                case 2:
                    System.out.println("Ingresando al menu de usuario...");
                    usuarioServicio.ingresarCuenta();
                    break;
                case 3:
                    System.out.println("SALIENDO DEL PROGRAMA...");
                    break;
                default:
                    System.out.println("La opción ingresada no es válida, intentelo nuevamente");
            }
        }while(opcion != 3);

    }

    public void inicializarUsuarios(){
        usuarioRepository.save(new Usuario("Juan", "Pere", 19, "falsa 123", "1133443311", "correo@prueba.com", new Cuenta(1439,
                130, "1234567891012345")));
        usuarioRepository.save(new Usuario("Logi", "Tech", 33, "lab 3", "1133221144", "qwerty@prueba.com", new Cuenta(1321,
                100, "9876543211012345")));
        usuarioRepository.save(new Usuario("Pan", "Queso", 19, "micro 12", "981231232", "supr@correo.com", new Cuenta(5411,
                300, "123456789763812")));
        usuarioRepository.save(new Usuario("pasta", "Salsa", 40, "pastas 33", "1123123122", "charly@correo.com", new Cuenta(2222,
                1000, "1234567891012345")));
        usuarioRepository.save(new Usuario("Dobby", "Free", 44, "lejos 1", "13123123123", "cerca@prueba.com", new Cuenta(5123,
                10, "1234561234562345")));
    }
}
