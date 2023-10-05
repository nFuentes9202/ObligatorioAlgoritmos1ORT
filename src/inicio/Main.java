package inicio;

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

}
