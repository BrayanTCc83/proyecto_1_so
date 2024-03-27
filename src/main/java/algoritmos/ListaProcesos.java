/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

/**
 *
 * @author btell
 */
public class ListaProcesos extends Lista<Proceso> {    
    public ListaProcesos() {
        super(Proceso.class);
    }
    // Método para remover y retornar el proceso al frente de la cola
    public Proceso recuperar(int idProceso) {
        if (frente == null) {
            return null; // La cola está vacía
        }
        Nodo auxiliar = frente;
        while(auxiliar != null && ((Proceso)auxiliar.valor).getIdProceso() != idProceso)
            auxiliar = auxiliar.siguiente;
        
        if (auxiliar == null)
            return null;
        
        return (Proceso) auxiliar.valor;
    }
    
    public Proceso eliminar(int idProceso) {
        if (frente == null)
            return null;
        
        Nodo anterior = null;
        Nodo auxiliar = frente;
        while(auxiliar != null && ((Proceso)auxiliar.valor).getIdProceso() != idProceso) {
            anterior = auxiliar;
            auxiliar = auxiliar.siguiente;
        }
        
        if(auxiliar == null)
            return null;
        
        if(anterior != null) {
            anterior.siguiente = auxiliar.siguiente;
            auxiliar.siguiente = null;
        }
        
        if(auxiliar == frente)
            frente = auxiliar.siguiente;
        else if(auxiliar == finalCola)
            finalCola = anterior;
        
        tamano--;
        return (Proceso) auxiliar.valor;
    }
}
