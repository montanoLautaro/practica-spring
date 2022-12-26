/*
 * hasta 10 usuarios
 * controlar q nada llegue vacio
 * nombre apellido direc tel correo
 * usuario
 * contraseña
 * usuario > 18 para abrir cuenta
 * menu para transaccion de usuario 1 a usuario 2
 * tarjeta con unico numero
 */


package com.banco.entidades;

public class Cuenta {
    private int contrasenia;
    private double saldo;
    private String numeroTarjeta;


    public Cuenta() {
        saldo = 0;
    }

    public Cuenta(int contrasenia, String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
        this.contrasenia = contrasenia;
        saldo = 0;
    }

    public Cuenta(int contrasenia, double saldo, String numeroTarjeta) {
        this.contrasenia = contrasenia;
        this.saldo = saldo;
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public int getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(int contrasenia) {
        this.contrasenia = contrasenia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
