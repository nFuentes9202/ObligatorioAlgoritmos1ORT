package tads;


public class Nodo<T extends Comparable> {

    private T dato;
    private Nodo<T> siguiente;
    
    public Nodo(T elDato){
        this.setDato(elDato);
        this.setSiguiente(null);
    }

  
    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

   
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
}
