package wiki.dto.page;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public class NewPageDto {

    private String directoryId;
    @NotNull
    private String name;
    @NotNull
    private ZonedDateTime createdAt;

    public String getDirectoryId() {
        return directoryId;
    }

    public void setDirectoryId(String directoryId) {
        this.directoryId = directoryId;
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
        return "NewPageDto{" +
                "directoryId='" + directoryId + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
