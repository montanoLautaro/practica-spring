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

public class Usuario {
    private String nombre;
    private String apellido;
    private int edad;
    private String direccion;
    private String telefono;
    private String correo;

    private Cuenta cuenta;

    public Usuario() {
    }


    
    public Usuario(String nombre, String apellido, int edad, String direccion, String telefono, String correo, Cuenta cuenta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.cuenta = cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return   "|" + nombre + "\t\t\t\t" + "\t\t\t" + apellido + "\t\t\t" + edad +
                "\t\t\t" + direccion + "\t\t\t\t\t\t\t\t" + telefono + "\t\t\t\t" + correo + "\t\t" ;
    }
}
