package xml.xstream;

import com.thoughtworks.xstream.XStream;
import xml.modelos.Empleado;

public class Main {
    public static void main(String[] args) {
        Empleado empleado = new Empleado(1,20, "Garcia",2456.45f);

        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[]{
                "models.*"
        });
        String xml = xStream.toXML(empleado);
        System.out.println(xml);

        Empleado empleado1 = new Empleado();
    }
}
