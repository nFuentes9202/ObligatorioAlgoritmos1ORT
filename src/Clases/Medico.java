/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author nfuen
 */
public class Medico implements Comparable<Medico> {
    
    private String nombre;
    
    private int codMedico;
    
    private int tel;
    
    private int especialidad;
    
    public Medico(String elNombre, int elCodMedico, int elTel, int laEspecialidad){
        this.nombre = elNombre;
        this.codMedico = elCodMedico;
        this.tel = elTel;
        this.especialidad = laEspecialidad;
    }

    public Medico() {
        
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
    
}
