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
    private int idProceso;
    private String nombre;
    private int tame;   
    private int tejecucion;
    private int tllegada;
    
    
    
    public Proceso(String n,int tm,int tej,int tll) {
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

    public int getTame() {
        return tame;
    }

    public int getTejecucion() {
        return tejecucion;
    }

    public int getTllegada() {
        return tllegada;
    }
    
}