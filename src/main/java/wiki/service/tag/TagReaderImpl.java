package wiki.service.tag;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.tag.TagDto;
import wiki.model.User;
import wiki.service.tag.api.TagMapper;
import wiki.service.tag.api.TagReader;
import wiki.service.tag.api.TagStore;
import wiki.service.user.api.AuthService;

import java.util.List;

@Service
public class TagReaderImpl implements TagReader {

    private final AuthService authService;
    private final TagMapper tagMapper;
    private final TagStore tagStore;

    public TagReaderImpl(AuthService authService,
                         TagMapper tagMapper,
                         TagStore tagStore) {
        this.authService = authService;
        this.tagMapper = tagMapper;
        this.tagStore = tagStore;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TagDto> readTags() {
        User user = authService.getCurrentUser();
        return tagStore.readUserTags(
                user,
                tagMapper::toDto);
    }
}
