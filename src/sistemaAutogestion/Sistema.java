package sistemaAutogestion;

import Clases.*;
import java.util.Date;
import java.time.LocalDate;
//import java.time.Month;
import java.time.ZoneId;
//import java.util.Map;
import java.util.HashMap;
//import java.util.Calendar;
import java.time.YearMonth;
//import java.util.ArrayList;
//import java.util.List;
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

    // Verificar si el médico existe
    if (!listaMedicos.existeElemento(medicoEliminar)) {
        r.resultado = Retorno.Resultado.ERROR_1;
        return r;
    }

    // Obtener el médico de la lista para verificar sus consultas
    Nodo<Medico> nodoMedico = listaMedicos.obtenerElemento(medicoEliminar);
    Medico medico = nodoMedico.getDato();

    // Verificar si el médico tiene consultas agendadas
    if (!medico.getConsultas().esVacia() || !medico.getColaDeEspera().isEmpty()) {
        r.resultado = Retorno.Resultado.ERROR_2;
        return r;
    }

    // Proceder a eliminar el médico
    listaMedicos.eliminarElemento(medicoEliminar);
    r.resultado = Retorno.Resultado.OK;
    return r;
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
    Paciente pacienteAEliminar = new Paciente();
    pacienteAEliminar.setCI(CI);

    // Verificar si el paciente existe
    if (!listaPacientes.existeElemento(pacienteAEliminar)) {
        r.resultado = Retorno.Resultado.ERROR_1;
        return r;
    }

    // Obtener el paciente de la lista para verificar sus consultas
    Nodo<Paciente> nodoPaciente = listaPacientes.obtenerElemento(pacienteAEliminar);
    Paciente paciente = nodoPaciente.getDato();

    // Verificar si el paciente ha agendado alguna vez una consulta
    if (paciente.haAgendadoConsulta()) { // Asumiendo que existe el método haAgendadoConsulta()
        r.resultado = Retorno.Resultado.ERROR_2;
        return r;
    }

    // Proceder a eliminar el paciente
    listaPacientes.eliminarElemento(pacienteAEliminar);
    r.resultado = Retorno.Resultado.OK;
    return r;
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
        aBuscar.setContadorConsultas(aBuscar.getContadorConsultas() + 1);
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

        // Buscar paciente por CI
        Paciente pacienteBuscado = new Paciente();
        pacienteBuscado.setCI(CIPaciente);
        Nodo<Paciente> nodoPaciente = listaPacientes.obtenerElemento(pacienteBuscado);
        if (nodoPaciente == null) {
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }

        // Buscar médico por Código
        Medico medicoBuscado = new Medico();
        medicoBuscado.setCodMedico(codMedico);
        Nodo<Medico> nodoMedico = listaMedicos.obtenerElemento(medicoBuscado);

        if (nodoMedico == null) {
            r.resultado = Retorno.Resultado.ERROR_2;
            return r;
        }

        Medico medico = nodoMedico.getDato();
        ListaSimple<Consulta> consultas = medico.getConsultas();
        Nodo<Consulta> nodoConsulta = consultas.getInicio();
        boolean consultaEncontrada = false;

        while (nodoConsulta != null) {
            Consulta consulta = nodoConsulta.getDato();
            if (consulta.getCiPaciente() == CIPaciente && consulta.getCodMedico() == codMedico
                    && consulta.sonDelMismoDia(consulta.getFecha(), fechaActual)
                    && consulta.getEstado().equals("en espera")) {

                consulta.setEstado("terminada");
                consulta.setDescripcion(detalleDeConsulta);

                // Agregar la consulta al historial clínico del paciente
                Paciente paciente = nodoPaciente.getDato();
                paciente.AgregarHistoriaClinica(consulta);

                consultaEncontrada = true;
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
        Nodo<Consulta> nodoConsulta = consultas.getInicio();//hasta aca llega la fecha de la consulta por get y la pasada por el metodo en main

        boolean consultaEncontrada = false;

        while (nodoConsulta != null) {
            Consulta consulta = nodoConsulta.getDato();//aca la fecha se pierde

            if (consulta.sonDelMismoDia(fechaConsulta, consulta.getFecha()) && consulta.getEstado().equals("pendiente")) {
                
                consulta.setEstado("no asistió");
                
                Paciente pacienteBuscado = new Paciente();
                pacienteBuscado.setCI(consulta.getCiPaciente());
                
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
    System.out.println("\nNúmero de Reserva: " + consulta.getNumeroReserva() +
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
        int codMedico = Integer.parseInt(codMedicoStr); 
    
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
        Nodo<Consulta> actual = medico.getConsultas().getInicio();
        while (actual != null) {
            Consulta consulta = actual.getDato();
            if (consulta.getFecha()!=null && consulta.getFecha().equals(fecha) && consulta.getEstado().equals("en espera")) {
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
    Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    
    Paciente pacienteBuscado = new Paciente();
    pacienteBuscado.setCI(CIPaciente);
    Nodo<Paciente> nodoPaciente = listaPacientes.obtenerElemento(pacienteBuscado);

    // Verificar si el paciente existe
    if (nodoPaciente == null) {
        r.resultado = Retorno.Resultado.ERROR_1;
        return r;
    }

    r.resultado = Retorno.Resultado.OK;
    System.out.println("Consultas pendientes del paciente CI: " + CIPaciente);
    listarConsultasPendientesDePacienteEnMedicos(listaMedicos.getInicio(), CIPaciente);

    return r;
    }


    private void listarConsultasPendientesDePacienteEnMedicos(Nodo<Medico> nodoMedico, int CIPaciente) {
    if (nodoMedico == null) {
        return; // Caso base: No hay más médicos en la lista.
    }

    Medico medico = nodoMedico.getDato();
    listarConsultasPendientesDePacienteEnConsultas(medico.getConsultas().getInicio(), CIPaciente);

    // Llamada recursiva para el siguiente médico en la lista.
    listarConsultasPendientesDePacienteEnMedicos(nodoMedico.getSiguiente(), CIPaciente);
}

    private void listarConsultasPendientesDePacienteEnConsultas(Nodo<Consulta> nodoConsulta, int CIPaciente) {
    if (nodoConsulta == null) {
        return; // Caso base: No hay más consultas en la lista.
    }

    Consulta consulta = nodoConsulta.getDato();
    if (consulta.getCiPaciente() == CIPaciente && consulta.getEstado().equals("pendiente")) {
        imprimirConsulta(consulta);
    }

    // Llamada recursiva para la siguiente consulta en la lista.
    listarConsultasPendientesDePacienteEnConsultas(nodoConsulta.getSiguiente(), CIPaciente);
}
    
    @Override
    public Retorno historiaClínicaPaciente(int ci) {
        Retorno retorno = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
    Paciente pacienteBuscado = new Paciente();
    pacienteBuscado.setCI(ci);
    Nodo<Paciente> nodoPaciente = listaPacientes.obtenerElemento(pacienteBuscado);

    if (nodoPaciente == null) {
        retorno.resultado = Retorno.Resultado.ERROR_1;
    } else {
        retorno.resultado = Retorno.Resultado.OK;
        // Suponiendo que el paciente tiene una lista de consultas ordenadas por fecha de manera descendente.
        listarHistoriaClinicaRecursiva(nodoPaciente.getDato().getHistoriaClinica().getInicio(), retorno);
    }
    return retorno;
    }

    private void listarHistoriaClinicaRecursiva(Nodo<Consulta> nodo, Retorno retorno) {
    if (nodo == null) {
        // Caso base: llegamos al final de la lista.
        return;
    }
    // Procesar la consulta actual.
    imprimirConsulta(nodo.getDato());
    // Llamada recursiva al siguiente nodo en la lista.
    listarHistoriaClinicaRecursiva(nodo.getSiguiente(), retorno);
}
    
    @Override
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año) {
        
        Retorno retorno = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        
        if(mes<=0 || mes>12 || año<2020 || año>2023){
            retorno.resultado = retorno.resultado.ERROR_1;
        }
        
        int[]especialidades = obtenerEspecialidadesUnicas();
        int diasEnMes = obtenerDiasEnMes(mes,año);
        ListaSimple<Consulta> consultas = obtenerConsultasUnicas();
        
        //Crear matriz para contar las consultar
        //Filas son los dias del mes y las columnas las especialidades
        int[][] contadorConsultas = new int[diasEnMes][especialidades.length];
        
        //Mapear cada especialidad a un indice de columna
        HashMap<Integer, Integer> mapaEspecialidades = new HashMap<>();
        for(int i = 0; i<especialidades.length;i++){
            mapaEspecialidades.put(especialidades[i], i);
        }
        
        //Vamos a convertir los datos para consultas
        mes = mes - 1;
        año = año -1900;
        //Contar las consultas cerradas x especialidad y x dia del mes
        for(Nodo<Consulta> nodoConsulta = consultas.getInicio(); nodoConsulta !=null; nodoConsulta = nodoConsulta.getSiguiente()){
            Consulta consulta = nodoConsulta.getDato();
            if(consulta.getFecha().getMonth()== mes && consulta.getFecha().getYear()== año && consulta.getEstado().equals("terminada")){
                Medico medicoBuscado = new Medico();
                medicoBuscado.setCodMedico(consulta.getCodMedico());
                Nodo<Medico> nodoMedico = listaMedicos.obtenerElemento(medicoBuscado);
                if(nodoMedico!=null){
                    int especialidadMedico = nodoMedico.getDato().getEspecialidad();//busque la especialidad x el nodo en vez d x medicoBuscado
                    int dia = consulta.getFecha().getDay()-1;
                    Integer especialidadIndex = mapaEspecialidades.get(especialidadMedico);//tenia q poner especialidadMedico angora sim
                    if(especialidadIndex != null){
                        contadorConsultas[dia][especialidadIndex]++;
                    }
                }

            }
        }
        
        // Convertir la matriz a una cadena de texto para mostrarla
        StringBuilder matrizComoString = new StringBuilder();
        
        //Encabezados de columnas (especialidades)
        StringBuilder encabezadosColumnas = new StringBuilder("     ");
        for(int especialidad: especialidades){
            encabezadosColumnas.append(String.format("ESP %d    ", especialidad));
        }
        encabezadosColumnas.append("\n");
        
        //Agregar encabezados de columnas a la cadena matrizComoString
        matrizComoString.append(encabezadosColumnas);
        

        
        //Rellenar matriz
        for(int i = 0; i < contadorConsultas.length; i++) {
            matrizComoString.append(String.format("%2d  ", i + 1));
            for(int j = 0; j < contadorConsultas[i].length; j++) {
                matrizComoString.append(String.format("%4d     ", contadorConsultas[i][j]));
            }
            matrizComoString.append("\n"); // Nueva línea para separar las filas de la matriz
        }
        
        retorno.valorString = matrizComoString.toString();
        retorno.resultado = retorno.resultado.OK;
        return retorno;
        
    }
    
    private int[]obtenerEspecialidadesUnicas(){
        Nodo<Medico> nodoMedicoActual = listaMedicos.getInicio();//obtener primer nodo de lista de medicos
        int[] especialidadesTemporales = new int[listaMedicos.cantidadElementos()];//setear como cantidad de especialidades la cantidad de medicos
        int cantEspecialidades = 0;
        
        while(nodoMedicoActual!=null){
            int especialidadActual = nodoMedicoActual.getDato().getEspecialidad();
            boolean yaExiste = false;
            
            for(int i = 0; i<cantEspecialidades; i++){
                if(especialidadesTemporales[i] == especialidadActual){
                    yaExiste = true;
                    break;
                }
            }
            
            if(!yaExiste){
                if(cantEspecialidades == especialidadesTemporales.length){
                    int[] nuevoArreglo = new int[cantEspecialidades + 1];
                    System.arraycopy(especialidadesTemporales, 0, nuevoArreglo, 0, especialidadesTemporales.length);
                    especialidadesTemporales = nuevoArreglo;
                }
                especialidadesTemporales[cantEspecialidades] = especialidadActual;
                cantEspecialidades++;
            }
            nodoMedicoActual = nodoMedicoActual.getSiguiente();
           
        }
                    //Copiar las especialidades recogidas al array final con tamaño exacto
            int[] especialidadesUnicas = new int[cantEspecialidades];
            System.arraycopy(especialidadesTemporales,0, especialidadesUnicas,0,cantEspecialidades);
            return especialidadesUnicas;
    }
    
    private ListaSimple<Consulta> obtenerConsultasUnicas(){
        ListaSimple<Consulta> consultas = new ListaSimple<>();
        
        Nodo<Medico> nodoMedico = listaMedicos.getInicio();
        
        while(nodoMedico != null){
            ListaSimple<Consulta> listaConsultasMedico = nodoMedico.getDato().getConsultas();
            Nodo<Consulta> nodoConsulta = listaConsultasMedico.getInicio();
            while(nodoConsulta!=null){
                if(!contieneConsulta(consultas, nodoConsulta.getDato())){
                    consultas.agregarFinal(nodoConsulta.getDato());
                }
 
                nodoConsulta = nodoConsulta.getSiguiente();
            }
            nodoMedico = nodoMedico.getSiguiente();
        }

        return consultas;
    }
    
    private boolean contieneConsulta(ListaSimple<Consulta> lista, Consulta consulta){
        Nodo<Consulta> nodoActual = lista.getInicio();
        while(nodoActual != null){
            if(nodoActual.getDato().equals(consulta)){
                return true;
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return false;
    }
    
    private int obtenerDiasEnMes(int mes, int año){
        YearMonth yearMonth = YearMonth.of(año, mes);
        return yearMonth.lengthOfMonth();
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
