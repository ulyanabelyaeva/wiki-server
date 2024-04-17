package wiki.service.directory;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.directory.DirectoryDto;
import wiki.model.Directory;
import wiki.service.directory.api.DirectoryMapper;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Component
public class DirectoryMapperImpl implements DirectoryMapper {


    @Override
    @Transactional(propagation = MANDATORY)
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
        if (directory.getChildDirectories().isEmpty()){
            return;
        }
        for (Directory childDirectory: directory.getChildDirectories()) {
            DirectoryDto childDto = new DirectoryDto();
            dto.getChildDirectories().add(childDto);
            this.createDtoRecursive(childDirectory, childDto);
        }
    }
}
