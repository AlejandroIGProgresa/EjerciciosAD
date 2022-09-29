package binarios;

import java.io.*;

public class Ejercicio1Bin {
    public static void main(String[] args) {
        File f1 = new File("listado.txt");
        File f2 = new File("listado2.dat");
        int saldo = 0, c1 = 0, c2 = 0, num = 0;
        try (FileReader fr = new FileReader(f1)){
            BufferedReader br = new BufferedReader(fr);
            String linea;
            FileOutputStream fos = new FileOutputStream(f2);
            DataOutputStream dos = new DataOutputStream(fos);
            FileInputStream fis = new FileInputStream(f2);
            DataInputStream dis = new DataInputStream(fis);
            while ((linea = br.readLine()) != null){
                dos.writeInt(Integer.parseInt(linea));
                num = (dis.readInt());
                if (num <= 0){
                    c1++;
                }
                else c2++;
                saldo += num;
            }
            dos.close();
            fos.close();
            fr.close();
            br.close();
            dis.close();
            fis.close();
            System.out.println("Saldo: " + saldo + ", cargos: " + c1 + ", abonos: " + c2);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (EOFException e) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
