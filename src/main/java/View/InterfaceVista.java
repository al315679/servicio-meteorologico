package View;


import Controller.Aplicacion;
import Model.BaseDatos;

public interface InterfaceVista {

    void ejecutar();
    void setControlador(Aplicacion controlador);
    void setModelo(BaseDatos modelo);
    Vista getVista();

}

