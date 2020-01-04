package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.*;

import Controller.Aplicacion;
import Model.BaseDatos;

public class Vista implements InterfaceVista, Serializable{

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
    private Boton botonBuscar, botonAtras;
    private JTextField jtfBuscar;
    private JTextArea jlResultado;
    private ButtonGroup tipoInformacion;
    private JRadioButton radioButtonCiudad, radioButtonCoordenadas, radioButtonBasica, radioButtonDetallada, radioButtonActual, radioButtonPrediccion;

    public Vista() {
        ventana = new JFrame("Servicio Metereológico");
        contenedor = ventana.getContentPane();
        panelGeneral = new JPanel();
        panelGeneral.setLayout(new GridLayout(6, 2, 10, 10));
    }

    //@Override
    public void ejecutar() {

        panelGeneral.add(crearPanelBusqueda());
        panelGeneral.add(crearPanelTipoConsulta());
        panelGeneral.add(crearPanelTipoInformacion());
        panelGeneral.add(crearPanelBuscar());
        panelGeneral.add(crearPanelMostrar());
        panelGeneral.add(crearPanelAtras());
        contenedor.add(panelGeneral);

        ventana.addWindowListener(new EscuchadorCerrarVentana());
        ventana.pack();
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        cargar();
        //crearJDConArchivo();
    }


    private void crearJDConArchivo() {
        emergente = new JDialog(ventana, true);
        Container contenedor = emergente.getContentPane();

        JPanel jpGeneral = new JPanel();
        jpGeneral.setLayout(new BoxLayout(jpGeneral, BoxLayout.Y_AXIS));

        JPanel jpMensaje = new JPanel();

        JLabel jlMensaje = new JLabel("Programa listo para ser usado.");
        jpMensaje.add(jlMensaje);

        JPanel jpAceptar = new JPanel();

        Boton jbOk = new Boton("OK", new Aceptar());
        jpAceptar.add(jbOk);

        jpGeneral.add(jpMensaje);
        jpGeneral.add(jpAceptar);

        contenedor.add(jpGeneral);

        emergente.pack();
        emergente.setResizable(false);
        emergente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        emergente.setLocationRelativeTo(null);
        emergente.setVisible(true);
    }

    //@SuppressWarnings("unchecked")
    private void cargar()
    {
        //System.out.println("Voy a cargar datos");
        ObjectInputStream ois = null;
        try{
            try {
                FileInputStream fis = new FileInputStream("baseDatos.bin");
                ois = new ObjectInputStream(fis);
                BaseDatos modelo2;

                modelo2 = (BaseDatos)ois.readObject();
                modelo.setCiudadesActualBD(modelo2.getCiudadesActualBD());
                modelo.setCoordenadasActualBD(modelo2.getCoordenadasActualBD());
                modelo.setCiudadesPrediccionBD(modelo2.getCiudadesPrediccionBD());
                modelo.setCoordenadasPrediciconBD(modelo2.getCoordenadasPrediciconBD());
                modelo.setFechasBusquedaCiudadBD (modelo2.getFechasBusquedaCiudadBD());
                modelo.setFechasBusquedaCoordenadas (modelo2.getFechasBusquedaCoordenadaDB());
                modelo.setFechasPrediccionCiudadBD (modelo2.getFechasPrediccionCiudadBD());
                modelo.setFechasPrediccionCoordenadas (modelo2.getFechasPrediccionCoordenadaDB ());
                modelo.setCiudadesFavoritas(modelo2.getCiudadesFavoritas());
                modelo.setCoordenadasFavoritas(modelo2.getCoordenadasFavoritas());
                //falta uno





            }
            finally {
                //System.out.println("Datos cargados");
                if(ois != null) ois.close();
            }
        }
        catch(FileNotFoundException exc) {
            //System.err.println("Fichero de datos no existe. Se crea una nueva base de datos.");

        }
        catch(IOException exc) {
            exc.printStackTrace();
        }
        catch(ClassNotFoundException exc) {
            exc.printStackTrace();
        }

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

    private Component crearPanelBusqueda(){

        JPanel jpBusqueda=new JPanel();

        JLabel jlBusqueda=new JLabel("Buscar");
        jtfBuscar=new JTextField(10);

        radioButtonCiudad = new JRadioButton("Ciudad");
        radioButtonCiudad.addActionListener(new CambioBoton());


        radioButtonCoordenadas= new JRadioButton("Coordenadas");
        radioButtonCoordenadas.addActionListener(new CambioBoton());



        ButtonGroup tipoInformacion = new ButtonGroup();
        tipoInformacion.add(radioButtonCiudad);
        tipoInformacion.add(radioButtonCoordenadas);



        jpBusqueda.add(jlBusqueda);
        jpBusqueda.add(jtfBuscar);
        jpBusqueda.add(radioButtonCiudad);
        jpBusqueda.add(radioButtonCoordenadas);


        return jpBusqueda;


    }


    //Para saber si quieres información detallada o básica
    private Component crearPanelTipoInformacion(){

        JPanel jpTipoInformacion=new JPanel();

        JLabel jlTipoInformacion=new JLabel("Información");

        radioButtonBasica = new JRadioButton("Básica");
        radioButtonBasica.addActionListener(new CambioBoton());

        radioButtonDetallada= new JRadioButton("Detallada");
        radioButtonDetallada.addActionListener(new CambioBoton());


        tipoInformacion = new ButtonGroup();
        tipoInformacion.add(radioButtonBasica);
        tipoInformacion.add(radioButtonDetallada);

        jpTipoInformacion.add(jlTipoInformacion);
        jpTipoInformacion.add(radioButtonBasica);
        jpTipoInformacion.add(radioButtonDetallada);

        return jpTipoInformacion;


    }

    private Component crearPanelTipoConsulta(){

        JPanel jpTipoConsulta=new JPanel();

        JLabel jlTipoConsulta=new JLabel("Consulta");

        radioButtonActual = new JRadioButton("Actual");
        radioButtonActual.addActionListener(new CambioBoton());
        radioButtonPrediccion = new JRadioButton("Preddición");
        radioButtonPrediccion.addActionListener(new CambioBoton());


        ButtonGroup tipoConsulta = new ButtonGroup();
        tipoConsulta.add(radioButtonActual);
        tipoConsulta.add(radioButtonPrediccion);

        jpTipoConsulta.add(jlTipoConsulta);
        jpTipoConsulta.add(radioButtonActual);
        jpTipoConsulta.add(radioButtonPrediccion);


        return jpTipoConsulta;

    }

    private Component crearPanelBuscar() {
        JPanel jpBuscar = new JPanel();
        botonBuscar = new Boton("Buscar", new Buscar());
        botonBuscar.setEnabled(false);

        jpBuscar.add(botonBuscar);
        return jpBuscar;
    }

    private Component crearPanelMostrar() {
        JPanel jpMostrar = new JPanel();

        jlResultado = new JTextArea("",80,40);
        jpMostrar.add(jlResultado);

        return jpMostrar;
    }

    private Component crearPanelAtras(){

        JPanel jp=new JPanel();

        botonAtras = new Boton("Favoritos", new Favoritos());

        jp.add(botonAtras);

        return jp;

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

    private class Aceptar implements ActionListener {
        //@Override
        public void actionPerformed(ActionEvent e) {
            emergente.dispose();
        }
    }

    private class Buscar implements ActionListener {
        //@Override
        public void actionPerformed(ActionEvent e) {
            String res= controlador.getTiempo(getVista());
            System.out.println("Info: "+res);
            setTexto(res);


        }
    }

    private class Favoritos implements ActionListener {
        //@Override
        public void actionPerformed(ActionEvent e) {
            VistaFavoritos secionBuscar = new VistaFavoritos();
            secionBuscar.setControlador(controlador);
            secionBuscar.setModelo(modelo);
            secionBuscar.ejecutar();
            ventana.dispose();
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
            if((radioButtonCiudad.isSelected() || radioButtonCoordenadas.isSelected()) && ((radioButtonActual.isSelected() && (radioButtonBasica.isSelected() || radioButtonDetallada.isSelected())) || radioButtonPrediccion.isSelected() )) {
                botonBuscar.setEnabled(true);
            }
            else {
                botonAtras.setEnabled(false);
            }
            if (radioButtonPrediccion.isSelected()){
                tipoInformacion.clearSelection();
                radioButtonBasica.setEnabled(false);
                radioButtonDetallada.setEnabled(false);

            }
            else{
                radioButtonBasica.setEnabled(true);
                radioButtonDetallada.setEnabled(true);
            }
        }
    }

    public void ciudadEncontrada(String res){
        JDialog jdResultado = new JDialog(emergente, true);
        JPanel jpGeneral = new JPanel();
        jpGeneral.setLayout(new BoxLayout(jpGeneral, BoxLayout.Y_AXIS));

        JPanel jpMensaje = new JPanel();
        JLabel jlMensaje;


        jlMensaje = new JLabel(res);


        jpMensaje.add(jlMensaje);
        jpGeneral.add(jpMensaje);

        jdResultado.getContentPane().add(jpGeneral);

        jdResultado.pack();
        jdResultado.setResizable(false);
        jdResultado.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdResultado.setVisible(true);
    }

    public void formatoCoordenadasIncorrecto(){
        JDialog jdResultado = new JDialog(emergente, true);
        JPanel jpGeneral = new JPanel();
        jpGeneral.setLayout(new BoxLayout(jpGeneral, BoxLayout.Y_AXIS));

        JPanel jpMensaje = new JPanel();
        JLabel jlMensaje;


        jlMensaje = new JLabel("Formato de coordenadas incorrecto, debe ser latitud, longitud");


        jpMensaje.add(jlMensaje);
        jpGeneral.add(jpMensaje);

        jdResultado.getContentPane().add(jpGeneral);

        jdResultado.pack();
        jdResultado.setResizable(false);
        jdResultado.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdResultado.setVisible(true);
    }

    public void ciudadNoEncontrada(String ciudad){
        JDialog jdResultado = new JDialog(emergente, true);
        JPanel jpGeneral = new JPanel();
        jpGeneral.setLayout(new BoxLayout(jpGeneral, BoxLayout.Y_AXIS));

        JPanel jpMensaje = new JPanel();
        JLabel jlMensaje;


        jlMensaje = new JLabel("No se ha encontrado la ciudad "+ciudad);


        jpMensaje.add(jlMensaje);
        jpGeneral.add(jpMensaje);

        jdResultado.getContentPane().add(jpGeneral);

        jdResultado.pack();
        jdResultado.setResizable(false);
        jdResultado.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdResultado.setVisible(true);
    }

    public void coordenadasNoEncontradas(String coordenadas){
        JDialog jdResultado = new JDialog(emergente, true);
        JPanel jpGeneral = new JPanel();
        jpGeneral.setLayout(new BoxLayout(jpGeneral, BoxLayout.Y_AXIS));

        JPanel jpMensaje = new JPanel();
        JLabel jlMensaje;


        jlMensaje = new JLabel("No se han encontrado las coordenadas "+coordenadas);


        jpMensaje.add(jlMensaje);
        jpGeneral.add(jpMensaje);

        jdResultado.getContentPane().add(jpGeneral);

        jdResultado.pack();
        jdResultado.setResizable(false);
        jdResultado.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jdResultado.setVisible(true);
    }


    public String getBusqueda(){
         return jtfBuscar.getText();
    }

    public String getTipoBusqueda() {
        if(radioButtonCiudad.isSelected()) {
            return "Ciudad";
        } else {
            return "Coordenada";
        }
    }

    public String getTipoConsulta() {
        if(radioButtonActual.isSelected()) {
            return "Actual";
        } else {
            return "Prediccion";
        }
    }

    public String getTipoInformacion() {
        if(radioButtonBasica.isSelected()) {
            return "Basica";
        } else {
            return "Detallada";
        }
    }

    public void setTexto(String resultado){
        this.jlResultado.setText(resultado);
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
    public Vista getVista() {
        return this;
    }


}

