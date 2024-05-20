package wiki.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "page")
public class Page {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_sequence")
    @SequenceGenerator(
            name = "id_sequence",
            initialValue = 1_000,
            allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "directory_id")
    private Directory directory;

    @Column(name = "file_uuid")
    private UUID fileUUID = UUID.randomUUID();

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public UUID getFileUUID() {
        return fileUUID;
    }

    public void setFileUUID(UUID fileUUID) {
        this.fileUUID = fileUUID;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getDbCreatedAt() {
        return dbCreatedAt;
    }

    public Timestamp getDbUpdatedAt() {
        return dbUpdatedAt;
    }

    @Override
    public String toString() {
        Long directoryId = nonNull(directory) ? directory.getId() : null;
        return "Page{" +
                "id=" + id +
                ", owner.id=" + owner.getId() +
                ", name='" + name + '\'' +
                ", directory=" + directoryId +
                ", fileUUID=" + fileUUID +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
