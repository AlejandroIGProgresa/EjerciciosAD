package ej3.handlers;

import ej3.modelo.Alumno;
import ej3.modelo.Tags;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class AlumnosSAXHandler extends DefaultHandler {

    private Alumno alumno;
    private String data;
    private ArrayList<Alumno> alumnoArrayList;

    public AlumnosSAXHandler(ArrayList<Alumno> alumnoArrayList) {
        this.alumnoArrayList = alumnoArrayList;
        this.alumnoArrayList.clear();
    }

    /**
     * Se dispara al encontrar etiqueta de entrada
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @param attributes The attributes attached to the element.  If
     *        there are no attributes, it shall be an empty
     *        Attributes object.
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        // Estoy en un nodo objeto
        if (qName.equals(Tags.ALUMNO)) {
            alumno = new Alumno();
            alumno.setId(attributes.getValue(Tags.ID));
        }
    }

    /**
     * Se dispara al encontrar etiqueta de cierre
     * @param uri The Namespace URI, or the empty string if the
     *        element has no Namespace URI or if Namespace
     *        processing is not being performed.
     * @param localName The local name (without prefix), or the
     *        empty string if Namespace processing is not being
     *        performed.
     * @param qName The qualified name (with prefix), or the
     *        empty string if qualified names are not available.
     * @throws SAXException
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        switch (qName){
            case Tags.ALUMNO -> {
                alumnoArrayList.add(alumno);
            }
            case Tags.NOMBRE -> {
                alumno.setNombre(data);
            }
            case Tags.APELLIDO -> {
                alumno.setApellido(data);
            }
            case Tags.ASIGNATURAS -> {
                alumno.setAsignaturas(Integer.parseInt(data));

            }

        }
    }

    /**
     * Se dispara al encontrar un texto plano
     * @param ch The characters.
     * @param start The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     * @throws SAXException
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        data = new String(ch, start, length);
    }
}
