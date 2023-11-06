package inicio;

import java.util.Date;
import sistemaAutogestion.*;
import sistemaAutogestion.Sistema;
public class Main {

    public static void main(String[] args) {
        Prueba p = new Prueba();
        Sistema s = new Sistema();
        p.inicializarResultadosPrueba();
        p1_creacionSistema(p,s);
        p1_RegistrarMedico(p,s);
        p2_EliminarMedico(p,s);
        p1_RegistrarPaciente(p,s);
        p1_EliminarPaciente(p,s);
        p1_ListarPacientes(p,s);
        p1_ListarMedicos(p,s);
        p1_RegistrarDiaConsulta(p,s);
        p1_ReservaConsulta(p,s);
        p1_CancelarReserva(p,s);
        p1_ListarConsultas(p,s);
        p.imprimirResultadosPrueba();
        
        
    }
    public static void p1_creacionSistema(Prueba p, Sistema s){
        
        p.ver(s.crearSistemaDeAutogestion(10).resultado, Retorno.Resultado.OK , "Se crea el sistema con capacidad de 10 pacientes por médico");
        p.ver(s.crearSistemaDeAutogestion(-1).resultado, Retorno.Resultado.ERROR_1 , "No se crea el sistema, capacidad fuera de rango");
        p.ver(s.crearSistemaDeAutogestion(17).resultado, Retorno.Resultado.ERROR_1 , "No se crea el sistema, capacidad fuera de rango");
    }
    public static void p1_RegistrarMedico(Prueba p, Sistema s){
        p.ver(s.registrarMedico("Nahuel", 1, 609, 8).resultado, Retorno.Resultado.OK, "Se da de alta un medico exitosamente");
        p.ver(s.registrarMedico("Paula", 2, 609, 5).resultado, Retorno.Resultado.OK, "Se da de alta un medico exitosamente");
        p.ver(s.registrarMedico("Jorge", 3, 609, 1).resultado, Retorno.Resultado.OK, "Se da de alta un medico exitosamente");
        p.ver(s.registrarMedico("Bruno", 4, 609, 3).resultado, Retorno.Resultado.OK, "Se da de alta un medico exitosamente");

        p.ver(s.registrarMedico("Nahuel", 1, 609, 8).resultado, Retorno.Resultado.ERROR_1, "Medico ya existente, no se da de alta");
        p.ver(s.registrarMedico("Paula", 5, 609, 0).resultado, Retorno.Resultado.ERROR_2, "Especialidad fuera de rango, no se da de alta");
        p.ver(s.registrarMedico("Paula", 6, 609, 21).resultado, Retorno.Resultado.ERROR_2, "Especialidad fuera de rango, no se da de alta");
    }
    public static void p2_EliminarMedico(Prueba p, Sistema s){
        p.ver(s.eliminarMedico(1).resultado, Retorno.Resultado.OK, "Se elimina correctamente al médico, no tiene consultas agendadas");
        p.ver(s.eliminarMedico(52).resultado, Retorno.Resultado.ERROR_1, "Médico no existente, no se puede borrar");

    }
    public static void p1_RegistrarPaciente(Prueba p, Sistema s){
        p.ver(s.agregarPaciente("Nahuel", 60242466, "Instrucciones 3928").resultado, Retorno.Resultado.OK, "Se agrega correctamente al paciente");
        p.ver(s.agregarPaciente("Paola", 4214522, "Av. Las Heras 2152").resultado, Retorno.Resultado.OK, "Se agrega correctamente al paciente");
        p.ver(s.agregarPaciente("Juan", 123144, "Belloni 5023").resultado, Retorno.Resultado.OK, "Se agrega correctamente al paciente");
        p.ver(s.agregarPaciente("Gabino", 1, "Av. Falsa 123").resultado, Retorno.Resultado.OK, "Se agrega correctamente al paciente");
        
        p.ver(s.agregarPaciente("Nahuel", 60242466, "Instrucciones 3928").resultado, Retorno.Resultado.ERROR_1, "Paciente con la misma cedula, no se crea");
    }
    public static void p1_EliminarPaciente(Prueba p, Sistema s){
        p.ver(s.eliminarPaciente(60242466).resultado, Retorno.Resultado.OK,"Se elimina correctamente, es un paciente válido");
        p.ver(s.eliminarPaciente(66023241).resultado, Retorno.Resultado.ERROR_1,"No se elimina, no hay paciente con esa cédula");
    }
    public static void p1_ListarPacientes(Prueba p, Sistema s){
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK,"Se imprime correctamente la lista de pacientes");
    }
    public static void p1_ListarMedicos(Prueba p, Sistema s){
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK,"Se imprime correctamente la lista de médicos.");
    }
    
    public static void p1_RegistrarDiaConsulta(Prueba p, Sistema s){
        // Suponiendo que ya has registrado algunos médicos con los códigos 1, 2, 3, etc.
        // y que la fecha actual es 2023-09-25.

        // Caso de prueba: Registrar día de consulta exitosamente
        p.ver(s.registrarDiaDeConsulta(4, new Date(2023, 9, 26)).resultado, Retorno.Resultado.OK, "Se registra día de consulta para el médico con código 4");

        // Caso de prueba: Intentar registrar día de consulta para un médico que no existe
        p.ver(s.registrarDiaDeConsulta(99, new Date(2023, 9, 26)).resultado, Retorno.Resultado.ERROR_1, "No se registra día de consulta, médico no existe");

        // Caso de prueba: Intentar registrar un día de consulta que ya existe
        p.ver(s.registrarDiaDeConsulta(4, new Date(2023, 9, 26)).resultado, Retorno.Resultado.ERROR_2, "No se registra día de consulta, fecha ya registrada para el médico");

        // Caso de prueba: Registrar día de consulta para una nueva fecha
        p.ver(s.registrarDiaDeConsulta(4, new Date(2023, 9, 27)).resultado, Retorno.Resultado.OK, "Se registra un nuevo día de consulta para el médico con código 4");

        // Caso de prueba: Registrar día de consulta para otro médico en la misma fecha
        p.ver(s.registrarDiaDeConsulta(2, new Date(2023, 9, 26)).resultado, Retorno.Resultado.OK, "Se registra día de consulta para el médico con código 2 en la misma fecha que el médico con código 1");

    }
    public static void p1_ReservaConsulta(Prueba p, Sistema s){
        // Asumimos que ya existen en el sistema un médico con código 1 y un paciente con CI 4214522
        p.ver(s.reservaConsulta(4, 4214522, new Date(2023,9,26)).resultado, Retorno.Resultado.OK, "Se realiza la reserva correctamente");
        p.ver(s.reservaConsulta(2, 4214522, new Date(2023,9,26)).resultado, Retorno.Resultado.OK, "Se realiza la reserva correctamente");
        p.ver(s.reservaConsulta(4, 99999999, new Date(2023,9,27)).resultado, Retorno.Resultado.ERROR_1, "No existe la cédula del paciente");
        p.ver(s.reservaConsulta(999, 4214522, new Date(2023,9,27)).resultado, Retorno.Resultado.ERROR_2, "No existe el código del médico");
        // Asumimos que el paciente 4214522 ya tiene una consulta con el médico 1
        p.ver(s.reservaConsulta(4, 4214522, new Date(2023,9,26)).resultado, Retorno.Resultado.ERROR_3, "El médico ya tiene una consulta con ese paciente");
        // Asumimos que no hay un día de consulta registrado para la fecha dada
        p.ver(s.reservaConsulta(4, 4214522, new Date(2023, 9, 25)).resultado, Retorno.Resultado.ERROR_4, "No hay un día de consulta registrado para esa fecha");
    }

    public static void p1_CancelarReserva(Prueba p, Sistema s){
        // Asumimos que el paciente 4214522 tiene una reserva pendiente con el médico 1
        p.ver(s.cancelarReserva(4, 4214522).resultado, Retorno.Resultado.OK, "Se cancela la reserva correctamente");
        p.ver(s.cancelarReserva(4, 99999999).resultado, Retorno.Resultado.ERROR_1, "No existe la cédula del paciente");
        p.ver(s.cancelarReserva(999, 4214522).resultado, Retorno.Resultado.ERROR_2, "No existe el código del médico");
        // Asumimos que el paciente 4214522 no tiene una reserva con el médico 1 o está cerrada
        p.ver(s.cancelarReserva(4, 4214522).resultado, Retorno.Resultado.ERROR_3, "El paciente no tenía una reserva con ese médico o está cerrada");
        // Falta todavía dejar una reserva confirmada para este caso, los demás estan OK
        p.ver(s.cancelarReserva(2, 4214522).resultado, Retorno.Resultado.ERROR_4, "La reserva no está en estado pendiente");
    }
    public static void p1_ListarConsultas(Prueba p, Sistema s){
        p.ver(s.listarConsultas(2).resultado, Retorno.Resultado.OK, "Se listan correctamente las consultas del médico con código 4");
        p.ver(s.listarConsultas(999).resultado, Retorno.Resultado.ERROR_1, "No se pueden listar las consultas porque el médico con código 999 no existe");
    }
}
