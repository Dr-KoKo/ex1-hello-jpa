package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member("member1");
            Member member2 = new Member("member2");

            Team team = new Team("teamA");
            team.addMember(member1);
            team.addMember(member2);
            em.persist(team);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            findTeam.getMembers().remove(0);

            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
