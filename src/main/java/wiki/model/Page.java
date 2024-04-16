package wiki.model;

import javax.persistence.*;
import java.util.UUID;

import static java.util.Objects.nonNull;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "page")
public class Page {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne
    @JoinColumn(name = "directory_id")
    private Directory directory;

    @Column(name = "source_uuid")
    private UUID sourceUUID = UUID.randomUUID();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public UUID getSourceUUID() {
        return sourceUUID;
    }

    public void setSourceUUID(UUID sourceUUID) {
        this.sourceUUID = sourceUUID;
    }

    @Override
    public String toString() {
        Long directoryId = nonNull(directory) ? directory.getId() : null;
        return "Page{" +
                "id=" + id +
                ", directory=" + directoryId +
                ", sourceUUID=" + sourceUUID +
                '}';
    }
}
