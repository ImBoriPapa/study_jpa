
import entity.Student;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Slf4j
public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("study-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        try {
            tx.begin();



            Student student1 = new Student("김학생");


            Student student2 = new Student("이학생");


            em.persist(student1);
            em.persist(student2);


            List<Student> students = em.createQuery("select s from Student s", Student.class).getResultList();

            students.forEach(s->log.info("student id= {}, name= {} ", s.getId(), s.getName()));


            tx.commit();
        } catch (Exception e) {
            log.info("ERROR= {}",e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
