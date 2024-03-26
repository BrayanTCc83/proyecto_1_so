/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import algoritmos.BancoProcesos;
import componentes.BotonEstilado;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import vistas.Vista;
import vistas.VistaPrecargarProcesos;

/**
 *
 * @author btell
 */
public class ControladorPrecargar extends Controlador {
    public ControladorPrecargar(Vista vista) {
        super(vista);
        BancoProcesos.obtenerBancoProcesos().observar(this);
    }

    @Override
    public void configurar() {
        VistaPrecargarProcesos precargar = ((VistaPrecargarProcesos)this.vista);
        VBox formulario = precargar.recuperarFormulario();
        BotonEstilado botonCrear = (BotonEstilado) formulario.getChildren().get(9);
        BotonEstilado botonCancelar = (BotonEstilado) precargar.recuperarControles().getChildren().get(0);
        BotonEstilado botonContinuar= (BotonEstilado) precargar.recuperarControles().getChildren().get(1);

        botonCrear.setOnAction(evento -> {
            ObservableList<Node> children = formulario.getChildren();
            try {
                ((Label)children.get(1)).setText("#");
                String nombre = ((TextField)children.get(2)).getText();
                String duracion = ((TextField)children.get(4)).getText();
                String llegada = ((TextField)children.get(6)).getText();
                String tamano = ((TextField)children.get(8)).getText();
                
                BancoProcesos.obtenerBancoProcesos().insertar(
                    nombre, Float.parseFloat(duracion), 
                    Float.parseFloat(llegada), Float.parseFloat(tamano)
                );
                vista.refrescar();
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        });
        
        botonCancelar.setOnAction(evento -> {
            BancoProcesos.obtenerBancoProcesos().vaciar();
        });
        
        botonContinuar.setOnAction(evento -> {
        
        });
    }

    @Override
    public void actualizar() {
        this.vista.refrescar();
    }
}
