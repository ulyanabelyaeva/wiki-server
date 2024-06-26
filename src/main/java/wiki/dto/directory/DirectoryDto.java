package wiki.dto.directory;

import wiki.dto.page.PageDto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class DirectoryDto {

    private String id;
    private String name;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private List<PageDto> pages = new ArrayList<>();
    private List<DirectoryDto> childDirectories = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<PageDto> getPages() {
        return pages;
    }

    public void setPages(List<PageDto> pages) {
        this.pages = pages;
    }

    public List<DirectoryDto> getChildDirectories() {
        return childDirectories;
    }

    public void setChildDirectories(List<DirectoryDto> childDirectories) {
        this.childDirectories = childDirectories;
    }

    @Override
    public String toString() {
        return "DirectoryDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", pages=" + pages +
                ", childDirectories=" + childDirectories +
                '}';
    }
}
