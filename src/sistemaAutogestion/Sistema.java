package sistemaAutogestion;

import Clases.*;
import java.util.Date;
import tads.*;

public class Sistema implements IObligatorio {

    
    
    private int cantMaxPacientesPorMedico;
    
    private ListaSimple listaMedicos;
    private ListaSimple listaPacientes;
    
    @Override
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        
        if(maxPacientesporMedico <= 0 || maxPacientesporMedico > 15){
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        else{
            listaMedicos = new ListaSimple();
            listaPacientes = new ListaSimple();
            cantMaxPacientesPorMedico = maxPacientesporMedico;
            r.resultado = Retorno.Resultado.OK;
        }
        return r;
    }

    @Override
    public Retorno registrarMedico(String nombre, int codMedico, int tel, int especialidad) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Medico m = new Medico(nombre, codMedico,tel, especialidad);
        if(listaMedicos.existeElemento(m)){
            r.resultado =Retorno.Resultado.ERROR_1;
            return r;
        }
        if(especialidad < 1 || especialidad > 20){
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }
        listaMedicos.agregarOrd(m);
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
    public Retorno eliminarMedico(int codMedico) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Medico medicoEliminar = new Medico();
        medicoEliminar.setCodMedico(codMedico);
        if(listaMedicos.existeElemento(medicoEliminar)){
            listaMedicos.eliminarElemento(medicoEliminar);
            r.resultado = Retorno.Resultado.OK;
            return r;
        }
        else{
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
    }

    @Override
    public Retorno agregarPaciente(String nombre, int CI, String direccion) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Paciente p = new Paciente(nombre, CI, direccion);
        
        if(listaPacientes.existeElemento(p)){
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        listaPacientes.agregarFinal(p);
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
    public Retorno eliminarPaciente(int CI) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Paciente aEliminar = new Paciente();
        aEliminar.setCI(CI);
        if(listaPacientes.existeElemento(aEliminar)){
            listaPacientes.eliminarElemento(aEliminar);
            r.resultado = Retorno.Resultado.OK;
            return r;
        }
        else{
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
    }

    @Override
    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno cancelarReserva(int codMedico, int ciPaciente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno anunciaLlegada(int codMedico, int CIPaciente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno cerrarConsulta(String codMédico, Date fechaConsulta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno listarMédicos() {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        listaMedicos.mostrar();
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
    public Retorno listarPacientes() {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        listaPacientes.mostrar();
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
    public Retorno listarConsultas(int codMédico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno listarPacientesEnEspera(String codMédico, Date fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno consultasPendientesPaciente(int CIPaciente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno historiaClínicaPaciente(int ci) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    

}
