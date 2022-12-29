package com.example.obspringdatajpa.servicios;




import com.example.obspringdatajpa.entidades.Cuenta;
import com.example.obspringdatajpa.entidades.Usuario;
import com.example.obspringdatajpa.repositorios.CuentaRepository;
import com.example.obspringdatajpa.repositorios.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
@Service
public class CuentaServicio {


    public void menuCuenta(Usuario user){
        int opcion;
        Cuenta aux = buscarCuentaPorUsuario(user);
        System.out.println("saldo cuenta aux:" + aux.getSaldo());
        do{
            System.out.println("\t\t MENÚ DE USUARIOS");
            System.out.println("1. Ingresar saldo.");
            System.out.println("2. Consultar Saldo.");
            System.out.println("3. Enviar dinero.");
            System.out.println("4. Salir");
            System.out.println("Elíja una opción: ");
            opcion = Validador.validarIngresoEnteroPositivo();
            switch (opcion){
                case 1:
                    ingresarDinero(buscarCuentaPorUsuario(user));
                    break;
                case 2:
                    mostrarDatosCuenta(buscarCuentaPorUsuario(user));
                    break;
                case 3:
                    enviarDinero(buscarCuentaPorUsuario(user));
                    break;
                case 4:
                    System.out.println("VOLVIENDO AL MENÚ PRINCIPAL...");
                    break;
                default:
                    System.out.println("La opción ingresada no es válida, intentelo nuevamente");
            }
        }while(opcion != 4);
    }
    public Cuenta crearCuenta(UsuarioRepository usuarios){
        Cuenta nuevaCuenta = new Cuenta();
        System.out.println("\t\t MENÚ DE CREACIÓN DE CUENTA");
        nuevaCuenta.setContrasenia(crearContrasenia());
        nuevaCuenta.setNumeroTarjeta(otorgarNumTarjeta(usuarios));
        return nuevaCuenta;
    }

    public int crearContrasenia(){
        int contrasenia;
        boolean resultado = true;

        do{
            System.out.println("Ingrese una nueva contraseña númerica para su cuenta, de 4 digitos: ");
            contrasenia = Validador.validarIngresoEnteroPositivo();
            if(Integer.toString(contrasenia).length() == 4){
                resultado = false;
            }else{
                System.out.println("La contraseña debe tener 4 digitos númericos, intentelo nuevamente.");
            }
        }while(resultado);


        return contrasenia;
    }

    public String crearNumeroTarjeta(){
        int numeros;
        Random numRandom = new Random();
        String resultado = "";
        String aux;

        for(int i=0; i<16; i++){

            numeros = numRandom.nextInt(9);
            //3413123123123123
            aux = String.valueOf(numeros);
            resultado = resultado + aux;
        }
        return resultado;
    }

    public String otorgarNumTarjeta(UsuarioRepository usuarios){
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        listaUsuarios.addAll(usuarios.findAll());
        String numeroNuevo;
        boolean validador;
        do{
            numeroNuevo = crearNumeroTarjeta();
            validador = false;
            for (Usuario user: listaUsuarios) {
                if (user.getCuenta().getNumeroTarjeta().equals(numeroNuevo)){
                    validador = true;
                    break;
                }
            }
        }while(validador);
        return numeroNuevo;
    }

    public void ingresarDinero(Cuenta cuenta){
        double dinero;
        System.out.println("-- INGRESO DE DINERO");
        System.out.println("Ingrese la cantidad de dinero que va a ingersar a su cuenta: ");
        dinero = Validador.validarIngresoDoublePositivo();
        dinero = dinero + cuenta.getSaldo();
        cuenta.setSaldo(dinero);
        PrincipalServicio.getCuentaRepository().save(cuenta);
        System.out.println("El ingreso se realizo con éxito.");
    }

    public void mostrarDatosCuenta(Cuenta cuenta){
        System.out.println("Tarjeta Nro " + cuenta.getNumeroTarjeta());
        System.out.println("Dinero disponible: $" + cuenta.getSaldo());
        System.out.println("");
    }

    public void enviarDinero(Cuenta cuentaBase){
        System.out.println("-- ENVIAR DINERO");
        String nombreUsuario = validarNombreUsuario();
        Usuario aux = encontrarUsuario(nombreUsuario);
        if(aux != null){
            realizarTransferencia(cuentaBase, buscarCuentaPorUsuario(aux));
            System.out.println("Operación realizada con exito");
        }else{
            System.out.println("El usuario que ingresó, no existe.");
        }
        System.out.println("Volviendo al menú de usuario...");
    }

    public Usuario encontrarUsuario(String nombre){
        ArrayList<Usuario>listaUsuarios = (ArrayList<Usuario>) PrincipalServicio.getUsuarioRepository().findAll();
        for (Usuario aux: listaUsuarios) {
            if(aux.getNombre().equalsIgnoreCase(nombre)){
                return  aux;
            }
        }
        return null;
    }

    public String validarNombreUsuario(){
        String nombre;
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        boolean validador;
        System.out.println("Ingrese el nombre del usuario al cual le quiere enviar dinero: ");
        do{
            nombre = sc.next();
            validador = Validador.validarIngresoSoloLetras(nombre);
        }while(validador);
        return nombre;
    }

    public void realizarTransferencia(Cuenta cuentaBase, Cuenta cuentaDestino ){
        boolean resultado = true;
        System.out.println("Ingrese la cantidad de dinero que quiere enviar: ");
        double dinero;
        do{
            dinero= Validador.validarIngresoDoublePositivo();
            if(dinero <= cuentaBase.getSaldo()){
                System.out.println("Enviando dinero a la cuenta destino...");
                restarSaldo(cuentaBase, dinero);
                sumarSaldo(cuentaDestino, dinero);
                resultado = false;
            }else {
                System.out.println("El monto ingresado es superior al monto disponible, ingrese otro monto:");
            }
        }while(resultado);
    }
    public void restarSaldo(Cuenta cuenta, double monto){
        double nuevoSaldo = cuenta.getSaldo() - monto;
        cuenta.setSaldo(nuevoSaldo);
        PrincipalServicio.getCuentaRepository().save(cuenta);
    }
    public void sumarSaldo(Cuenta cuenta, double monto){
        double nuevoSaldo = cuenta.getSaldo() + monto;
        cuenta.setSaldo(nuevoSaldo);
        PrincipalServicio.getCuentaRepository().save(cuenta);
    }
    public Cuenta buscarCuentaPorUsuario(Usuario user){
        if(user != null){
            return PrincipalServicio.getUsuarioRepository().findById(user.getId()).get().getCuenta();
        }else{
            System.out.println("Error al buscar la cuenta del usuario");
            return null;
        }
    }

}
