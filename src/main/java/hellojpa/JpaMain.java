package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // JPQL
            List<Member> resultJPQL = em.createQuery(
                            "select m from Member m where m.username like '%kim%'",
                            Member.class)
                    .getResultList();

            // Criteria
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);
            Root<Member> m = query.from(Member.class);
            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
            List<Member> resultCriteria = em.createQuery(cq).getResultList();

            // Querydsl

            // NativeQuery
            List<Member> result = em.createNativeQuery("select member_id, city, street, zipcode, username from member", Member.class)
                    .getResultList();

            // JDBC or JDBCTemplate - JPA와 혼용시 JDBC로 쿼리하기 전에 flush 필수


            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
