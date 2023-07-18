package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member findMember1 = em.find(Member.class, 101L);
            findMember1.setName("ZZZ");

//            em.persist(findMember1);

            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
