package ej3;

import ej3.handlers.AlumnosSAXHandler;
import ej3.modelo.Alumno;
import ej3.modelo.Tags;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Alumno> alumnosList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            opcion = menu(sc);

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
                    try {
                        cargarAlumnos(alumnosList, sc);
                    } catch (ParserConfigurationException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (SAXException e) {
                        System.out.println("Fichero incompatible");
                    }
                    break;
                case 4:
                    try {
                        cargarAlumnosSax(alumnosList, sc);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (SAXException e) {
                        System.out.println("Error de SAX");
                        System.out.println(e.getLocalizedMessage());
                    } catch (ParserConfigurationException e) {
                        System.out.println("Error en el parser");
                        System.out.println(e.getLocalizedMessage());
                    }
                    break;
                case 5:
                    break;
            }
        } while (opcion != 5);

    }

    private static void cargarAlumnosSax(ArrayList<Alumno> alumnosList, Scanner sc) throws IOException, SAXException, ParserConfigurationException {
        System.out.println("Nombre: ");
        String fileName = sc.nextLine();
        File fichero = new File(fileName);

        SAXParserFactory saxPF = SAXParserFactory.newInstance();
        SAXParser saxP = saxPF.newSAXParser();
        AlumnosSAXHandler handler = new AlumnosSAXHandler(alumnosList);
        saxP.parse(fichero, handler);

        for (Alumno a :
        alumnosList) {
            System.out.println(a);
        }

    }

    private static void cargarAlumnos(ArrayList<Alumno> alumnosList, Scanner sc) throws ParserConfigurationException, IOException, SAXException {
        System.out.println("Nombre: ");
        String fileName = sc.nextLine();
        File fichero = new File(fileName);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(fichero);
        doc.getDocumentElement().normalize();

        Node raiz = doc.getDocumentElement().getParentNode();
        System.out.println(raiz.getLocalName());

        NodeList alumnosNodos = doc.getElementsByTagName(Tags.ALUMNO);

        for (int i = 0; i < alumnosNodos.getLength(); i++) {
            Node alumno = alumnosNodos.item(i);
            if (alumno.getNodeType() == Node.ELEMENT_NODE) {
                Element alumnoElement = (Element) alumno;
                String id = alumnoElement.getAttribute(Tags.ID);
                String nombre = alumnoElement.getElementsByTagName(Tags.NOMBRE).item(0).getTextContent();
                String apellido = alumnoElement.getElementsByTagName(Tags.APELLIDO).item(0).getTextContent();
                int asignaturas = Integer.parseInt(alumnoElement.getElementsByTagName(Tags.ASIGNATURAS).item(0).getTextContent());
                alumnosList.add(new Alumno(id, nombre,apellido, asignaturas));
            }
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
            Element alumno = doc.createElement(Tags.ALUMNO);
            raiz.appendChild(alumno);


            Attr attrId = doc.createAttribute(Tags.ID);
            attrId.setValue(a.getId());
            alumno.setAttributeNode(attrId);

            Element nombre = doc.createElement(Tags.NOMBRE);
            nombre.setTextContent(a.getNombre());
            alumno.appendChild(nombre);

            Element apellido = doc.createElement(Tags.APELLIDO);
            nombre.setTextContent(a.getApellido());
            alumno.appendChild(apellido);

            Element asignaturas = doc.createElement(Tags.ASIGNATURAS);
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
        sc.nextLine();
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
            System.out.println("4. Lectura por SAX");
            System.out.println("5. Salir");
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion < 1 || opcion > 4);
        return opcion;
    }

}
