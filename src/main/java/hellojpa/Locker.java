package hellojpa;

import jakarta.persistence.*;

@Entity
public class Locker {
    @Id
    private Long id;
    private String name;
}
