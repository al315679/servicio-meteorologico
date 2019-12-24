package View;


import Controller.Aplicacion;
import Controller.Controlador;
import Model.BaseDatos;

public interface InterfaceVista {

    void ejecutar();
    void setControlador(Aplicacion controlador);
    void setModelo(BaseDatos modelo);
    Vista getVista();

}

