package hellojpa;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    protected Member() {
    }

    public Member(Long id, String username, Team team) {
        this.id = id;
        this.username = username;
        this.team = team;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

//    public void join(Team team) {
//        this.setTeam(team);
//        team.getMembers().add(this);
//    }
}
