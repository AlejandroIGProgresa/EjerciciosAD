package hibernate.ejemplo;

import hibernate.ejemplo.modelos.Alumno;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Alumno alumno = new Alumno("Edu", "Sanz", "12345678H");

        // crea la variable que puede conectar
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        // variable de la conexion
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        // create (insert)
        //session.persist(alumno);

        // read
        Alumno a = session.get(Alumno.class, 3);
        System.out.println(a.toString());

        // update
        a.setNombre("Eduard");
        session.merge(a);

        // delete
        // session.remove(a);


        session.getTransaction().commit();
        session.close();
    }
}
