/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package algoritmos;

/**
 *
 * @author btell
 */
public interface Observable {
    public void observar(Observador o);
    public void olvidar(Observador o);
    public void notificar();
}
