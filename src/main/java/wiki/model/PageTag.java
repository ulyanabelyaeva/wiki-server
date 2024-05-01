package wiki.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.sql.Timestamp;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "page_tag")
public class PageTag {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "page_id", nullable = false)
    private Page page;

    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

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

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Timestamp getDbCreatedAt() {
        return dbCreatedAt;
    }

    public Timestamp getDbUpdatedAt() {
        return dbUpdatedAt;
    }

    @Override
    public String toString() {
        return "PageTag{" +
                "id=" + id +
                ", page.id=" + page.getId() +
                ", tag.id=" + tag.getId() +
                '}';
    }
}
