
package tads;

public interface IListaSimple<T> {
    
    boolean esVacia();
    void agregarInicio(T dato);
    void agregarFinal(T dato);
    void eliminarInicio();
    void eliminarFinal();
    void eliminarElemento(T dato);
    boolean existeElemento(T dato);
    Nodo obtenerElemento(T dato);
    void vaciar();
    void mostrar();
     int cantidadElementos();
    public void agregarOrd(T n);
     public void mostrarREC();
     public void mostrarRECinverso();

}
