/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Date;

/**
 *
 * @author nfuen
 */
public class Paciente implements Comparable<Paciente>{
    
    private String nombre;
    
    private int CI;
    
    private String direccion;
    
    private Date fechaDeseadaUltimaConsulta;
    
    public Paciente(String elNombre, int laCI, String laDireccion){
        this.nombre = elNombre;
        this.CI = laCI;
        this.direccion = laDireccion;
    }

    public Paciente() {
        
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the CI
     */
    public int getCI() {
        return CI;
    }

    /**
     * @param CI the CI to set
     */
    public void setCI(int CI) {
        this.CI = CI;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int compareTo(Paciente o) {
        return this.getNombre().compareTo(o.getNombre());
    }
    @Override
    public boolean equals(Object o){
        Paciente p = (Paciente) o;
        return this.getCI()== p.getCI();
    }
    
    @Override
    public String toString() {
        return this.getNombre();
    }

    /**
     * @return the fechaDeseadaUltimaConsulta
     */
    public Date getFechaDeseadaUltimaConsulta() {
        return fechaDeseadaUltimaConsulta;
    }

    /**
     * @param fechaDeseadaUltimaConsulta the fechaDeseadaUltimaConsulta to set
     */
    public void setFechaDeseadaUltimaConsulta(Date fechaDeseadaUltimaConsulta) {
        this.fechaDeseadaUltimaConsulta = fechaDeseadaUltimaConsulta;
    }
}
