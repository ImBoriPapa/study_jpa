
import entity.Student;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;


@Slf4j
public class JpaMain {

    public static String id = "";

    public static void insert() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("study-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Student student1 = new Student("김학생");
            id = student1.getId();
            em.persist(student1);
            transaction.commit();
        } catch (Exception e) {

        } finally {
            em.close();
        }
        emf.close();
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("study-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        insert();

        try {
            transaction.begin();
            em.clear();
            log.info("[TRANSACTION BEGIN]");

            log.info("[FIND QUERY START]");
            Student student = em.find(Student.class, id);

            log.info("[FIND QUERY FINISH]");
            student.setName("수정된 학생 이름");

            log.info("[UPDATE QUERY START]");
            transaction.commit();
            log.info("[UPDATE QUERY FINISH]");

        } catch (Exception e) {
            log.info("ERROR= {}", e.getMessage());
            transaction.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
