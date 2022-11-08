package xml.lectura;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xml.modelos.Empleado;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        File fEmpXML = new File("Empleados.xml");
        // Parsear fichero XML -> NO LEO PASO A PASO EL FICHERO
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        // Ahora cargo dentro de una variable Documento (DOM)
        Document document = db.parse(fEmpXML);
        // Limpio los nodos para una mejor visualización
        document.getDocumentElement().normalize();

        System.out.println("Raíz del documento: " + document.getDocumentElement().getNodeName());

        // NodeList nodos = document.getChildNodes();
        NodeList nodos = document.getElementsByTagName("empleado");

        for (int i = 0; i < nodos.getLength(); i++) {
            Node nodo = nodos.item(i); // Extraigo el nodo
            System.out.println("Elemento: " + nodo.getNodeName());
            // Compruebo que tienen hijos
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element empleado = (Element) nodo;
                // Atributos
                String dni = empleado.getAttribute("dni");
                String apellido = empleado.getElementsByTagName("apellido").item(0).getTextContent();
                int id = Integer.parseInt(empleado.getElementsByTagName("id").item(0).getTextContent());
                int dep = Integer.parseInt(empleado.getElementsByTagName("dep").item(0).getTextContent());
                float salario = Float.parseFloat(empleado.getElementsByTagName("salario").item(0).getTextContent());

                Empleado emp = new Empleado(id, dep, apellido, salario);
                System.out.println(emp);
            }

            
        }
    }
}