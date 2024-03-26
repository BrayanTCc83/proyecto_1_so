/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

import componentes.ItemProceso;

/**
 *
 * @author btell
 */
public class BancoProcesos {
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
    
    public void insertar(Proceso proceso) {
        this.listaProcesos.insertar(proceso);
    }
    
    public void insertar(String nombre, float tamano, float tiempoLlegada, float tiempoEjecucion) {
        this.listaProcesos.insertar(new Proceso(nombre, tamano, tiempoLlegada, tiempoEjecucion));
    }
    
    public boolean eliminar(Proceso proceso) {
        Proceso resultado = this.listaProcesos.eliminar(proceso.getIdProceso());
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
        
        return cola;
    }
    
    public Proceso[] recuperarProcesos() {
        return this.listaProcesos.comoArreglo();
    }
}
