import Controller.Aplicacion;
import Model.BaseDatos;
import View.Vista;
import View.VistaFavoritos;

import javax.swing.*;
import java.io.Serializable;

public class PrincipalMVC  implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1413967620063398188L;

    private PrincipalMVC() {
        super();
    }

    private void Menu(){
        Aplicacion controlador = new Aplicacion();
        final Vista vista = new Vista();
        BaseDatos modelo = new BaseDatos();

        controlador.setModelo(modelo);
        controlador.setVista(vista);
        vista.setControlador(controlador);
        vista.setModelo(modelo);
        modelo.setVista(vista);
        vista.ejecutar();
    }



    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PrincipalMVC().Menu();
            }
        });
    }
}