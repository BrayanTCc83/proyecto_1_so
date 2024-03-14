/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

/**
 *
 * @author jcapi
 */
public class Proceso {
    private static int contador = 0;
    final private int idProceso;
    final private String nombre;
    final private float tame;   
    final private float tejecucion;
    final private float tllegada;
    
    public Proceso(String n,float tm,float tej,float tll) {
        contador++; // Incrementa el contador cada vez que se crea una nueva instancia
        idProceso = contador; // Asigna el valor actual del contador como ID del proceso
        nombre=n; //Asigna el nombre del proceso
        tame=tm; //Asigna tamaño del proceso
        tejecucion=tej; //Asigna tiempo de ejecucion
        tllegada=tll;   //Asigna tiempo de llegada         
    }
    
    public int getIdProceso() {
        return idProceso;
    }

    public static int getContador() {
        return contador;
    }

    public String getNombre() {
        return nombre;
    }

    public float getTame() {
        return tame;
    }

    public float getTejecucion() {
        return tejecucion;
    }

    public float getTllegada() {
        return tllegada;
    }
}