package wiki.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "directory")
public class Directory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @JoinColumn(name = "parent_directory_id")
    @ManyToOne
    private Directory parentDirectory;

    @OneToMany(mappedBy = "parentDirectory", cascade = ALL)
    private List<Directory> childDirectories = new ArrayList<>();

    @OneToMany(mappedBy = "directory", cascade = ALL)
    private List<Page> pages = new ArrayList<>();

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

    @Override
    public String toString() {
        Long parentDirectoryId = nonNull(parentDirectory) ? parentDirectory.getId() : null;
        List<Long> childDirectoryIds = childDirectories.stream().map(Directory::getId).toList();
        List<Long> pageIds = pages.stream().map(Page::getId).toList();

        return "Directory{" +
                "id=" + id +
                ", owner.id=" + owner.getId() +
                ", parentDirectory.id=" + parentDirectoryId +
                ", childDirectory.ids=" + childDirectoryIds +
                ", pages=" + pageIds +
                '}';
    }
}
