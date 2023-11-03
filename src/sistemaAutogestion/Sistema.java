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
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        
        //Buscamos al médico
        Medico medicoBusqueda = new Medico();
        medicoBusqueda.setCodMedico(codMedico);
        
        Nodo<Medico> nodoBuscado = listaMedicos.obtenerElemento(medicoBusqueda);
        
        if(nodoBuscado == null){
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }
        medicoBusqueda = nodoBuscado.getDato();
        
        if(!medicoBusqueda.existeDiaDeConsulta(fecha)){
            r.resultado = Retorno.Resultado.ERROR_4;
            return r;
        }
        
        
        Paciente aBuscar = new Paciente();
        
        aBuscar.setCI(ciPaciente);
        
        Nodo<Paciente> nodoPacienteBuscado = listaPacientes.obtenerElemento(aBuscar);
        
        if(nodoPacienteBuscado == null){
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }     
        if(medicoBusqueda.tieneReservaConPaciente(ciPaciente)){
            r.resultado = Retorno.Resultado.ERROR_3;
            return r;
        }

        aBuscar = nodoPacienteBuscado.getDato();
        
        int cantidadAtendidos = medicoBusqueda.contarConsultasPorFecha(fecha);
        
        if(cantidadAtendidos < cantMaxPacientesPorMedico){
             Consulta nuevaConsulta = new Consulta(ciPaciente, codMedico, fecha);
             medicoBusqueda.AgregarConsulta(nuevaConsulta);
        } else{
            aBuscar.setFechaDeseadaUltimaConsulta(fecha);
            medicoBusqueda.getColaDeEspera().encolar(aBuscar);
        }
        
        r.resultado = Retorno.Resultado.OK;
        
        return r;
    }

    @Override
    public Retorno cancelarReserva(int codMedico, int ciPaciente) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        
        Medico medicoBuscado = new Medico();
        medicoBuscado.setCodMedico(codMedico);
        
        Nodo<Medico> nodoMedico = listaMedicos.obtenerElemento(medicoBuscado);
        
        if(nodoMedico == null){
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }
        
        medicoBuscado = nodoMedico.getDato();
        
        
        Paciente pacienteBuscado = new Paciente();
        pacienteBuscado.setCI(ciPaciente);
        Nodo<Paciente> nodoPaciente = listaPacientes.obtenerElemento(pacienteBuscado);
        if(nodoPaciente == null){
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        
        
        Consulta consultaBuscada = new Consulta();
        consultaBuscada.setCiPaciente(ciPaciente);
        Nodo<Consulta> nodoConsulta = nodoMedico.getDato().getConsultas().obtenerElemento(consultaBuscada);
        
        if(nodoConsulta == null || nodoConsulta.getDato().getEstado().equals("cerrada")){
            r.resultado = Retorno.Resultado.ERROR_3;
            return r;
        }
        consultaBuscada = nodoConsulta.getDato();
        
        if (!consultaBuscada.getEstado().equals("pendiente")) {
            r.resultado = Retorno.Resultado.ERROR_4;
            return r;
        }
        
        medicoBuscado.getConsultas().eliminarElemento(consultaBuscada);
        r.resultado = Retorno.Resultado.OK;
        
        if(!medicoBuscado.getConsultas().esVacia()){
            Paciente pacienteEnEspera = medicoBuscado.getColaDeEspera().desencolar();
            
            Consulta nuevaConsulta = new Consulta(codMedico, pacienteEnEspera.getCI(), pacienteEnEspera.getFechaDeseadaUltimaConsulta());
            medicoBuscado.getConsultas().agregarOrd(nuevaConsulta);
        }
        return r;
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

    @Override
    public Retorno registrarDiaDeConsulta(int codMedico, Date fecha) {
    Retorno retorno = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    Medico medicoBuscado = new Medico();
    medicoBuscado.setCodMedico(codMedico);
    Nodo<Medico> nodoMedico = listaMedicos.obtenerElemento(medicoBuscado);
     
    if (nodoMedico == null) {
        retorno.resultado = Retorno.Resultado.ERROR_1;
    } else if (nodoMedico.getDato().existeDiaDeConsulta(fecha)) {
        retorno.resultado = Retorno.Resultado.ERROR_2;
    } else {
        boolean agregado = nodoMedico.getDato().agregarDiaDeConsulta(fecha);
        if (agregado) {
            retorno.resultado = Retorno.Resultado.OK;
        }
    }

    return retorno;
    }

   

    

}
