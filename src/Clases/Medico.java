/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Date;
import tads.Cola;
import tads.ListaSimple;
import tads.Nodo;

/**
 *
 * @author nfuen
 */
public class Medico implements Comparable<Medico> {
    
    private String nombre;
    
    private int codMedico;
    
    private int tel;
    
    private int especialidad;
    
    private ListaSimple<Consulta> consultas;
    
    private Cola<Paciente> colaDeEspera;
    
    private ListaSimple<Date> diasDisponiblesParaConsultas;
    
    public Medico(String elNombre, int elCodMedico, int elTel, int laEspecialidad){
        this.nombre = elNombre;
        this.codMedico = elCodMedico;
        this.tel = elTel;
        this.especialidad = laEspecialidad;
        this.consultas = new ListaSimple<>();
        this.colaDeEspera = new Cola<>();
        this.diasDisponiblesParaConsultas = new ListaSimple<>();
    }

    public Medico() {
        this.consultas = new ListaSimple<>();
        this.colaDeEspera = new Cola<>();
        this.diasDisponiblesParaConsultas = new ListaSimple<>();
    }
    
    
    public void AgregarConsulta(Consulta consulta){
        this.getConsultas().agregarOrd(consulta);
    }
    
    public boolean tieneReservaConPaciente(int ciPaciente) {
        Nodo<Consulta> actual = consultas.getInicio();
        while (actual != null) {
            if (actual.getDato().getCiPaciente() == ciPaciente) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
    
    public int contarConsultasPorFecha(Date fecha) {
    int contador = 0;
    Nodo<Consulta> actual = this.consultas.getInicio();
    while (actual != null) {
        if (actual.getDato().getFecha().equals(fecha)) {
            contador++;
        }
        actual = actual.getSiguiente();
    }
    return contador;
    }
    
    public boolean agregarDiaDeConsulta(Date fecha) {
        // Verificar si la fecha ya está en la lista
        if (!this.diasDisponiblesParaConsultas.existeElemento(fecha)) {
            this.getDiasDisponiblesParaConsultas().agregarFinal(fecha);
            return true;
        }
        return false;
    }
    
    public boolean existeDiaDeConsulta(Date fecha) {
        return this.getDiasDisponiblesParaConsultas().existeElemento(fecha);
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
     * @return the codMedico
     */
    public int getCodMedico() {
        return codMedico;
    }

    /**
     * @param codMedico the codMedico to set
     */
    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
    }

    /**
     * @return the tel
     */
    public int getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(int tel) {
        this.tel = tel;
    }

    /**
     * @return the especialidad
     */
    public int getEspecialidad() {
        return especialidad;
    }

    /**
     * @param especialidad the especialidad to set
     */
    public void setEspecialidad(int especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public int compareTo(Medico m) {
        
        return this.getNombre().compareTo(m.getNombre());
    }
    
    @Override
    public boolean equals(Object o){
        Medico m = (Medico) o;
        return this.getCodMedico() == m.getCodMedico();
    }
    
    @Override
    public String toString() {
        return this.getNombre();
    }

    /**
     * @return the consultas
     */
    public ListaSimple<Consulta> getConsultas() {
        return consultas;
    }

    /**
     * @param consultas the consultas to set
     */
    public void setConsultas(ListaSimple<Consulta> consultas) {
        this.consultas = consultas;
    }

    /**
     * @return the colaDeEspera
     */
    public Cola<Paciente> getColaDeEspera() {
        return colaDeEspera;
    }

    /**
     * @param colaDeEspera the colaDeEspera to set
     */
    public void setColaDeEspera(Cola<Paciente> colaDeEspera) {
        this.colaDeEspera = colaDeEspera;
    }

    /**
     * @return the diasDisponiblesParaConsultas
     */
    public ListaSimple<Date> getDiasDisponiblesParaConsultas() {
        return diasDisponiblesParaConsultas;
    }

    /**
     * @param diasDisponiblesParaConsultas the diasDisponiblesParaConsultas to set
     */
    public void setDiasDisponiblesParaConsultas(ListaSimple<Date> diasDisponiblesParaConsultas) {
        this.diasDisponiblesParaConsultas = diasDisponiblesParaConsultas;
    }
    
}
