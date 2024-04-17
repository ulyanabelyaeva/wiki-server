package wiki.model;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "directory")
public class Directory {

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

    @JoinColumn(name = "parent_directory_id")
    @ManyToOne
    private Directory parentDirectory;

    @OneToMany(mappedBy = "parentDirectory", cascade = ALL)
    private List<Directory> childDirectories = new ArrayList<>();

    @OneToMany(mappedBy = "directory", cascade = ALL)
    private List<Page> pages = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

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

    public Directory getParentDirectory() {
        return parentDirectory;
    }

    public void setParentDirectory(Directory parentDirectory) {
        this.parentDirectory = parentDirectory;
    }

    public List<Directory> getChildDirectories() {
        return childDirectories;
    }

    public void setChildDirectories(List<Directory> childDirectories) {
        this.childDirectories = childDirectories;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
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

    @Override
    public String toString() {
        Long parentDirectoryId = nonNull(parentDirectory) ? parentDirectory.getId() : null;
        List<Long> childDirectoryIds = childDirectories.stream().map(Directory::getId).toList();
        List<Long> pageIds = pages.stream().map(Page::getId).toList();

        return "Directory{" +
                "id=" + id +
                ", owner.id=" + owner.getId() +
                ", name='" + name + '\'' +
                ", parentDirectory.id=" + parentDirectoryId +
                ", childDirectory.ids=" + childDirectoryIds +
                ", pages=" + pageIds +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
