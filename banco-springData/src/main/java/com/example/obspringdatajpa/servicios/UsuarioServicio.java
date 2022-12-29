package com.example.obspringdatajpa.servicios;


import com.example.obspringdatajpa.entidades.Usuario;
import com.example.obspringdatajpa.repositorios.CuentaRepository;
import com.example.obspringdatajpa.repositorios.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Scanner;



@Service
public class UsuarioServicio {


    private Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public Usuario crearUsuario(){
        System.out.println("\t\t INGRESO DE DATOS PARA UN NUEVO USUARIO");

        Usuario u = new Usuario();

        System.out.println("Ingrese el nombre del titular: ");
        u.setNombre(ingresarNombre());

        System.out.println("Ingrese el apellido: ");
        u.setApellido(ingresarNombre());

        u.setEdad(ingresarEdad());

        System.out.println("Ingrese la dirección: ");
        u.setDireccion(sc.next());

        System.out.println("Ingrese el número de teléfono: ");
        u.setTelefono(sc.next());

        System.out.println("Ingrese el correo electronico: ");
        u.setCorreo(sc.next());

        u.setCuenta(PrincipalServicio.getCuentaServicio().crearCuenta(PrincipalServicio.getUsuarioRepository()));

        return u;
    }
    public void agregarNuevoUsuario(){
        Usuario nuevoUsuario = crearUsuario();
        if(nuevoUsuario != null){
            if(PrincipalServicio.getUsuarioRepository().count() < 10){
                System.out.println("creando usuario...");
                PrincipalServicio.getUsuarioRepository().save(nuevoUsuario);
                System.out.println("Se ingresaron " + PrincipalServicio.getUsuarioRepository().count() + " de 10 usuarios.");
            }else{
                System.out.println("Se alcanzó el máximo de usuarios permitido.");
            }
        }

    }
    public String ingresarNombre(){
        String nombre;
        boolean validador;
        do{
            nombre = sc.next();
            validador = Validador.validarIngresoSoloLetras(nombre);
        }while(validador);

        return nombre;
    }

    public int ingresarEdad() {
        int edad;
        System.out.println("Ingrese la edad del usuario: ");
        edad = Validador.validarIngresoEnteroPositivo();
        while (edad < 18 || edad > 130) {
            System.out.println("La edad ingresada no es válida.");
            System.out.println("Ingrese la edad del usuario: ");
            edad = Validador.validarIngresoEnteroPositivo();
        }
        return edad;
    }
    public void ingresarCuenta(){
        ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) PrincipalServicio.getUsuarioRepository().findAll();
        String correo;
        boolean resultado = false;

        System.out.println("Ingrese su correo electronico: ");
        correo = sc.next();
        for (Usuario user: listaUsuarios) {
            if(user.getCorreo().equals(correo)){
                if(validarContrasenia(user)){
                    resultado = true;
                    PrincipalServicio.getCuentaServicio().menuCuenta(user);
                }
                break;
            }
        }
        if(resultado == false){
            System.out.println("Usuario no encontrado, volviendo al menú principal...");
        }
    }

    public boolean validarContrasenia(Usuario user){
        boolean resultado = false;
        int contrasenia;
        int intentos = 5;
        do{
            System.out.println("Ingrese su contraseña: ");
            contrasenia = sc.nextInt();
            if(user.getCuenta().getContrasenia() == contrasenia){
                System.out.println("Bienvenido " + user.getNombre() + " " + user.getApellido());
                resultado = true;
            }else{
                System.out.println("Ingreso mal la contraseña, le quedan " + intentos + " intentos.");
            }
            intentos--;
        }while(intentos > 0 && resultado == false);

        if(!resultado){
            System.out.println("Se quedó sin intentos, volviendo al menú principal.");
        }

        return resultado;
    }

}
