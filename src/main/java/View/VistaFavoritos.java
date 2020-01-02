package View;

import Controller.Aplicacion;
import Model.BaseDatos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class VistaFavoritos {

    /**
     *
     */
    private static final long serialVersionUID = -9128339207038054787L;
    private Aplicacion controlador;
    private BaseDatos modelo;
    private JFrame ventana;
    private JDialog emergente;
    private Container contenedor;
    private JPanel panelGeneral;
    private Boton botonAñadir, botonBorrar, botonBuscar;
    private JTextField jtfTexto, jtfBuscar;
    private JTextArea jlListaFavoritos, jlActualFavoritos, jlPrediccionFavoritos;
    private JRadioButton radioButtonCiudad, radioButtonCoordenadas;

    public VistaFavoritos() {
        ventana = new JFrame("Servicio Metereológico");
        contenedor = ventana.getContentPane();
        panelGeneral = new JPanel();
        panelGeneral.setLayout(new GridLayout(5, 2, 10, 10));
    }

    //@Override
    public void ejecutar() {

        panelGeneral.add(crearPanelAñadirBorrar());
        panelGeneral.add(crearPanelTipo());
        panelGeneral.add(crearPanelMostrar());
        panelGeneral.add(crearPanelAtras());
        contenedor.add(panelGeneral);

        ventana.addWindowListener(new EscuchadorCerrarVentana());
        ventana.pack();
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);



    }

    private Component crearPanelAñadirBorrar(){

        JPanel jpTexto=new JPanel();

        JLabel jlTexto=new JLabel("Ciudad/Coordenadas");
        jtfTexto=new JTextField(10);

        botonAñadir = new Boton("Añadir", new Añadir());
        botonAñadir.setEnabled(false);

        botonBorrar = new Boton("Borrar", new Borrar());
        botonBorrar.setEnabled(false);

        jpTexto.add(jlTexto);
        jpTexto.add(jtfTexto);
        jpTexto.add(botonAñadir);
        jpTexto.add(botonBorrar);


        return jpTexto;


    }

    private Component crearPanelTipo(){

        JPanel jpBusqueda=new JPanel();

        radioButtonCiudad = new JRadioButton("Ciudad");
        radioButtonCiudad.addActionListener(new CambioBoton());

        radioButtonCoordenadas= new JRadioButton("Coordenadas");
        radioButtonCoordenadas.addActionListener(new CambioBoton());



        ButtonGroup tipoInformacion = new ButtonGroup();
        tipoInformacion.add(radioButtonCiudad);
        tipoInformacion.add(radioButtonCoordenadas);



        jpBusqueda.add(radioButtonCiudad);
        jpBusqueda.add(radioButtonCoordenadas);


        return jpBusqueda;


    }


    private Component crearPanelMostrar() {
        JPanel jpMostrar = new JPanel();

        JLabel jlFavoritos=new JLabel("Lista favoritos");
        jlListaFavoritos = new JTextArea("");

        JLabel jlActual=new JLabel("Tiempo actual");
        jlActualFavoritos = new JTextArea("");

        JLabel jlPrediccion=new JLabel("Predicción");
        jlPrediccionFavoritos = new JTextArea("");

        jpMostrar.add(jlFavoritos);
        jpMostrar.add(jlListaFavoritos);
        jpMostrar.add(jlActual);
        jpMostrar.add(jlActualFavoritos);

        jpMostrar.add(jlPrediccion);

        jpMostrar.add(jlPrediccionFavoritos);


        return jpMostrar;
    }

    private Component crearPanelAtras(){

        JPanel jp=new JPanel();

        botonBuscar = new Boton("<< Atrás", new SeccionBuscar());

        jp.add(botonBuscar);

        return jp;


    }

    private class Añadir implements ActionListener {
        //@Override
        public void actionPerformed(ActionEvent e) {
            controlador.añadirFavoritos(getVista());
        }
    }

    private class Borrar implements ActionListener {
        //@Override
        public void actionPerformed(ActionEvent e) {
            controlador.borrarFavoritos(getVista());
        }
    }

    private class SeccionBuscar implements ActionListener {
        //@Override
        public void actionPerformed(ActionEvent e) {
            Vista seecionBuscar = new Vista();
            seecionBuscar.setControlador(controlador);
            seecionBuscar.setModelo(modelo);
            seecionBuscar.ejecutar();
            ventana.dispose();

        }
    }

    public void AñadidoCorrectamente(String cadena){
        JDialog jdResultado = new JDialog(emergente, true);
        JPanel jpGeneral = new JPanel();
        jpGeneral.setLayout(new BoxLayout(jpGeneral, BoxLayout.Y_AXIS));

        JPanel jpMensaje = new JPanel();
        JLabel jlMensaje;


        jlMensaje = new JLabel(cadena+" se ha añadido correctamente ");


        jpMensaje.add(jlMensaje);
        jpGeneral.add(jpMensaje);

        jdResultado.getContentPane().add(jpGeneral);

        jdResultado.pack();
        jdResultado.setResizable(false);
        jdResultado.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdResultado.setVisible(true);
    }

    public void BorradoCorrectamente(String cadena){
        JDialog jdResultado = new JDialog(emergente, true);
        JPanel jpGeneral = new JPanel();
        jpGeneral.setLayout(new BoxLayout(jpGeneral, BoxLayout.Y_AXIS));

        JPanel jpMensaje = new JPanel();
        JLabel jlMensaje;


        jlMensaje = new JLabel(cadena+" se ha borrado correctamente ");


        jpMensaje.add(jlMensaje);
        jpGeneral.add(jpMensaje);

        jdResultado.getContentPane().add(jpGeneral);

        jdResultado.pack();
        jdResultado.setResizable(false);
        jdResultado.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdResultado.setVisible(true);
    }

    public void AñadirExistente(String cadena){
        JDialog jdResultado = new JDialog(emergente, true);
        JPanel jpGeneral = new JPanel();
        jpGeneral.setLayout(new BoxLayout(jpGeneral, BoxLayout.Y_AXIS));

        JPanel jpMensaje = new JPanel();
        JLabel jlMensaje;


        jlMensaje = new JLabel(cadena+" ya existe en favoritos ");


        jpMensaje.add(jlMensaje);
        jpGeneral.add(jpMensaje);

        jdResultado.getContentPane().add(jpGeneral);

        jdResultado.pack();
        jdResultado.setResizable(false);
        jdResultado.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdResultado.setVisible(true);
    }

    public void BorrarExistente(String cadena){
        JDialog jdResultado = new JDialog(emergente, true);
        JPanel jpGeneral = new JPanel();
        jpGeneral.setLayout(new BoxLayout(jpGeneral, BoxLayout.Y_AXIS));

        JPanel jpMensaje = new JPanel();
        JLabel jlMensaje;


        jlMensaje = new JLabel(cadena+" no existe en favoritos ");


        jpMensaje.add(jlMensaje);
        jpGeneral.add(jpMensaje);

        jdResultado.getContentPane().add(jpGeneral);

        jdResultado.pack();
        jdResultado.setResizable(false);
        jdResultado.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdResultado.setVisible(true);
    }

    private  void guardar() {
        ObjectOutputStream oos=null;
        System.out.println("Voy a guardar datos");
        try {
            try {
                FileOutputStream fos = new FileOutputStream("baseDatos.bin");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(modelo);
            }
            finally {
                System.out.println("Datos guardados");
                oos.close();
            }
        }
        catch(FileNotFoundException exc) {
            System.out.println("El fichero no existe.");
            exc.printStackTrace();
        }
        catch(IOException exc) {
            exc.printStackTrace();
        }
    }

    private class EscuchadorCerrarVentana extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            JDialog preguntarSalida = new JDialog(ventana, true);
            Container contenedor = preguntarSalida.getContentPane();

            JPanel jpGeneral = new JPanel();
            jpGeneral.setLayout(new BoxLayout(jpGeneral, BoxLayout.Y_AXIS));

            JPanel jpMensaje = new JPanel();
            JLabel jlMensaje = new JLabel("Desea guardar los cambios?");
            jpMensaje.add(jlMensaje);

            JPanel jpSiNo = new JPanel();
            Boton jbSi = new Boton("Si", new Cerrar());
            Boton jbNo = new Boton("No", new Cancelar());
            jpSiNo.add(jbSi);
            jpSiNo.add(jbNo);

            jpGeneral.add(jpMensaje);
            jpGeneral.add(jpSiNo);

            contenedor.add(jpGeneral);

            preguntarSalida.pack();
            preguntarSalida.setResizable(false);
            preguntarSalida.setLocationRelativeTo(null);
            preguntarSalida.setVisible(true);

        }
        private class Cancelar implements ActionListener {
            //@Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
            }
        }
    }

    private class Cerrar implements ActionListener {
        //@Override
        public void actionPerformed(ActionEvent e) {
            guardar();
            ventana.dispose();
        }
    }

    private class CambioBoton implements ActionListener {
        //@Override
        public void actionPerformed(ActionEvent arg0) {
            if((radioButtonCiudad.isSelected() || radioButtonCoordenadas.isSelected())) {
                botonAñadir.setEnabled(true);
                botonBorrar.setEnabled(true);
            }
        }
    }

    public String getTipoBusqueda() {
        if(radioButtonCiudad.isSelected()) {
            return "Ciudad";
        } else {
            return "Coordenada";
        }
    }

    public void setJlListaFavoritos(String resultado){
        this.jlListaFavoritos.setText(resultado);
    }

    public void setJlActualFavoritos(String resultado){
        this.jlActualFavoritos.setText(resultado);
    }

    public void setJlPrediccionFavoritos(String resultado){
        this.jlPrediccionFavoritos.setText(resultado);
    }

    //@Override
    public void setControlador(Aplicacion controlador) {
        this.controlador = controlador;

    }

    //@Override
    public void setModelo(BaseDatos modelo) {
        this.modelo = modelo;

    }

    //@Override
    public VistaFavoritos getVista() {
        return this;
    }

}
