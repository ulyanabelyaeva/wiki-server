package wiki.dto.page;

import java.time.ZonedDateTime;
import java.util.UUID;

public class PageDto {

    protected String id;
    protected String name;
    protected UUID fileUUID;
    protected ZonedDateTime createdAt;
    protected ZonedDateTime updatedAt;

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

    @Override
    public String toString() {
        return "PageDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fileUUID=" + fileUUID +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
