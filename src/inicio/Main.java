package inicio;

import java.util.Date;
import java.time.LocalDate;
import java.time.Month;
import sistemaAutogestion.*;
import sistemaAutogestion.Sistema;
public class Main {

    public static void main(String[] args) {
        Prueba p = new Prueba();
        Sistema s = new Sistema();
        p.inicializarResultadosPrueba();
        p1_creacionSistema(p,s);
        p1_RegistrarMedico(p,s);
        p1_RegistrarPaciente(p,s);
        p1_ListarPacientes(p,s);
        p1_ListarMedicos(p,s);
        p1_RegistrarDiaConsulta(p,s);
        p1_ReservaConsulta(p,s);
        p2_EliminarMedico(p,s);
        p1_EliminarPaciente(p,s);
        p1_AnunciarLlegada(p,s);
        p1_CancelarReserva(p,s);
        p1_ListarConsultas(p,s);
        p1_ListarPacientesEspera(p,s);
        p1_terminarConsultaMedicoPaciente(p,s);
        p1_cerrarConsulta(p,s);
        p1_ListarHistorialClinico(p,s);
        p1_ConsultasPendientesPaciente(p,s);
        p1_reporteDePacientesXFechaYEspecialidad(p,s);
        p.imprimirResultadosPrueba();   
    }
    public static void p1_creacionSistema(Prueba p, Sistema s){
        //Caso de prueba: Se puede inicializar el sistema correctamente
        p.ver(s.crearSistemaDeAutogestion(10).resultado, Retorno.Resultado.OK , "Se crea el sistema con capacidad de 10 pacientes por médico");
        //Caso de prueba: No se puede incializar el sistema porque la cantidad de pacientes es menor a 0
        p.ver(s.crearSistemaDeAutogestion(-1).resultado, Retorno.Resultado.ERROR_1 , "No se crea el sistema, capacidad fuera de rango");
        //Caso de prueba: No se puede inicializar el sistema porque la cantidad de pacientes es menor a 0
        p.ver(s.crearSistemaDeAutogestion(17).resultado, Retorno.Resultado.ERROR_1 , "No se crea el sistema, capacidad fuera de rango");
    }
    public static void p1_RegistrarMedico(Prueba p, Sistema s){
        //Caso de prueba: Si se pudo registrar el medico
        p.ver(s.registrarMedico("Nahuel", 1, 609, 8).resultado, Retorno.Resultado.OK, "Se da de alta un medico exitosamente");//Se elimina en proximo metodo
        p.ver(s.registrarMedico("Paula", 2, 609, 5).resultado, Retorno.Resultado.OK, "Se da de alta un medico exitosamente");
        p.ver(s.registrarMedico("Jorge", 3, 609, 1).resultado, Retorno.Resultado.OK, "Se da de alta un medico exitosamente");
        p.ver(s.registrarMedico("Bruno", 4, 609, 3).resultado, Retorno.Resultado.OK, "Se da de alta un medico exitosamente");
        p.ver(s.registrarMedico("Raul", 5, 609, 5).resultado, Retorno.Resultado.OK, "Se da de alta un medico exitosamente");
        //Caso de prueba: Ya existe un medico con igual codigo
        p.ver(s.registrarMedico("Nahuel", 1, 609, 8).resultado, Retorno.Resultado.ERROR_1, "Medico ya existente, no se da de alta");
        //Caso de prueba: Especialidad fuera de rango <1 o >20
        p.ver(s.registrarMedico("Paula", 7, 609, 0).resultado, Retorno.Resultado.ERROR_2, "Especialidad fuera de rango, no se da de alta");
        p.ver(s.registrarMedico("Paula", 6, 609, 21).resultado, Retorno.Resultado.ERROR_2, "Especialidad fuera de rango, no se da de alta");
    }
    public static void p1_RegistrarPaciente(Prueba p, Sistema s){
        //Caso de prueba: Si se pudo crear el paciente
        p.ver(s.agregarPaciente("Nahuel", 60242466, "Instrucciones 3928").resultado, Retorno.Resultado.OK, "Se agrega correctamente al paciente");
        p.ver(s.agregarPaciente("Paola", 4214522, "Av. Las Heras 2152").resultado, Retorno.Resultado.OK, "Se agrega correctamente al paciente");
        p.ver(s.agregarPaciente("Juan", 123144, "Belloni 5023").resultado, Retorno.Resultado.OK, "Se agrega correctamente al paciente");
        p.ver(s.agregarPaciente("Gabino", 1, "Av. Falsa 123").resultado, Retorno.Resultado.OK, "Se agrega correctamente al paciente");
        p.ver(s.agregarPaciente("Lucia", 2, "Tabaré s/n").resultado, Retorno.Resultado.OK, "Se agrega correctamente al paciente");//prueba paula
        p.ver(s.agregarPaciente("Paciente Prueba", 9999, "Direccion de prueba").resultado, Retorno.Resultado.OK, "Se agrega correctamente al paciente de prueba");

        //Caso de prueba: No se pudo crear el paciente porque ya hay uno con igual CI
        p.ver(s.agregarPaciente("Nahuel", 60242466, "Instrucciones 3928").resultado, Retorno.Resultado.ERROR_1, "Paciente con la misma cedula, no se crea");
    }
    public static void p1_ListarPacientes(Prueba p, Sistema s){
        //Caso de prueba: Se muestran todos los pacientes con sus datos
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK,"Se imprime correctamente la lista de pacientes");
    }
    public static void p1_ListarMedicos(Prueba p, Sistema s){
        //Caso de prueba: se muestran todos los medicos
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK,"Se imprime correctamente la lista de médicos.");
    }
    public static void p1_RegistrarDiaConsulta(Prueba p, Sistema s){
        // Suponiendo que ya has registrado algunos médicos con los códigos 1, 2, 3, etc.
        // y que la fecha actual es 2023-09-25.

        // Caso de prueba: Registrar día de consulta exitosamente
        p.ver(s.registrarDiaDeConsulta(4, s.convertirLocalDateADate(LocalDate.of(2023,9,26))).resultado, Retorno.Resultado.OK, "Se registra día de consulta para el médico con código 4");

        // Caso de prueba: Intentar registrar día de consulta para un médico que no existe
        p.ver(s.registrarDiaDeConsulta(99, s.convertirLocalDateADate(LocalDate.of(2023,9,26))).resultado, Retorno.Resultado.ERROR_1, "No se registra día de consulta, médico no existe");

        // Caso de prueba: Intentar registrar un día de consulta que ya existe
        p.ver(s.registrarDiaDeConsulta(4, s.convertirLocalDateADate(LocalDate.of(2023,9,26))).resultado, Retorno.Resultado.ERROR_2, "No se registra día de consulta, fecha ya registrada para el médico");

        // Caso de prueba: Registrar día de consulta para una nueva fecha
        p.ver(s.registrarDiaDeConsulta(4, s.convertirLocalDateADate(LocalDate.of(2023,9,27))).resultado, Retorno.Resultado.OK, "Se registra un nuevo día de consulta para el médico con código 4");

        // Caso de prueba: Registrar día de consulta para otro médico en la misma fecha
        p.ver(s.registrarDiaDeConsulta(2, s.convertirLocalDateADate(LocalDate.of(2023,9,26))).resultado, Retorno.Resultado.OK, "Se registra día de consulta para el médico con código 2 en la misma fecha que el médico con código 1");
        
        // Caso de prueba: Registrar día de consulta para una nueva fecha que es today
        p.ver(s.registrarDiaDeConsulta(5, s.convertirLocalDateADate(LocalDate.now())).resultado, Retorno.Resultado.OK, "Se registra un nuevo día de consulta para el médico con código 5");

    }
    public static void p1_ReservaConsulta(Prueba p, Sistema s) {//arreglar comentarios bien
        // Asumimos que ya existen en el sistema un médico con código 4 y un paciente con CI 4214522
        
        //Caso de prueba: se realiza la reserva correctamente
        p.ver(s.reservaConsulta(4, 4214522, s.convertirLocalDateADate(LocalDate.of(2023,9,26))).resultado, Retorno.Resultado.OK, "Se realiza la reserva correctamente");
        //Caso de prueba: se realiza la reserva correctamente
        p.ver(s.reservaConsulta(2, 4214522, s.convertirLocalDateADate(LocalDate.of(2023,9,26))).resultado, Retorno.Resultado.OK, "Se realiza la reserva correctamente");
        //Caso de prueba: No se realiza la reserva porque no existe la cedula del paciente
        p.ver(s.reservaConsulta(4, 99999999, s.convertirLocalDateADate(LocalDate.of(2023, 9, 27))).resultado, Retorno.Resultado.ERROR_1, "No existe la cédula del paciente");
        //Caso de prueba: No se realiza la reserva porque no existe el codigo del 
        p.ver(s.reservaConsulta(999, 4214522, s.convertirLocalDateADate(LocalDate.of(2023,9,27))).resultado, Retorno.Resultado.ERROR_2, "No existe el código del médico");
    
        // Asumimos que el paciente 4214522 ya tiene una consulta con el médico 4
        p.ver(s.reservaConsulta(4, 4214522, s.convertirLocalDateADate(LocalDate.of(2023,9,26))).resultado, Retorno.Resultado.ERROR_3, "El médico ya tiene una consulta con ese paciente");

        // Asumimos que no hay un día de consulta registrado para la fecha dada
        p.ver(s.reservaConsulta(4, 4214522, s.convertirLocalDateADate(LocalDate.of(2023,9,25))).resultado, Retorno.Resultado.ERROR_4, "No hay un día de consulta registrado para esa fecha");
    
        // Asumimos que existe en el sistema un medico con el codigo 5 y un paciente con CI 2 y que el medico tiene dia de consulta hoy PARA TERMINAR CONSULTA MEDICO-PACIENTE
        p.ver(s.reservaConsulta(5, 2, s.convertirLocalDateADate(LocalDate.now())).resultado, Retorno.Resultado.OK, "Se realiza la reserva correctamente para el medico 5 y el paciente 2");
        // Asumimos que existe en el sistema un medico con el codigo 5 y un paciente con CI 1 y que el medico tiene dia de consulta hoy
        p.ver(s.reservaConsulta(5, 1, s.convertirLocalDateADate(LocalDate.now())).resultado, Retorno.Resultado.OK, "Se realiza la reserva correctamente para el medico 5 y el paciente 1");
        // Asumimos que existe en el sistema un medico con el codigo 5 y un paciente con CI 1 y que el medico tiene dia de consulta hoy PARA CERRAR CONSULTA CON ESTADO PENDIENTE
        p.ver(s.reservaConsulta(5, 4214522, s.convertirLocalDateADate(LocalDate.now())).resultado, Retorno.Resultado.OK, "Se realiza la reserva correctamente para el medico 5 y el paciente 1");
    }
    public static void p2_EliminarMedico(Prueba p, Sistema s){
        //Caso de prueba: Si se pudo eliminar el medico
        p.ver(s.eliminarMedico(1).resultado, Retorno.Resultado.OK, "Se elimina correctamente al médico, no tiene consultas agendadas");
        //Caso de prueba: No se puede eliminar medico porque no existe medico con ese codigo
        p.ver(s.eliminarMedico(52).resultado, Retorno.Resultado.ERROR_1, "Médico no existente, no se puede borrar");
        //Falta hacer que no se pueda borrar porque tiene reservado
        p.ver(s.eliminarMedico(5).resultado, Retorno.Resultado.ERROR_2, "Médico no se puede borrar porque tiene una reserva");

    }
    public static void p1_EliminarPaciente(Prueba p, Sistema s){
        //Caso de prueba: Si se pudo eliminar al paciente
        p.ver(s.eliminarPaciente(60242466).resultado, Retorno.Resultado.OK,"Se elimina correctamente, es un paciente válido");
        //Caso de prueba: No se puede eliminar al paciente porque no existe su CI
        p.ver(s.eliminarPaciente(66023241).resultado, Retorno.Resultado.ERROR_1,"No se elimina, no hay paciente con esa cédula");
        //Caso de prueba: No se puede eliminar al paciente porque ya agendo una consulta
        p.ver(s.eliminarPaciente(4214522).resultado, Retorno.Resultado.ERROR_2,"No se elimina, paciente ha realizado alguna consulta");
    }
    public static void p1_AnunciarLlegada(Prueba p, Sistema s){
        
    //Caso de prueba: Anunciar llegada de un paciente con CI 2 para el medico con codigo 5 en la fecha de hoy.
    p.ver(s.anunciaLlegada(5, 2).resultado, Retorno.Resultado.OK, "Se anuncia llegada del paciente con CI 2");
    //Caso de prueba: Anunciar llegada de un paciente con CI 1 para el medico con codigo 5 en la fecha de hoy.
    p.ver(s.anunciaLlegada(5, 1).resultado, Retorno.Resultado.OK, "Se anuncia llegada del paciente con CI 4214522");
    //Caso de prueba: Anunciar llegada de un paciente que no exista su CI
    p.ver(s.anunciaLlegada(5, 55555).resultado, Retorno.Resultado.ERROR_1, "No se anuncia la llegada, paciente no existe");
    //Caso de prueba: Anunciar llegada de un paciente que no tiene consulta ese día
    p.ver(s.anunciaLlegada(2, 4214522).resultado, Retorno.Resultado.ERROR_2, "El paciente no tiene consulta el día de hoy");

    }
    public static void p1_CancelarReserva(Prueba p, Sistema s){
        // Asumimos que el paciente 4214522 tiene una reserva pendiente con el médico 1
        
        //Caso de prueba: Si se pudo cancelar la reserva
        p.ver(s.cancelarReserva(4, 4214522).resultado, Retorno.Resultado.OK, "Se cancela la reserva correctamente");
        //Caso de prueba: No se pudo cancelar porque no existe la CI del paciente
        p.ver(s.cancelarReserva(4, 99999999).resultado, Retorno.Resultado.ERROR_1, "No existe la cédula del paciente");
        //Caso de prueba: No se pudo cancelar porque no existe el codigo del medico
        p.ver(s.cancelarReserva(999, 4214522).resultado, Retorno.Resultado.ERROR_2, "No existe el código del médico");
        //Caso de prueba: No se puede cancelar porque no tiene una reserva con ese medico o esta cerrada
        p.ver(s.cancelarReserva(4, 4214522).resultado, Retorno.Resultado.ERROR_3, "El paciente no tenía una reserva con ese médico o está cerrada");   
        //Caso de prueba: La reserva no esta en estado pendiente
        p.ver(s.cancelarReserva(5, 2).resultado, Retorno.Resultado.ERROR_4, "La reserva no está en estado pendiente");
    }
    public static void p1_ListarPacientesEspera(Prueba p, Sistema s){
        
        //Caso de prueba: Si se muestran los pacientes “en espera” para ese médico en esa fecha.
        p.ver(s.listarPacientesEnEspera("5", s.convertirLocalDateADate(LocalDate.of(2023,11,9))).resultado, Retorno.Resultado.OK, "Se listan los pacientes en espera para el médico con código 2 en la fecha");
        
        //Caso de prueba: No se muestran los pacientes “en espera” para ese médico en esa fecha porque el medico no tiene dia de consulta .
        p.ver(s.listarPacientesEnEspera("3", s.convertirLocalDateADate(LocalDate.now())).resultado, Retorno.Resultado.ERROR_1, "No se listan los pacientes porque el médico con código 3 no tiene consultas en la fecha de hoy.");
    }
    public static void p1_terminarConsultaMedicoPaciente(Prueba p, Sistema s){
        //Se asume que existe una reserva en espera para el medico con codigo 5 y el paciente con CI 2
        p.ver(s.terminarConsultaMedicoPaciente(2, 5, "Reposo por una semana").resultado, Retorno.Resultado.OK, "Se cierra la consulta del paciente 2 con el medico 5 y se setea la descripcion");
        // Caso de prueba: CI de paciente no existe
        p.ver(s.terminarConsultaMedicoPaciente(4343, 5, "Detalle de la consulta").resultado, Retorno.Resultado.ERROR_1, "No existe un paciente con CI 4343");

        // Caso de prueba: Código de médico no existe
        p.ver(s.terminarConsultaMedicoPaciente(2, 4764, "Detalle de la consulta").resultado, Retorno.Resultado.ERROR_2, "No existe un médico con código 4764");

        // Caso de prueba: No hay consulta "en espera" en la fecha del día
        p.ver(s.terminarConsultaMedicoPaciente(9999, 2, "Detalle de la consulta").resultado, Retorno.Resultado.ERROR_2, "No hay consulta 'en espera' para hoy para el paciente con CI 9999 con el médico 2");
    }
    public static void p1_cerrarConsulta(Prueba p, Sistema s){
        //Caso de prueba: Si se pudo cerrar la consulta correctamente
        p.ver(s.cerrarConsulta(5, s.convertirLocalDateADate(LocalDate.now())).resultado, Retorno.Resultado.OK, "Se cierra la consulta del medico 5");
        //Caso de prueba: No se cierra porque el medico no existe
        p.ver(s.cerrarConsulta(105, s.convertirLocalDateADate(LocalDate.now())).resultado, Retorno.Resultado.ERROR_1, "No se cierra la consulta del medico 105 porque ese medico no existe");
        //Caso de prueba: No se cierra porque el medico no tiene consultas el dia de la fecha
        p.ver(s.cerrarConsulta(2, s.convertirLocalDateADate(LocalDate.now())).resultado, Retorno.Resultado.ERROR_2, "No se cierra la consulta del medico 2, no tiene consultas el dia de la fecha");
    }
    public static void p1_ListarConsultas(Prueba p, Sistema s){
        //Caso de prueba: Se listan las consultas con sus fechas.
        p.ver(s.listarConsultas(2).resultado, Retorno.Resultado.OK, "Se listan correctamente las consultas del médico con código 2");
        //Caso de prueba: No se listan las consultas porque el codigo de medico no existe.
        p.ver(s.listarConsultas(999).resultado, Retorno.Resultado.ERROR_1, "No se pueden listar las consultas porque el médico con código 999 no existe");
    }
    public static void p1_ListarHistorialClinico(Prueba p, Sistema s){
        // Asumimos que el paciente con CI 4214522 ha tenido consultas previas
        p.ver(s.historiaClínicaPaciente(4214522).resultado, Retorno.Resultado.OK, "Se lista correctamente el historial clínico del paciente con CI 4214522");
        // Caso de prueba: Intentar listar el historial clínico de un paciente que no existe
        p.ver(s.historiaClínicaPaciente(99999999).resultado, Retorno.Resultado.ERROR_1, "No se puede listar el historial clínico, paciente no existe");
    }
    public static void p1_ConsultasPendientesPaciente(Prueba p, Sistema s) {
    // Prueba con un paciente que tiene consultas pendientes
    // Asumiendo que el paciente con CI 4214522 tiene consultas pendientes
    p.ver(s.consultasPendientesPaciente(4214522).resultado, Retorno.Resultado.OK, "Se listan correctamente las consultas pendientes del paciente con CI 4214522");

    // Prueba con un paciente que no tiene consultas pendientes
    // Asumiendo que el paciente con CI 1 está en el sistema pero no tiene consultas pendientes
    p.ver(s.consultasPendientesPaciente(1).resultado, Retorno.Resultado.OK, "No hay consultas pendientes para el paciente con CI 1, pero la operación es correcta");

    // Prueba con un paciente inexistente
    // Asumiendo que no hay un paciente con CI 99999999
    p.ver(s.consultasPendientesPaciente(99999999).resultado, Retorno.Resultado.ERROR_1, "No existe un paciente con CI 99999999, se retorna un error");
    }
    public static void p1_reporteDePacientesXFechaYEspecialidad(Prueba p, Sistema s){
        Retorno resultado = s.reporteDePacientesXFechaYEspecialidad(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        p.ver(resultado.resultado, Retorno.Resultado.OK, "Se muestra la matriz");
        if(resultado.resultado == Retorno.Resultado.OK){
            System.out.println("Matriz de consultas por dia y especialidad");
            System.out.println(resultado.getValorString());
        }
    }
    
}   
    
