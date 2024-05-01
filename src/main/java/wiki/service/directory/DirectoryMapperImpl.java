package wiki.service.directory;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.directory.DirectoryDto;
import wiki.dto.page.PageDto;
import wiki.model.Directory;
import wiki.service.directory.api.DirectoryMapper;
import wiki.service.page.api.PageMapper;

import java.util.List;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Component
@Transactional(propagation = MANDATORY)
public class DirectoryMapperImpl implements DirectoryMapper {

    private final PageMapper pageMapper;

    public DirectoryMapperImpl(PageMapper pageMapper) {
        this.pageMapper = pageMapper;
    }


    @Override
    public DirectoryDto toDto(Directory directory) {
        DirectoryDto dto = new DirectoryDto();
        this.createDtoRecursive(directory, dto);
        return dto;
    }

    private void createDtoRecursive(Directory directory,
                                    DirectoryDto dto) {
        dto.setId(String.valueOf(directory.getId()));
        dto.setName(directory.getName());
        dto.setCreatedAt(directory.getCreatedAt());
        dto.setUpdatedAt(directory.getUpdatedAt());
        List<PageDto> pageDtoList = directory.getPages().stream()
                .map(pageMapper::toDto)
                .toList();
        dto.setPages(pageDtoList);
        if (directory.getChildDirectories().isEmpty()) {
            return;
        }
        for (Directory childDirectory : directory.getChildDirectories()) {
            DirectoryDto childDto = new DirectoryDto();
            dto.getChildDirectories().add(childDto);
            this.createDtoRecursive(childDirectory, childDto);
        }
    }
}
