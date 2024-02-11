package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address homeAddress = new Address("city", "street", "zipcode");
            Period workPeriod = new Period(LocalDate.now(), LocalDate.now().plusDays(2L));

            Member member1 = new Member("member", workPeriod, homeAddress);
            em.persist(member1);
            Member member2 = new Member("member", workPeriod, homeAddress);
            em.persist(member2);

            member1.setHomeAddress(new Address("newCity", "newStreet", "newZipcode"));

            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
