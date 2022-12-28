package com.example.obspringdatajpa.servicios;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Validador {


    public static int validarIngresoEnteroPositivo(){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        int num = 0;
        boolean validador = true;
        do{

            try{
                num = sc.nextInt();
                if(num > 0){
                    validador = false;
                }else{
                    System.out.println("El número ingresado no es válido, no se permite el ingreso de números negativos.");
                }
            }catch(InputMismatchException a){
                System.out.println(a.getMessage());
                System.out.println("Solo puede ingresar números enteros, intentelo nuevamente.");
                sc.nextLine();
            }catch(Exception b){
                System.out.println(b.getMessage());
                System.out.println("Error de sistema, intentelo nuevamente.");
                sc.nextLine();
            }
        }while(validador);
        return num;
    }

    public static double validarIngresoDoublePositivo(){
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        double num = 0;
        boolean validador = true;
        do{

            try{
                num = sc.nextDouble();
                if(num > 0){
                    validador = false;
                }else{
                    System.out.println("El número ingresado no es válido, no se permite el ingreso de números negativos.");
                }
            }catch(InputMismatchException a){
                System.out.println(a.getMessage());
                System.out.println("Solo puede ingresar números enteros, intentelo nuevamente.");
                sc.nextLine();
            }catch(Exception b){
                System.out.println(b.getMessage());
                System.out.println("Error de sistema, intentelo nuevamente.");
                sc.nextLine();
            }
        }while(validador);
        return num;
    }

    public static boolean validarIngresoSoloLetras(String cadena){
        boolean resultado = true;
        int contador = 0;
        int valorASCII;
        char caracter;

        cadena = cadena.toLowerCase();

        if(cadena.length() > 0){
            for (int i=0; i<cadena.length(); i++){
                caracter = cadena.charAt(i);
                valorASCII = caracter;
                if(valorASCII < 97 || valorASCII > 122 ){
                    contador++;
                }
            }
            if(contador == 0) {
                resultado = false;
            }else{
                System.out.println("Solo puede ingresar letras, intentelo nuevamente.");
            }
        }else{
            System.out.println("No ingreso ningun caracter, intentelo nuevamente.");
        }
        return resultado;
    }
}
