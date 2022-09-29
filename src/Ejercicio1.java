import java.io.*;

public class Ejercicio1 {
    public static void main(String[] args) {
        int saldo = 0;
        int numCargos = 0, numAbonos = 0;

        File cargos = new File("listado.txt");
        try (FileReader fr = new FileReader(cargos)) {
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null){
                if (Integer.parseInt(linea) >= 0) numCargos++;
                else numAbonos++;
                saldo += Integer.parseInt(linea);
            }
            System.out.println("Saldo: " + saldo + ", cargos: " + numCargos + ", abono: " + numAbonos);
            br.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
