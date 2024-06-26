package wiki.dto.directory;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class UpdateDirectoryDto {

    @NotNull
    private String id;
    private String name;
    @NotNull
    private ZonedDateTime updatedAt;

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

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UpdateDirectoryDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
