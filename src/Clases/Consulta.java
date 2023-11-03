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
public class Consulta implements Comparable<Consulta> {
    
    private static int UltimoId = 1;
    
    private int numeroReserva;
    
    private int CodMedico;
    
    private int CiPaciente;
    
    private Date Fecha;
    
    private String estado;
    
    
    public Consulta(int ciPaciente, int codMedico, Date fecha){
        this.numeroReserva = UltimoId++;
        this.CiPaciente = ciPaciente;
        this.CodMedico = codMedico;
        this.Fecha = fecha;
        this.estado = "pendiente";
    }
    
    public Consulta(){
        
    }
    /**
     * @return the UltimoId
     */
    public static int getUltimoId() {
        return UltimoId;
    }

    /**
     * @param aUltimoId the UltimoId to set
     */
    public static void setUltimoId(int aUltimoId) {
        UltimoId = aUltimoId;
    }

    /**
     * @return the numeroReserva
     */
    public int getNumeroReserva() {
        return numeroReserva;
    }

    /**
     * @param numeroReserva the numeroReserva to set
     */
    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    /**
     * @return the CodMedico
     */
    public int getCodMedico() {
        return CodMedico;
    }

    /**
     * @param CodMedico the CodMedico to set
     */
    public void setCodMedico(int CodMedico) {
        this.CodMedico = CodMedico;
    }


    /**
     * @return the Fecha
     */
    public Date getFecha() {
        return Fecha;
    }

    /**
     * @param Fecha the Fecha to set
     */
    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the CiPaciente
     */
    public int getCiPaciente() {
        return CiPaciente;
    }

    /**
     * @param CiPaciente the CiPaciente to set
     */
    public void setCiPaciente(int CiPaciente) {
        this.CiPaciente = CiPaciente;
    }

    @Override
    public int compareTo(Consulta o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}