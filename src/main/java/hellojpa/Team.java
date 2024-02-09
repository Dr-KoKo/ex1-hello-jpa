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
    @OneToMany
    @JoinColumn(name = "team_id")
    private List<Member> members = new ArrayList<>();

    protected Team() {
    }

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

//    public void addMember(Member member) {
//        member.setTeam(this);
//        this.members.add(member);
//    }

    public void addMember(Member member) {
        this.members.add(member);
    }
}
