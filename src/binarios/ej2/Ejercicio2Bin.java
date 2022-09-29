package binarios.ej2;

import ej2.EstadoPartida;

import java.io.*;

public class Ejercicio2Bin {
    public static void main(String[] args) {
        EstadoPartida p = new EstadoPartida(100, 7);
        EstadoPartida pGuardada;
        try {
            guardarPartida(p);
            pGuardada = recuperarPartida();
            System.out.println(pGuardada.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void guardarPartida(EstadoPartida ep) throws IOException, EOFException {
        File file = new File("partida.dat");
        FileOutputStream fos = new FileOutputStream(file);
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeInt(ep.getVidaRestante());
        dos.writeInt(ep.getPantallaActual());
        dos.close();
        fos.close();
    }

    static EstadoPartida recuperarPartida() throws IOException, EOFException {
        File file = new File("partida.dat");
        FileInputStream fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);
        int vidaRes = dis.readInt();
        int pantActual = dis.readInt();
        EstadoPartida ep = new EstadoPartida(vidaRes, pantActual);
        dis.close();
        fis.close();
        return ep;
    }
}
