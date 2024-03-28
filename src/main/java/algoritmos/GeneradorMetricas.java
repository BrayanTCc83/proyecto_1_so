/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package algoritmos;

/**
 *
 * @author btell
 */
public class GeneradorMetricas implements Observable {
    private static GeneradorMetricas instancia;
    private GeneradorMetricas(){}
    public static GeneradorMetricas obtenerGeneradorMetricas() {
        if(instancia == null)
            instancia = new GeneradorMetricas();
        
        return instancia;
    }

    public class Metrica {
        final private GeneradorMetricas padre;
        final private Proceso proceso;
        final public int idProceso;
        private int tiempoEjecutado = 0;        // Tiempo de ejecución hasta el quantum anterior (Se actualiza cuando se crea un nuevo quantum)
        private int tiempoMaximoEjecucion = 0;  // Tiempo de ejecución actual (Se actualiza cuando el proceso esta arriba)
        private int tiempoMaximaEspera = -1;    // Ultima vez que subió al procesador
        private int tiempoPrimeraSubida = -1;   // Primera vez que subió al procesador
        protected Metrica(Proceso proceso, GeneradorMetricas padre) {
            this.padre = padre;
            this.proceso = proceso;
            this.idProceso = proceso.idProceso;
        }
        
        public void actualizarTiemposEjecucion() {
            int quantum = GestorDeProcesos.obtenerGestorProcesos().obtenerQuantum();
            if(tiempoPrimeraSubida != -1 && tiempoMaximoEjecucion%quantum==0)
                tiempoEjecutado = tiempoMaximoEjecucion;
            tiempoMaximoEjecucion ++;
            padre.notificar();
        }
        
        public void actualizarTiempoEspera(int subida) {
            tiempoPrimeraSubida = subida;
        }

        public void actualizarTiempoMaximoEspera(int tiempoMaximaEspera) {
            this.tiempoMaximaEspera = tiempoMaximaEspera;
        }
        
        public int obtenerTiempoEjecutado() {
            return tiempoEjecutado;
        }

        public int obtenerTiempoEjecutadoActual() {
            return (tiempoMaximaEspera + tiempoMaximoEjecucion - tiempoEjecutado);
        }
        
        public int obtenerMaximaEspera() {
            return tiempoMaximaEspera;
        }
        
        public int obtenerPrimeraSubida() {
            return tiempoPrimeraSubida;
        }
        
        public int obtenerTiempoRestante() {
            return ((int)proceso.tejecucion) - tiempoMaximoEjecucion;
        }
        
        public float calcularTiempoEspera() {
            return tiempoMaximaEspera - proceso.tllegada - tiempoEjecutado;
        }
        
        public float calcularTiempoEjecucion() {
            return obtenerTiempoEjecutadoActual() - proceso.tllegada;
        }
        
        public float calcularTiempoRespuesta() {
            return tiempoPrimeraSubida - proceso.tllegada;
        }
        
        public boolean estaTerminado() {
            return tiempoEjecutado >= proceso.tejecucion;
        }
    }
    private final Lista<Metrica> metricas = new Lista<>(Metrica.class);
    
    void registrarProceso(Proceso proceso) {
        metricas.insertar(new Metrica(proceso, this));
        notificar();
    }
    
    public Metrica obtenerMetricasProceso(Proceso proceso) {
        for(Metrica metrica: metricas.comoArreglo())
            if(metrica.idProceso == proceso.idProceso)
                return metrica;
        return null;
    }
    
    public float calcularTiempoEsperaPromedio() {
        float total = 0;
        for(Metrica metrica: metricas.comoArreglo())
            total += metrica.calcularTiempoEspera();
        
        return total/metricas.tamano;
    }
    
    public float calcularTiempoEjecucionPromedio() {
        float total = 0;
        for(Metrica metrica: metricas.comoArreglo())
            total += metrica.calcularTiempoEjecucion();
        
        return total/metricas.tamano;
    }
    
    public float calcularTiempoRespuestaPromedio() {
        float total = 0;
        for(Metrica metrica: metricas.comoArreglo())
            total += metrica.calcularTiempoRespuesta();
        
        return total/metricas.tamano;
    }
    
    private final Lista<Observador> observadores = new Lista<>(Observador.class);
    @Override
    public void observar(Observador o) {
        observadores.insertar(o);
    }

    @Override
    public void olvidar(Observador o) {
        observadores.eliminar(o);
    }

    @Override
    public void notificar() {
        for(Observador o: observadores.comoArreglo())
            o.actualizar();
    }
}
