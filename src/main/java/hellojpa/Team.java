package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    protected Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void addMember(Member member) {
        member.setTeam(this);
        this.members.add(member);
    }
}
