package sistemaAutogestion;

import Clases.*;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
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
        consultaBuscada.setCodMedico(codMedico);
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
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
                
        Date fechaActual = new Date();
        
        //obtiene paciente
        Paciente pacienteBuscado = new Paciente();
        pacienteBuscado.setCI(CIPaciente);
        Nodo<Paciente> nodoPaciente = listaPacientes.obtenerElemento(pacienteBuscado);
        if(nodoPaciente == null){
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        
        //obtiene medico
        Medico medicoBuscado = new Medico();
        medicoBuscado.setCodMedico(codMedico);
        Nodo<Medico> nodoMedico = listaMedicos.obtenerElemento(medicoBuscado);
        
        Medico medico = nodoMedico.getDato();
        ListaSimple<Consulta> consultas = medico.getConsultas();
        Nodo<Consulta> nodoConsulta = consultas.getInicio();
        
        boolean consultaEncontrada = false;
        while(nodoConsulta!=null){
            Consulta consulta = nodoConsulta.getDato();
            if(consulta.getCiPaciente()== CIPaciente && consulta.getEstado().equals("pendiente") && consulta.sonDelMismoDia(consulta.getFecha(), fechaActual)){
                consulta.setEstado("en espera");
                consultaEncontrada = true;
                break;
            }
            nodoConsulta = nodoConsulta.getSiguiente();
        }
        
        if(consultaEncontrada){
            r.resultado = Retorno.Resultado.OK;
        }else{
            r.resultado = Retorno.Resultado.ERROR_2;
        }

        return r;
    }

    @Override
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        
        Date fechaActual = new Date();
        
        //Buscar paciente por CI
        Paciente pacienteBuscado = new Paciente();
        pacienteBuscado.setCI(CIPaciente);
        Nodo<Paciente> nodoPaciente = listaPacientes.obtenerElemento(pacienteBuscado);
        if(nodoPaciente == null){
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        
        //Buscar medico por Codigo
        Medico medicoBuscado = new Medico();
        medicoBuscado.setCodMedico(codMedico);
        Nodo<Medico> nodoMedico = listaMedicos.obtenerElemento(medicoBuscado);
        
        Medico medico = nodoMedico.getDato();
        ListaSimple<Consulta> consultas = medico.getConsultas();
        Nodo<Consulta> nodoConsulta = consultas.getInicio();
        boolean consultaEncontrada = false;
        
        while(nodoConsulta!=null){
            Consulta consulta = nodoConsulta.getDato();
            if(consulta.getCiPaciente()== CIPaciente && consulta.getCodMedico()== codMedico && consulta.sonDelMismoDia(consulta.getFecha(), fechaActual) && consulta.getEstado().equals("en espera")){
                consulta.setEstado("terminada");
                consulta.setDescripcion(detalleDeConsulta);
                medicoBuscado.AgregarConsulta(consulta);
                consultaEncontrada = true;
                break;
            }
            nodoConsulta = nodoConsulta.getSiguiente();
        }
        if(consultaEncontrada){
            r.resultado = Retorno.Resultado.OK;
        }else{
            r.resultado = Retorno.Resultado.ERROR_2;
        }
        return r;
    }

    @Override
    public Retorno cerrarConsulta(int codMedico, Date fechaConsulta) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        
        // Buscar al médico por su código
        Medico medicoBuscado = new Medico();
        medicoBuscado.setCodMedico(codMedico);
        Nodo<Medico> nodoMedico = listaMedicos.obtenerElemento(medicoBuscado);
        if (nodoMedico == null) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        
        medicoBuscado = nodoMedico.getDato();
        ListaSimple<Consulta> consultas = medicoBuscado.getConsultas();
        Nodo<Consulta> nodoConsulta = consultas.getInicio();

        boolean consultaEncontrada = false;

        while (nodoConsulta != null) {
            Consulta consulta = nodoConsulta.getDato();

            if (consulta.getFecha().equals(fechaConsulta) && consulta.getEstado().equals("pendiente")) {
                
                consulta.setEstado("no asistió");
                
                Paciente pacienteBuscado = new Paciente();
                pacienteBuscado.setCI(consulta.getCiPaciente());
                Nodo<Paciente> nodoPaciente = listaPacientes.obtenerElemento(pacienteBuscado);
                Paciente paciente = nodoPaciente.getDato();
                
                consultaEncontrada = true;
                pacienteBuscado.AgregarHistoriaClinica(consulta);
                break;
            }

            nodoConsulta = nodoConsulta.getSiguiente();
        }

        if (consultaEncontrada) {
            r.resultado = Retorno.Resultado.OK;
        } else {
            r.resultado = Retorno.Resultado.ERROR_2;
        }

        return r;
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
        Retorno retorno = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Medico medicoBuscado = new Medico();
        medicoBuscado.setCodMedico(codMédico);
        
        Nodo<Medico> nodoMedico = listaMedicos.obtenerElemento(medicoBuscado);
        
        if(nodoMedico == null){
            retorno.resultado = Retorno.Resultado.ERROR_1;
            return retorno;
        } else{
            Medico medico = nodoMedico.getDato();
            retorno.resultado = Retorno.Resultado.OK;
            listarConsultasRecursivo(medico.getConsultas().getInicio(), retorno);
        }  
        return retorno;
    }
    
    private void imprimirConsulta(Consulta consulta) {
    // Aquí implementas la lógica para imprimir los detalles de la consulta
    System.out.println("Número de Reserva: " + consulta.getNumeroReserva() +
                       "\nCódigo Médico: " + consulta.getCodMedico() +
                       "\nCédula Paciente: " + consulta.getCiPaciente() +
                       "\nFecha: " + consulta.getFecha() +
                       "\nEstado: " + consulta.getEstado());
    }
    
    private void listarConsultasRecursivo(Nodo<Consulta> nodo, Retorno retorno) {
    if (nodo == null) {
        return;
    }
    
    // Suponiendo que tienes un método para imprimir la consulta
    imprimirConsulta(nodo.getDato());
    listarConsultasRecursivo(nodo.getSiguiente(), retorno);
    }
    
    
    @Override
    public Retorno listarPacientesEnEspera(String codMedicoStr, Date fecha) {
        Retorno retorno = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        int codMedico = Integer.parseInt(codMedicoStr); // Asegúrate de manejar NumberFormatException si el código no es un número válido.
    
        // Buscar el médico por código.
        Medico medicoBuscado = new Medico();
        medicoBuscado.setCodMedico(codMedico);
        Nodo<Medico> nodoMedico = listaMedicos.obtenerElemento(medicoBuscado);
    
        if (nodoMedico == null) {
            retorno.resultado = Retorno.Resultado.ERROR_1;
            return retorno;
        }
    
        Medico medico = nodoMedico.getDato();
        ListaSimple<Consulta> consultasDelDia = new ListaSimple<>();
    
        // Filtrar consultas por fecha y estado "en espera".
        ListaSimple<Consulta> consultasMedico = medico.getConsultas();
        Nodo<Consulta> actual = medico.getConsultas().getInicio();
        while (actual != null) {
            Consulta consulta = actual.getDato();
            if (consulta.getFecha()!=null && consulta.getFecha().equals(fecha) && consulta.getEstado().equals("en espera")) {//problema en consulta.getFecha() no llega la fecha
                consultasDelDia.agregarFinal(consulta);
            }
            actual = actual.getSiguiente();
        }
    
        // Verificar si hay consultas en espera para esa fecha.
        if (consultasDelDia.esVacia()) {
            retorno.resultado = Retorno.Resultado.ERROR_1;
            return retorno;
        }
    
        // Ordenar las consultas por número de reserva.
        //consultasDelDia.ordenar(); // Asumiendo que tienes un método para ordenar.
    
        // Imprimir las consultas.
        actual = consultasDelDia.getInicio();
        while (actual != null) {
            Consulta consulta = actual.getDato();
            System.out.println("Reserva: " + consulta.getNumeroReserva() +
                            ", Paciente CI: " + consulta.getCiPaciente() +
                            ", Estado: " + consulta.getEstado());
            actual = actual.getSiguiente();
        }
    
        retorno.resultado = Retorno.Resultado.OK;
        return retorno;
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
    
    public Date convertirLocalDateADate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        return Date.from(localDate.atStartOfDay(zoneId).toInstant());
    }
}
