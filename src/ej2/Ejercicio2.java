package ej2;

import java.io.*;

public class Ejercicio2 {
    public static void main(String[] args) {
        EstadoPartida p = new EstadoPartida(100, 7);
        try {
            guardarPartida(p);
            EstadoPartida pGuardada = recuperarPartida();
            System.out.println(pGuardada.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static void guardarPartida(EstadoPartida ep) throws IOException {
        File file = new File("partida");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.valueOf(ep.getVidaRestante()));
        bw.newLine();
        bw.write(String.valueOf(ep.getPantallaActual()));
        bw.close();
        fw.close();

    }

    static EstadoPartida recuperarPartida() throws IOException {
        File file = new File("partida");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        int vidaRes = Integer.parseInt(br.readLine());
        int pantActual = Integer.parseInt(br.readLine());
        br.close();
        fr.close();
        return new EstadoPartida(vidaRes, pantActual);
    }
}
