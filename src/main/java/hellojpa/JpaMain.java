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
            /**
             * Insert
             */
/*
            Member member = new Member();
            member.setId(2L);
            member.setName("HelloB");

            em.persist(member);
 */

            /**
             * Select
             */
/*
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());
*/

            /**
             * Delete
             */
/*
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);
 */

            /**
             * Update
             */
/*
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");
 */

            /**
             * Selct All
             */
/*
            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
 */


            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(5)
                            .setMaxResults(8)
                                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
