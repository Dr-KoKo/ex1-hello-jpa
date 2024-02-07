package hellojpa;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "MBR",
        uniqueConstraints = @UniqueConstraint(name = "UNIQUE_NAME", columnNames = {"name"}))
public class Member {
    @Id
    private Long id;
    //@Column(name = "name", unique = true, nullable = false, length = 10, insertable = true, updatable = true)
    @Column(name = "name", nullable = false, length = 10, insertable = true, updatable = true)
    //@Column(columnDefinition = "varchar(100) default `EMPTY`")
    private String username;
    private Integer age;
    @Column(precision = 19)
    private BigDecimal age2;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    private LocalDate createdDate2;
    private LocalDateTime lastModifiedDate2;
    @Lob
    private String description;
    @Transient
    private int temp;

    protected Member() {
    }
}
