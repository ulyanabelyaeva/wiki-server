package wiki.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_sequence")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "tag", cascade = ALL)
    private List<PageTag> pageTags = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "db_created_at", updatable = false)
    private Timestamp dbCreatedAt;

    @UpdateTimestamp
    @Column(name = "db_updated_at")
    private Timestamp dbUpdatedAt;

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<PageTag> getPageTags() {
        return pageTags;
    }

    public void setPageTags(List<PageTag> pageTags) {
        this.pageTags = pageTags;
    }

    public Timestamp getDbCreatedAt() {
        return dbCreatedAt;
    }

    public Timestamp getDbUpdatedAt() {
        return dbUpdatedAt;
    }

    @Override
    public String toString() {
        List<Long> pageTagIds = pageTags.stream().map(PageTag::getId).toList();

        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner.id=" + owner.getId() +
                ", pageTags=" + pageTagIds +
                '}';
    }
}
