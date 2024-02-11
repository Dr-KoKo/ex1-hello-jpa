package hellojpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class AddressEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Address address;

    protected AddressEntity() {
    }

    public AddressEntity(String city, String street, String zipcode) {
        this.address = new Address(city, street, zipcode);
    }
}
