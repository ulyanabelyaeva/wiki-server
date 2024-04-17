package wiki.dto.directory;

import java.util.ArrayList;
import java.util.List;

public class TreeDto {

    private List<DirectoryDto> directories = new ArrayList<>();

    public List<DirectoryDto> getDirectories() {
        return directories;
    }

    public void setDirectories(List<DirectoryDto> directories) {
        this.directories = directories;
    }

    @Override
    public String toString() {
        return "TreeDto{" +
                "directories=" + directories +
                '}';
    }
}
