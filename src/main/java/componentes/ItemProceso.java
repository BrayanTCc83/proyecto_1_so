/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentes;

import algoritmos.BancoProcesos;
import algoritmos.Proceso;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author btell
 */
public class ItemProceso extends VBox {
    final private Proceso proceso;
    public ItemProceso(Proceso proceso) {
        super(new Label("" + proceso.getIdProceso()), new Label(proceso.getNombre()), new BotonEstilado("Eliminar"));
        this.proceso = proceso;
        inicializar();
    }
    
    private void inicializar() {
        ((BotonEstilado)getChildren().get(2)).setOnAction(evento -> {
            BancoProcesos.obtenerBancoProcesos().eliminar(proceso);
        });
    }
}
