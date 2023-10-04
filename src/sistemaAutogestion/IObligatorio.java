package sistemaAutogestion;

import java.util.Date;


public interface IObligatorio {
    
    /*
    **************** REGISTROS **************************************
    */
    
    /*pre: Entero válido para maxPacientesporMedico.
    post:Si maxPacientesporMedico es menor o igual a 0 o mayor que 15, la función da como resultado de r ERROR_1. 
    Si maxPacientesporMedico es un valor válido, la función crea dos listas (listaMedicos y listaPacientes) y el resultado de r se establece como OK.*/
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico);
    
     /*pre:Valores válidos para los parámetros nombre, codMedico, tel y especialidad. La lista de médicos listaMedicos existe y está inicializada antes de llamar a esta función.      
    post:Si el médico con el mismo código ya existe, establece el resultado de r como ERROR_1.
    Si el valor de especialidad es menor que 1 o mayor que 20, establece el resultado de r como ERROR_2.
    Si médico con codMedico no existe en listaMedicos y especialidad es un valor válido, crea un objeto Medico con los datos dados, lo agrega ordenadamente a listaMedicos.*/
    public Retorno registrarMedico(String nombre,int codMedico,int tel, int especialidad); 
    
     /*pre:CodMedico es un valor valido. La lista de médicos listaMedicos existe y está inicializada antes de llamar a esta función.      
    post:Si el codMedico existe en listaMedicos, la función lo elimina de la lista y establece r como OK. 
    Si el codMedico no existe en la lista, la función establece r como ERROR_1.*/
    public  Retorno eliminarMedico(int codMedico); 
    
     /*pre:CI y direccion tienen valores validos. La lista de pacientes listaPacientes existe y está inicializada antes de llamar a esta función.      
    post:Si ya existe un paciente con el mismo CI en listaPacientes, establece r como ERROR_1.
    Si no existe un paciente con mismo CI en listaPacientes, y CI y direccion son válidos, crea un objeto Paciente  y lo agrega al final de listaPacientes.*/
    public Retorno agregarPaciente(String nombre, int CI, String direccion);
    
     /*pre: CI tiene un valor valido. listaPacientes existe y está inicializada antes de llamar a esta función.      
    post:Si existe paciente con el mismo CI en listaPacientes, la función lo elimina de la lista y establece r como OK.
    Si no existe paciente con el mismo CI en listaPacientes, establece  r como ERROR_1.*/
    public Retorno eliminarPaciente(int CI); 

     /*
    **************** GESTIÓN DE CONSULTAS **************************************
    */
      
    /*pre: codMedico, ciPaciente y fecha deben tener valores validos. listaMedicos, listaPacientes, listaConsultas y listaEspera existen y estan inicializadas antes de llamar a la funcion    
    post: Si codMedico existe en listaMedicos, ciPaciente existe en listaPacientes y la fecha es un valor valido se crea un objeto Consulta y lo agrega ordenadamente a listaConsultas, establece r como OK.
    Si el medico no tiene numeros disponibles para el dia de la consulta se añadira ordenadamente la solicitud a una lista de espera existente.
    Si el ciPaciente no existe en listaPacientes establece r como ERROR_1.
    Si el codMedico no existe en listaMedicos establece r como ERROR_2.
    Si hay una consulta en listaConsultas que tenga por codMedico y ciPaciente los dados como parametro, establece r como ERROR_3.*/
    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha);
    
    /*pre:codMedico y ciPaciente deben tener valores validos. listaMedicos, listaPacientes, listaConsultas y listaEspera existen y estan inicializadas      
    post: Si codMedico existe en listaMedicos, ciPaciente existe en lista pacientes y estan asignados a una consulta en listaConsulta que tenga estado "Pendiente"
    se elimina la consulta, se crea una nueva consulta con el primer paciente de la lista de espera y se asigna a ese numero. Se establece r como OK.
    Si ciPaciente no existe en listaPacientes se establece r como ERROR_1.
    Si codMedico no existe en listaMedicos se establece r como ERROR_2.
    Si ciPaciente no tiene una consulta asignada con codMedico se establece r como ERROR_3.
    Si la consulta asignada a ciPaciente y codMedico no tiene estado "Pendiente" se establece r como ERROR_4*/
    public Retorno cancelarReserva(int codMedico, int ciPaciente);
    
    /*pre: codMedico y CIPaciente deben tener valores validos, listaMedicos, listaPacientes y listaConsultas existen y estan inicializadas.     
    post: Si en listaConsultas hay una consulta con estado "Pendiente" para el codMedico y el CIPaciente y que su fecha sea igual a today. Su estado
    se cambiara a "En espera",y se imprimira el nombre del medico con codMedico y el numero de reserva, se establece r como OK.
    Si CIPaciente no existe en listaPacientes se establece r como ERROR_1
    Si en la listaConsultas no hay una consulta con los codMedico y CIPaciente asignados se establece r como ERROR_2
    Si en la listaConsultas no hay una consulta con los codMedico y CIPaciente asignados para la fecha del dia se establece r como ERROR_2*/
    public Retorno anunciaLlegada(int codMedico, int CIPaciente);
    
    /*pre:CIPaciente, codMedico y detalleDeConsulta tienen valores validos     
    post:*/
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta);
    
    //pre:      post:
    public Retorno cerrarConsulta(String codMédico, Date fechaConsulta); 
    
 
      /*
    **************** LISTADO Y REPORTES **************************************
    */
    
    /*pre: listaMedicos existe y está inicializada antes de llamar a esta función.      
    post:Muestra la lista de médicos sin modificarla y establece r como OK después de mostrar la lista.*/
    public Retorno listarMédicos();
    
    /*pre: listaPacientes existe y está inicializada antes de llamar a esta función.      
    post:Muestra la lista de pacientes sin modificarla y establece r como OK después de mostrar la lista.*/
    public Retorno listarPacientes();
    
    /*pre:listaConsultas existe y está inicializada antes de llamar a esta función. codMedico es un valor valido.     
    post:Si codMedico existe en el sistema muestra la lista de consultas para el mismo sin modificarla y establece r como OK después de mostrar la lista.
    Si codMedico no existe en el sistema establece r como ERROR_1.*/
    public Retorno listarConsultas(int codMedico); 
    
    /*pre: codMedico y fecha son valores validos. listaMedicos, listaConsultas y listaPacientes existen y estan inicializadas antes de llamar a esta funcion.      
    post:Si codMedico existe en listaMedicos y fecha es un valor valido, devuelve la lista de pacientes que tienen una consulta asignada al medico con el
    codMedico dado y el estado de la consulta es "Pendiente", establece r como OK despues de mostrar la lista.
    Si no hay consultas asignadas para esa fecha establece r como ERROR_1*/
    public Retorno listarPacientesEnEspera (String codMedico, Date fecha);
    
    /*pre: CIPaciente es un valor valido. listaPacientes y listaConsultas existen y estan inicializadas antes de llamar a esta funcion      
    post: Si CIPaciente existe en listaPacientes, y listaConsultas tiene consultas con estado "Pendiente" asignadas a ese CIPaciente devuelve la lista de
    consultas pendientes para el paciente dado y establece r como OK despues de mostrar la lista.
    Si CIPaciente no existe en listaPacientes establece r como ERROR_1.*/
    public Retorno consultasPendientesPaciente(int CIPaciente);
    
    /*pre: ci es un valor valido. listaPacientes y listaConsultas existen y estan inicializadas antes de llamar a esta funcion.     
    post: Si ci existe en listaPacientes, y listaConsultas tiene consultas con estado "Terminada" o "No asistio" asignadas a ese 
    ci devuelve la lista de consultas a las que asistio y establece r como OK despues de mostrar la lista.
    Si ci no existe en listaPacientes establece r como ERROR_1.*/
    public Retorno historiaClínicaPaciente (int ci);
    
    /*pre: mes y año son valores validos. listaPacientes y listaEspecialidades existen y estan incializadas antes de llamar a esta funcion     
    post: Si mes y año son valores validos (que mes sea de 1 a 12 y que el año sea de 2020 hasta 2023) devuelve la cantidad de pacientes
    por cada especialidad y dia del mes y establece r como OK despues de mostrar el reporte.
    Si mes y año no son valores validos establece r como ERROR_1.*/
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año); 
    /*tal vez no deberia haber una listaEspecialidades sino q dentro de esta funcion arme una lista de especialidades
    recorriendo a cada medico y tomando la especialidad de su property y ahi despues q la arme la recorra y busque lo q tenga q buscar*/
     
}
