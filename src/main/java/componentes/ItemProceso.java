/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentes;

import algoritmos.Proceso;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author btell
 */
public class ItemProceso extends VBox {
    public ItemProceso(Proceso proceso) {
        super(new Label("" + proceso.getIdProceso()), new Label(proceso.getNombre()), new BotonEstilado("Eliminar"));
    }
}
