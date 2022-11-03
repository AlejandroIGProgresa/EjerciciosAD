package hibernate.ejemplo;

import hibernate.ejemplo.modelos.Alumno;
import hibernate.ejemplo.modelos.Curso;
import hibernate.ejemplo.modelos.Equipo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Alumno alumno = new Alumno("Edu", "Sanz", "12345678H");
        Curso curso = new Curso("DAM", "POR CULEROS", 'C');
        Equipo equipo = new Equipo("Apple", "iMac");

        alumno.setEquipo(equipo);
        alumno.setCurso(curso);

        curso.getAlumnos().add(alumno);
        equipo.setAlumno(alumno);

        // crea la variable que puede conectar
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        // variable de la conexion
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        // create (insert)
        session.persist(curso);
        session.persist(equipo);

        /*
        // read
        Alumno a = session.get(Alumno.class, 3);
        System.out.println(a.toString());

        // update
        a.setNombre("Eduard");
        session.merge(a);

        // delete
        session.remove(a);


         */
        
        session.getTransaction().commit();
        session.close();
    }
}
