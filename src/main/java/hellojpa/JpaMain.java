package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // when
            Address homeAddress = new Address("city", "street", "zipcode");
            Member member = new Member("member", homeAddress);

            member.addFavoriteFood("치킨");
            member.addFavoriteFood("족발");
            member.addFavoriteFood("피자");

            member.addAddressHistory(new Address("oldCity1", "oldStreet1", "oldZipcode1"));
            member.addAddressHistory(new Address("oldCity2", "oldStreet2", "oldZipcode2"));

            em.persist(member);

            em.flush();
            em.clear();

            // given
            System.out.println("========== READ ==========");
            Member findMember = em.find(Member.class, member.getId());

            System.out.println("========== address ==========");
            List<Address> addressHistories = findMember.getAddressHistories();
            for (Address addressHistory : addressHistories) {
                System.out.println(addressHistory);
            }

            System.out.println("========== favorite food ==========");
            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println(favoriteFood);
            }

            System.out.println("========== UPDATE ==========");
            System.out.println("========== home address ==========");
            System.out.println("homeAddress = " + findMember.getHomeAddress().getCity());
            System.out.println("homeAddress = " + findMember.getHomeAddress().getStreet());
            System.out.println("homeAddress = " + findMember.getHomeAddress().getZipcode());
            findMember.changeHomeAddress(new Address("newCity", "newStreet", "newZipcode"));

            System.out.println("========== favorite food ==========");
            findMember.getFavoriteFoods().remove("치킨");
            findMember.getFavoriteFoods().add("한식");

            System.out.println("========== address ==========");
            findMember.getAddressHistories().remove(new Address("oldCity1", "oldStreet1", "oldZipcode1"));
            findMember.getAddressHistories().add(new Address("oldCity3", "oldStreet3", "oldZipcode3"));


            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
