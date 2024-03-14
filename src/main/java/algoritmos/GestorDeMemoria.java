/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

/**
 *
 * @author btell
 */
public class GestorDeMemoria {
    static private GestorDeMemoria gestorMemoria = null;
    final private ColaProcesos procesosEnMemoria;
    final private float memoriaTotal;
    private float memoriaRestante;
    private int posicionFinal = 0;
    
    public static GestorDeMemoria crearGestorMemoria(float memoriaTotal) {
        if(gestorMemoria == null)
            gestorMemoria = new GestorDeMemoria(memoriaTotal);
        
        return gestorMemoria;
    }
    
    public static GestorDeMemoria obtenerGestorMemoria() {
        return gestorMemoria;
    }
    
    private GestorDeMemoria(float memoriaTotal) {
        this.memoriaTotal = this.memoriaRestante = memoriaTotal;
        this.procesosEnMemoria = new ColaProcesos();
    }
    
    public int asignarMemoria(Proceso proceso) {
        float tamProceso = proceso.getTame();
        if(this.memoriaRestante < tamProceso){
            System.out.println("Le memoria esta llena.");
            return -1;
        }
        
        memoriaRestante -= tamProceso;
        this.procesosEnMemoria.enqueue(proceso);
        System.out.println("Subió el proceso " + proceso.getNombre() + " y restan " + this.memoriaRestante + " unidades de memoria.");
        return posicionFinal++;
    }
    
    public Proceso extraerDeMemoria() {
        Proceso proceso = this.procesosEnMemoria.dequeue();
        if(proceso == null)
            return null;
        
        this.memoriaRestante += proceso.getTame();
        --this.posicionFinal;
        return proceso;
    }
    
    public float obtenerMemoriaTotal() {
        return this.memoriaTotal;
    }
    
    public float obtenerMemoriaRestante() {
        return this.memoriaRestante;
    }
    
    public int obtenerNumeroProcesosCargados() {
        return this.posicionFinal;
    }
}
