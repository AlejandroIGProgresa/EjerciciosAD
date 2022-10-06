package binarios.ej3;

import binarios.ej3.streams.MyOOS;
import ej2.EstadoPartida;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        File partidas = new File("saved.dat");

        do {
            opcion = menu(sc);
        } while (opcion != 4);

        switch (opcion){
            case 1:
                crearPartida(sc, partidas);
                break;
            case 2:
                mostrarPartidas(partidas);
                break;
            case 3:
                eliminarPartidas(sc, partidas);
                break;
            case 4:
                System.out.println("Sayonara");
                break;
            default:
                break;
        }

    }

    private static void crearPartida(Scanner sc, File partidas) {
        try {
            EstadoPartida partida = datosPartida(sc);
            ObjectOutputStream oos;
            if(partidas.exists()){
                oos = new MyOOS(new FileOutputStream(partidas, true));
            }
            else {
                oos = new ObjectOutputStream(new FileOutputStream(partidas));
            }
            oos.writeObject(partida);
            oos.close();
        } catch (InputMismatchException e){
            System.out.println("Error con los datos");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static EstadoPartida datosPartida(Scanner sc) throws InputMismatchException{
        System.out.println("Dime la vida");
        int vida = sc.nextInt();
        System.out.println("Dime la pantalla en la que estás");
        int pantalla  = sc.nextInt();

        return new EstadoPartida(vida, pantalla);
    }

    private static void mostrarPartidas(File partidas) {
        if (partidas.exists()){
            try {
                FileInputStream inputStream = new FileInputStream(partidas);
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                while (true){
                    EstadoPartida p = (EstadoPartida) ois.readObject();
                    System.out.println(p.toString());
                }
            } catch (EOFException e){
                throw new RuntimeException(e);

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static void eliminarPartidas(Scanner sc, File partidas) {
        System.out.println("Seguro?");
        String respuesta = sc.nextLine().toLowerCase().trim();
        if (respuesta.equals("si")){
            if (partidas.delete()) System.out.println("Borrada");
            else System.out.println("?");
        }
        else System.out.println("No borrado");

    }


    private static int menu(Scanner scanner) {
        int opcion;
        do {

            System.out.println("BIENVENIDO");
            System.out.println("1. Guardar Partida");
            System.out.println("2. Mostrar Partidas");
            System.out.println("3. Eliminar Partidas");
            System.out.println("4. Salir");
            try {
                opcion = scanner.nextInt();
            } catch (Exception e){
                opcion = 0;
                System.out.println("Eres bobo?");
                scanner.nextLine();
            }
        }
        while (opcion < 1 || opcion > 4);
        return opcion;
    }
}
