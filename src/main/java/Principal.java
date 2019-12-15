import Controller.Aplicacion;

import java.io.*;
import java.util.Scanner;

public class Principal implements Serializable{

    private Aplicacion bd = new Aplicacion();


    public static void main(String[] args) throws IOException {
        new Principal().Menu();
    }



    private Principal() {
        super();
    }

    private void Menu() throws IOException {
        Scanner sc=new Scanner(System.in);
        int opcion=1;
        Cargar();

        while(opcion<=5){
            System.out.println("***********************************************");
            mostrarMensaje();
            System.out.println("Escoge una opción: ");
            opcion=sc.nextInt();
            switch(opcion)
            {
                case 1:
                    bd.getTiempoCiudad();
                    break;
                case 2:
                    bd.getTiempoCoordenadas();
                    break;
                case 3:
                    bd.getPrediccionCiudad();
                    break;
                case 4:
                    bd.getPrediccionCoordenadas();
                    break;
                case 5:
                    MenuFavoritos();
                    break;
                default:
                    Guardar();
                    System.out.println("\nCerrando aplicacion. \n");
                    System.exit(1);
                    break;
            }
        }
    }

    private static void mostrarMensaje()
    {
        System.out.println("1.  Consultar el tiempo actual de una ciudad.");
        System.out.println("2.  Consultar el tiempo actual de unas coordenadas.");
        System.out.println("3.  Consultar la predicción del tiempo de una ciudad.");
        System.out.println("4.  Consultar la predicción del tiempo de unas coordenadas.");
        System.out.println("5.  Sección de favoritos.");
        System.out.println("6.  Salir de la aplicación.");
    }

    private void MenuFavoritos() throws IOException {
        Scanner sc=new Scanner(System.in);
        int opcion=1;

        while(opcion<=5){
            System.out.println("Favoritos");
            mostrarMensajeFavoritos();
            System.out.println("Escoge una opción: ");
            opcion=sc.nextInt();
            switch(opcion)
            {
                case 1:
                    bd.getFavoritos();
                    break;
                case 2:
                    bd.añadirFavorito();
                    break;
                case 3:
                    bd.borrarFavorito();
                    break;
                case 4:
                    bd.getTiempoCiudadesFavoritas();
                    break;
                case 5:
                    bd.getPrediccionCiudadesFavoritas();
                    break;
                default:
                    Menu();
                    break;
            }
        }
    }

    private static void mostrarMensajeFavoritos()
    {
        System.out.println("1.  Lista favoritos.");
        System.out.println("2.  Añadir favoritos.");
        System.out.println("3.  Eliminar favoritos.");
        System.out.println("4.  Consultar tiempo actual de lugares favoritos.");
        System.out.println("5.  Consultar predicción de lugares favoritos");
        System.out.println("6.  Menú principal.");
    }

    private void Cargar()
    {
        System.out.println("Voy a cargar datos");
        ObjectInputStream ois = null;
        try{
            try {
                FileInputStream fis = new FileInputStream("principal.bin");
                ois = new ObjectInputStream(fis);
                bd = (Aplicacion)ois.readObject();
            }
            finally {
                System.out.println("Datos cargados");
                if(ois != null) ois.close();
            }
        }
        catch(FileNotFoundException exc) {
            System.err.println("Fichero de datos no existe. Se crea una nueva base de datos.");
        }
        catch(IOException exc) {
            exc.printStackTrace();
        }
        catch(ClassNotFoundException exc) {
            exc.printStackTrace();
        }

    }

    private  void Guardar() {
        ObjectOutputStream oos=null;
        System.out.println("Voy a guardar datos");
        try {
            try {
                FileOutputStream fos = new FileOutputStream("principal.bin");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(bd);
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






}
