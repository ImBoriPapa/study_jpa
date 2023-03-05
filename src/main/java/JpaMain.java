
import entity.Student;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;


@Slf4j
public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("study-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        try {
            tx.begin();

            Student student1 = new Student("김학생");
            // 영속화
            em.persist(student1);

            Student student = em.find(Student.class, student1.getId());
            log.info("영속 후 데이터베이스에 저장 전 캐시에 저장된 값 확인: cache contains student1={}", student != null);

            log.info("커밋 전");
            tx.commit();
            log.info("커밋 완료");
        } catch (Exception e) {
            log.info("ERROR= {}", e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
