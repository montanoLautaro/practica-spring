package com.banco.servicios;

import com.banco.entidades.Cuenta;
import com.banco.entidades.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
@Component
public class PrincipalServicio {
    private UsuarioServicio usuarioSv;
    private CuentaServicio cuentaSv;
    private ArrayList<Usuario> usuarios;

    public PrincipalServicio( ApplicationContext context) {
        usuarioSv = (UsuarioServicio) context.getBean("usuarioServicio");
        cuentaSv = (CuentaServicio) context.getBean("cuentaServicio");
        usuarios = new ArrayList<>();
    }

    public  void menu(){
        int opcion;
        inicializarUsuarios();

        do{
            System.out.println("\t\t MENÚ PRINCIPAL");
            System.out.println("1. Ingresar un nuevo usuario.");
            System.out.println("2. Ingresar a la cuenta");
            System.out.println("3. Salir");
            System.out.println("Elíja una opción: ");
            opcion = Validador.validarIngresoEnteroPositivo();
            switch (opcion){
                case 1:
                    usuarioSv.agregarNuevoUsuario(usuarios, cuentaSv);
                    break;
                case 2:
                    System.out.println("Ingresando al menu de usuario...");
                    usuarioSv.ingresarCuenta(usuarios, cuentaSv);
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
        usuarios.add(new Usuario("Juan", "Pere", 19, "falsa 123", "1133443311", "correo@prueba.com", new Cuenta(1439,
                130, "1234567891012345")));
        usuarios.add(new Usuario("Logi", "Tech", 33, "lab 3", "1133221144", "qwerty@prueba.com", new Cuenta(1321,
                100, "9876543211012345")));
        usuarios.add(new Usuario("Pan", "Queso", 19, "micro 12", "981231232", "supr@correo.com", new Cuenta(5411,
                300, "123456789763812")));
        usuarios.add(new Usuario("pasta", "Salsa", 40, "pastas 33", "1123123122", "charly@correo.com", new Cuenta(2222,
                1000, "1234567891012345")));
        usuarios.add(new Usuario("Dobby", "Free", 44, "lejos 1", "13123123123", "cerca@prueba.com", new Cuenta(5123,
                10, "1234561234562345")));
    }
}
