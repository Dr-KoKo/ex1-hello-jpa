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
            System.out.println("findMember1.id = " + findMember1.getId());
            System.out.println("findMember1.name = " + findMember1.getName());

            System.out.println("=== BEFORE ===");

            Member findMember2 = em.find(Member.class, 101L);
            System.out.println("findMember2.id = " + findMember2.getId());
            System.out.println("findMember2.name = " + findMember2.getName());

            System.out.println("=== AFTER ===");

            System.out.println("findMember1==findMember2?");
            System.out.println(findMember1==findMember2);

            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
