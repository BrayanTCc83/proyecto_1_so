/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

/**
 *
 * @author btell
 */
public class BancoProcesos implements Observable {
    static BancoProcesos banco;
    final private ListaProcesos listaProcesos;
    
    private BancoProcesos() {
        this.listaProcesos = new ListaProcesos();
    }
    
    public static BancoProcesos obtenerBancoProcesos() {
        if(banco == null)
            banco = new BancoProcesos();
        
        return banco;
    }
    
    public void insertar(String nombre, float tamano, float tiempoLlegada, float tiempoEjecucion) {
        this.listaProcesos.insertar(new Proceso(nombre, tamano, tiempoLlegada, tiempoEjecucion));
        notificar();
    }
    
    public boolean eliminar(Proceso proceso) {
        Proceso resultado = this.listaProcesos.eliminar(proceso.getIdProceso());
        if(resultado != null)
            notificar();
        
        return resultado != null;
    }
    
    public ColaProcesos extraerProcesos(int tiempo) {
        if(listaProcesos.estaVacia())
            return null;
        
        ColaProcesos cola = new ColaProcesos();
        
        Nodo nodo = listaProcesos.obtenerInicio();
        while(nodo != null) {
            if(nodo.proceso.getTllegada() == tiempo)
                cola.enqueue(listaProcesos.eliminar(nodo.proceso.getIdProceso()));

            nodo = nodo.siguiente;
        }
        notificar();
        
        return cola;
    }
    
    public void vaciar() {
        this.listaProcesos.vaciar();
        if(this.o != null)
            this.o.actualizar();
    }
    
    public Proceso[] recuperarProcesos() {
        return this.listaProcesos.comoArreglo();
    }

    private Observador o;
    @Override
    public void observar(Observador o) {
        this.o = o;
    }

    @Override
    public void olvidar(Observador o) {
        this.o = null;
    }

    @Override
    public void notificar() {
        if(this.o != null)
            this.o.actualizar();
    }
}
