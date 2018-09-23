/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1ma;

import java.util.Date;

/**
 *
 * @author godin
 */
public class usuario {
    private String usuario;
    private String nombre;
    private String apellido;
    private String password;
    private boolean rol;
    private Date fecha_nacimiento;
    private String  correo_alternativo;
    private int telefono;
    private String path_fotografia;
    private boolean status;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCorreo_alternativo() {
        return correo_alternativo;
    }

    public void setCorreo_alternativo(String correo_alternativo) {
        this.correo_alternativo = correo_alternativo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getPath_fotografia() {
        return path_fotografia;
    }

    public void setPath_fotografia(String path_fotografia) {
        this.path_fotografia = path_fotografia;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
  
    
}
