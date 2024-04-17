package wiki.dto.directory;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class NewDictionaryDto {

    private String parentDirectoryId;
    @NotNull
    private String name;
    @NotNull
    private ZonedDateTime createdAt;

    public String getParentDirectoryId() {
        return parentDirectoryId;
    }

    public void setParentDirectoryId(String parentDirectoryId) {
        this.parentDirectoryId = parentDirectoryId;
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

    @Override
    public String toString() {
        return "NewDictionaryDto{" +
                "parentDirectoryId='" + parentDirectoryId + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
