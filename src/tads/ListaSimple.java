
package tads;

public class ListaSimple<T extends Comparable<T>> implements IListaSimple<T> {
    
    private Nodo<T> inicio;
    
    private Nodo<T> fin;
    private int cantMax;
    
    public int cantElementos;
    public ListaSimple(int tope){
        inicio = null;
        cantElementos = 0;
        cantMax = tope;
    }
    public ListaSimple(){
        inicio = null;
        cantElementos = 0;
    }
    
    @Override
    public boolean esVacia() {
        return getInicio() == null;
    }

    @Override
    public void agregarInicio(T dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(inicio);
        inicio = nuevo;
        if(cantElementos == 0){
            fin = nuevo;
        }
        cantElementos++;
    }

    @Override
    public void agregarFinal(T dato) {
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
            }
        cantElementos++;
    }

    @Override
    public void eliminarInicio() {
        if (!esVacia()) {
            Nodo borrar = inicio;
            inicio = inicio.getSiguiente();
            borrar.setSiguiente(null);
            cantElementos--;
        }
    }

    @Override
    public void eliminarFinal() {
        if (!esVacia()) {
            if (inicio.getSiguiente() == null) {
                this.vaciar();
            } else {

                Nodo actual = inicio;

                while (actual.getSiguiente().getSiguiente() != fin) {
                    actual = actual.getSiguiente();

                }
                actual.setSiguiente(null);
                fin = actual;
                cantElementos--;
            }
        }
    }

    @Override
    public void eliminarElemento(T dato) {
        if (!esVacia()) {
            if (inicio.getDato().equals(dato)) {
                this.eliminarInicio();
            } else {

                Nodo aux = inicio;

                while (aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(dato)) {
                    aux = aux.getSiguiente();
                }
                
                if(aux.getSiguiente()!=null){ //Enbcontre el elemento
                    
                    Nodo aBorrar = aux.getSiguiente();
                    aux.setSiguiente(aBorrar.getSiguiente());
                    aBorrar.setSiguiente(null);
                    cantElementos--;
                    
                }

            }

        }
    }

     @Override
    public boolean existeElemento(T dato) {
         boolean existe = false;

        Nodo aux = inicio;

        while (aux != null && !existe) {
            if (aux.getDato().equals(dato)) {
                existe = true;
            }
            aux = aux.getSiguiente();
        }

        return existe;
    }

    @Override
public Nodo<T> obtenerElemento(T n) {
    Nodo<T> actual = inicio;
    while (actual != null) {
        if (actual.getDato().equals(n)) {
            return actual; // Retorna el nodo que contiene el dato buscado.
        }
        actual = actual.getSiguiente();
    }
    return null; // Si no se encuentra el elemento, se retorna null.
}

    @Override
    public void vaciar() {
        this.inicio = null;
        cantElementos = 0;
    }

    @Override
    public void mostrar() {
        Nodo mostrar = inicio;

        while (mostrar != null) {
            System.out.println(mostrar.getDato());
            mostrar = mostrar.getSiguiente();
        }
    }

    @Override
    public int cantidadElementos() {
        return cantElementos;
    }

    @Override
    public void agregarOrd(T n) {
        if(esVacia() || inicio.getDato().compareTo(n) > 0){
            this.agregarInicio(n);
        }
        else{
            Nodo aux = inicio;
            while(aux.getSiguiente() != null && aux.getSiguiente().getDato().compareTo(n) < 0){
                aux = aux.getSiguiente();
            }
            if(aux.getSiguiente() == null){
                this.agregarFinal(n);
            }
            else{
                Nodo nuevo = new Nodo(n);
                nuevo.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(nuevo);
                cantElementos++;
            }
        }
    }

    @Override
    public void mostrarREC() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarRECinverso() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * @return the inicio
     */
    public Nodo<T> getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Nodo<T> inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the fin
     */
    public Nodo<T> getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(Nodo<T> fin) {
        this.fin = fin;
    }

}
