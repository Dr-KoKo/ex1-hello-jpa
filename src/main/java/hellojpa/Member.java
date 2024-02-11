package hellojpa;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Address homeAddress;
    @ElementCollection
    @CollectionTable(name = "favorite_food", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "food_name")
    private Set<String> favoriteFoods = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "address", joinColumns = @JoinColumn(name = "member_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "history_city")),
            @AttributeOverride(name = "street", column = @Column(name = "history_street")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "history_zipcode"))
    })
    @OrderColumn(name = "address_history_order")
    private List<Address> addressHistories = new ArrayList<>();

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "member_id")
//    private List<AddressEntity> addressHistories = new ArrayList<>();

    protected Member() {
    }

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, Address homeAddress) {
        this.username = username;
        this.homeAddress = homeAddress;
    }

    public void addFavoriteFood(String foodName) {
        this.favoriteFoods.add(foodName);
    }

    public void addAddressHistory(Address addressHistory) {
        this.addressHistories.add(addressHistory);
    }

    public void join(Team team) {
        this.setTeam(team);
        team.getMembers().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public List<Address> getAddressHistories() {
        return addressHistories;
    }

    public void changeHomeAddress(Address newHomeAddress) {
        this.homeAddress = newHomeAddress;
    }
}
