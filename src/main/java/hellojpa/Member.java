package hellojpa;

import jakarta.persistence.*;

@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void join(Team team) {
        this.setTeam(team);
        team.getMembers().add(this);
    }
}
