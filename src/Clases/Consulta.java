/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Date;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.ZoneId;
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
    
    private String Descripcion;
    
    
    public Consulta(int ciPaciente, int codMedico, Date fecha){
        this.numeroReserva = UltimoId++;
        this.CiPaciente = ciPaciente;
        this.CodMedico = codMedico;
        this.Fecha = fecha;
        this.estado = "pendiente";
        this.Descripcion = null;
    }
    
    public Consulta(){
        
    }
    
    public boolean sonDelMismoDia(Date fecha1, Date fecha2) {
        // Convierte las fechas a tipos de fecha locales
        LocalDate localDate1 = fecha1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = fecha2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    
        // Compara si están en el mismo día
        return localDate1.isEqual(localDate2);
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
    
     /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the estado to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    @Override
    public int compareTo(Consulta o) {
    // Asumiendo que numeroReserva es un campo de la clase Consulta que almacena el número de reserva
    if (this.numeroReserva < o.getNumeroReserva()) {
        return -1; // Devuelve -1 si este objeto es menor que el objeto pasado como parámetro
    } else if (this.numeroReserva > o.getNumeroReserva()) {
        return 1; // Devuelve 1 si este objeto es mayor que el objeto pasado como parámetro
    } else {
        return 0; // Devuelve 0 si ambos objetos son iguales en términos del número de reserva
    }
}
    
    @Override
    public boolean equals(Object o){
        Consulta c = (Consulta) o;
        return this.getCiPaciente() == c.getCiPaciente() && this.getCodMedico() == c.getCodMedico();
    }
    
}
