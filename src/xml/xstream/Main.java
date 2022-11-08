package xml.xstream;

import com.thoughtworks.xstream.XStream;
import xml.modelos.Empleado;

public class Main {
    public static void main(String[] args) {
        Empleado empleado = new Empleado(1,20, "Garcia",2456.45f);

        XStream xstream = new XStream();
        xstream.allowTypesByWildcard(new String[]{
                "xml.modelos.*"
        });
        String xml = xstream.toXML(empleado);
        System.out.println(xml);

        Empleado empleado2 = new Empleado();
        xstream.fromXML(xml, empleado2);
        System.out.println(empleado2.toString());
    }
}
