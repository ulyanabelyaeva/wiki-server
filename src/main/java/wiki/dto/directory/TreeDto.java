package wiki.dto.directory;

import wiki.dto.page.PageDto;

import java.util.ArrayList;
import java.util.List;

public class TreeDto {

    private List<DirectoryDto> directories = new ArrayList<>();

    private List<PageDto> pages = new ArrayList<>();

    public List<DirectoryDto> getDirectories() {
        return directories;
    }

    public void setDirectories(List<DirectoryDto> directories) {
        this.directories = directories;
    }

    public List<PageDto> getPages() {
        return pages;
    }

    public void setPages(List<PageDto> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "TreeDto{" +
                "directories=" + directories +
                ", pages=" + pages +
                '}';
    }
}
