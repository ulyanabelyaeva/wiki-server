package wiki.dto.page;

import wiki.dto.tag.TagDto;

import java.util.ArrayList;
import java.util.List;

public class PageAdvancedDto extends PageDto{

    private String content;
    private List<TagDto> tags = new ArrayList<>();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "PageAdvancedDto{" +
                "content='" + content + '\'' +
                ", tags=" + tags +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", fileUUID=" + fileUUID +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
