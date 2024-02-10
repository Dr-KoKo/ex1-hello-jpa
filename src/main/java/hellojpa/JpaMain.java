package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Hibernate;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member("member1");
            Member member2 = new Member("member2");
            Member member3 = new Member("member3");
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            em.flush();
            em.clear();

            Member findMember1 = em.find(Member.class, member1.getId());
            Member findMember2Ref = em.getReference(Member.class, member2.getId());

            logic(findMember1, findMember2Ref);

            Member findMember1Ref = em.getReference(Member.class, member1.getId());
            System.out.println("findMember1Ref.getClass() = " + findMember1Ref.getClass());
            System.out.println("findMember1 == findMember1Ref : " + (findMember1 == findMember1Ref));

            Member findMember2 = em.find(Member.class, member2.getId());
            System.out.println("findMember2.getClass() = " + findMember2.getClass());
            System.out.println("findMember2 == findMember2Ref : " + (findMember2 == findMember2Ref));


            Member findMember3Ref = em.getReference(Member.class, member3.getId());

            // 프록시 인스턴스의 초기화 여부 확인
            boolean isLoaded = emf.getPersistenceUnitUtil().isLoaded(findMember3Ref);
            System.out.println("isLoaded = " + isLoaded);

            // 프록시 강제 초기화
//            findMember3Ref.getUsername();
            Hibernate.initialize(findMember3Ref);
            isLoaded = emf.getPersistenceUnitUtil().isLoaded(findMember3Ref);
            System.out.println("isLoaded = " + isLoaded);

            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(Member m1, Member m2) {
        System.out.println("m1 == m2 : " + (m1 == m2));
        System.out.println("m1 instanceof Member : " + (m1 instanceof Member));
        System.out.println("m2 instanceof Member : " + (m2 instanceof Member));
    }
}
