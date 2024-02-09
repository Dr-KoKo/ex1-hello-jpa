package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    private String username;
//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> products = new ArrayList<>();
//    @ManyToOne
//    @JoinColumn(name = "team_id")
//    private Team team;
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public Team getTeam() {
//        return team;
//    }

//    public void setTeam(Team team) {
//        this.team = team;
//    }

//    public void join(Team team) {
//        this.setTeam(team);
//        team.getMembers().add(this);
//    }
}
