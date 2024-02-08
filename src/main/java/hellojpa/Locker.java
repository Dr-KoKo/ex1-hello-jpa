package hellojpa;

import jakarta.persistence.*;

@Entity
public class Locker {
    @Id
    private Long id;
    private String name;
    @OneToOne(mappedBy = "locker")
//    @OneToOne
//    @JoinColumn(name = "member_id")
    private Member member;
}
