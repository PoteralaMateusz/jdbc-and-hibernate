package pl.sda.orange2.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.orange2.entity.Student;
import pl.sda.orange2.hibernate.util.HibernateUtil;

public class HibernateTest {

    public static void main(String[] args) {

        Student student = new Student("Ramesh", "Fadatare", "rameshfadatare@javaguides.com");
        Student student1 = new Student("John", "Cena", "john@javaguides.com");

        Transaction transaction = null;
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

    }
}
