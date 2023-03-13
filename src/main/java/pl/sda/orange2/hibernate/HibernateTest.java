package pl.sda.orange2.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.orange2.entity.Student;
import pl.sda.orange2.hibernate.util.HibernateUtil;

import java.util.List;

public class HibernateTest {

    public static void main(String[] args) {

        Student student = new Student("Mateusz", "Poterala", "mateusz@gmail.com");
        Student student1 = new Student("Jan", "Kowalski", "jan@gmail.com");

        Transaction transaction = null;
        // add some students to database
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(student);
            session.persist(student1);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

        // gets students from database
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Student> students = session.createQuery("from Student", Student.class).getResultList();
            students.forEach(System.out::println);
        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }
}
