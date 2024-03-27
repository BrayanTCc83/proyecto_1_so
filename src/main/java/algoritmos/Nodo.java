/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

/**
 *
 * @author btell
 */
public class Nodo<T> {
    public T valor;
    public Nodo siguiente;

    public Nodo(T proceso) {
        this.valor = proceso;
        this.siguiente = null;
    }
}