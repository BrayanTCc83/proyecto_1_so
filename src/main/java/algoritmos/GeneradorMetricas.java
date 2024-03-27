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
        final private Proceso proceso;
        final public int idProceso;
        private int tiempoEjecutado = 0;
        private int tiempoMaximoEjecucion = 0;
        private int tiempoMaximaEspera = -1;
        private int tiempoPrimeraSubida = -1;
        protected Metrica(Proceso proceso) {
            this.proceso = proceso;
            this.idProceso = proceso.idProceso;
        }
        
        public void actualizarTiemposEjecucion() {
            int quantum = GestorDeProcesos.obtenerGestorProcesos().obtenerQuantum();
            if(tiempoPrimeraSubida != -1 && tiempoMaximoEjecucion%quantum==0)
                tiempoEjecutado = tiempoMaximoEjecucion;
            tiempoMaximoEjecucion ++;
        }
        
        public void actualizarTiempoEspera(int subida) {
            if(tiempoPrimeraSubida < 0)
                tiempoPrimeraSubida = subida;
            
            tiempoMaximaEspera = subida;
        }
        
        public int obtenerTiempoEjecutado() {
            return tiempoEjecutado;
        }
        
        public int obtenerMaximaEspera() {
            return tiempoMaximaEspera;
        }
        
        public int obtenerPrimeraSubida() {
            return tiempoPrimeraSubida;
        }
        
        public int obtenerTiempoRestante() {
            return ((int)proceso.tejecucion) - tiempoEjecutado;
        }
        
        public float calcularTiempoEspera() {
            return tiempoMaximaEspera - proceso.tejecucion - tiempoEjecutado;
        }
        
        public float calcularTiempoEjecucion() {
            return tiempoMaximoEjecucion - proceso.tllegada;
        }
        
        public float calcularTiempoRespuesta() {
            return tiempoPrimeraSubida - proceso.tllegada;
        }
        
        public boolean estaTerminado() {
            return tiempoEjecutado >= proceso.tejecucion;
        }
    }
    private final Lista<Metrica> metricas = new Lista<>(Metrica.class);
    private int estaArriba = -1;
    private int tiempoActual = 0;
    
    void registrarProceso(Proceso proceso) {
        metricas.insertar(new Metrica(proceso));
        estaArriba = proceso.idProceso;
        notificar();
    }
    
    void subirProceso(Proceso proceso) {
        tiempoActual = 0;
        estaArriba = proceso.idProceso;
        notificar();
    }
    
    public int obtenerProcesoArriba() {
        tiempoActual++;
        return estaArriba;
    }
    
    public int obtenerTiempoArribaActual() {
        notificar();
        return tiempoActual;
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
