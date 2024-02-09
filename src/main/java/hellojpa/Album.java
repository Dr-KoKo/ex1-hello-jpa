package hellojpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;

    protected Album() {
    }

    public Album(String name, int price, String artist) {
        super(name, price);
        this.artist = artist;
    }
}
