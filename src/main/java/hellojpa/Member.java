package hellojpa;

import jakarta.persistence.*;

@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;
    @Embedded
    private Period workPeriod;
    @Embedded
    private Address homeAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "work_city")),
            @AttributeOverride(name = "street", column = @Column(name = "work_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "work_zipcode"))
    })
    private Address workAddress;

    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, Period workPeriod, Address homeAddress) {
        this.username = username;
        this.workPeriod = workPeriod;
        this.homeAddress = homeAddress;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Team getTeam() {
        return team;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void join(Team team) {
        this.setTeam(team);
        team.getMembers().add(this);
    }
}
