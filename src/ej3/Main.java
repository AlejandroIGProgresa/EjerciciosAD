package ej3;

import ej3.modelo.Alumno;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Alumno> alumnosList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int opcion = menu(sc);

        switch (opcion){
            case 1:
                crearAlumno(alumnosList, sc);
                break;
            case 2:
                try {
                    guardarAlumnos(alumnosList, sc);
                } catch (ParserConfigurationException e) {
                    throw new RuntimeException(e);
                } catch (TransformerException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                alumnosList = cargarAlumnos();
                break;
            case 4:
                break;
        }

    }

    private static void guardarAlumnos(ArrayList<Alumno> alumnosList, Scanner sc) throws ParserConfigurationException, TransformerException {
        System.out.println("Nombre del fichero:");
        String nombreFichero = sc.nextLine() + ".xml";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.newDocument();

        //PARA LA RAÏZ DEL DOCUMENTO

        Element raiz = doc.createElement("alumnos");
        doc.appendChild(raiz);

        for (Alumno a:
             alumnosList) {
            Element alumno = doc.createElement("alumno");
            raiz.appendChild(alumno);


            Attr attrId = doc.createAttribute("id_estudiante");
            attrId.setValue(a.getId());
            alumno.setAttributeNode(attrId);

            Element nombre = doc.createElement("nombre");
            nombre.setTextContent(a.getNombre());
            alumno.appendChild(nombre);

            Element apellido = doc.createElement("apellido");
            nombre.setTextContent(a.getApellido());
            alumno.appendChild(apellido);

            Element asignaturas = doc.createElement("nombre");
            nombre.setTextContent(String.valueOf(a.getAsignaturas()));
            alumno.appendChild(asignaturas);

        }

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer optimus = tf.newTransformer();
        DOMSource ds = new DOMSource(doc);

        optimus.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult sr = new StreamResult(nombreFichero);

        optimus.transform(ds, sr);

    }

    private static void crearAlumno(ArrayList<Alumno> alumnosList, Scanner sc) {
        System.out.println("ID: ");
        String id = sc.nextLine();
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Apellidos:");
        String apellidos = sc.nextLine();
        System.out.println("Asignaturas: ");
        int asignaturas = sc.nextInt();
        sc.nextLine();

        alumnosList.add(new Alumno(id, nombre, apellidos, asignaturas));
    }


    private static int menu(Scanner sc){
        int opcion;
        do{

            System.out.println("1. Introducir alumnnos");
            System.out.println("2. Guardar alumnnos");
            System.out.println("3. Cargar alumnnos");
            System.out.println("4. Salir");
            opcion = sc.nextInt();
        } while (opcion < 1 || opcion > 4);
        return opcion;
    }

}
