package xml.escritura;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        //Creo un elemento
        Element raiz =  document.createElement("estudiantes");
        //Se lo enchufo al padre
        document.appendChild(raiz);

        //Creamos primer estudiante
        Element estudia1 = document.createElement("estudiante");
        raiz.appendChild(estudia1);

        Attr dn1 = document.createAttribute("dni");
        dn1.setValue("12345678H");
        estudia1.setAttributeNode(dn1);
        Element nombre1 = document.createElement("nombre");
        estudia1.appendChild(nombre1);
        nombre1.setTextContent("Yeffry");
        Element nota1 = document.createElement("nota");
        estudia1.appendChild(nota1);
        nota1.setTextContent("4.5");
        // creamos un traductor de elements a dom
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer optimus = tf.newTransformer();
        DOMSource ds = new DOMSource(document);

        // organizar documento
        optimus.setOutputProperty(OutputKeys.INDENT,"yes");

        StreamResult result = new StreamResult(new File("estudiantes.xml"));
        optimus.transform(ds, result);
    }
}
