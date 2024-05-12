package wiki.service.tag;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.dto.tag.NewTagDto;
import wiki.model.Tag;
import wiki.model.User;
import wiki.service.tag.api.TagCreator;
import wiki.service.tag.api.TagStore;
import wiki.service.user.api.AuthService;

import java.util.Optional;

@Service
public class TagCreatorImpl implements TagCreator {

    private final AuthService authService;
    private final TagStore tagStore;

    public TagCreatorImpl(AuthService authService,
                          TagStore tagStore) {
        this.authService = authService;
        this.tagStore = tagStore;
    }

    @Override
    @Transactional
    public Long create(NewTagDto request) {
        User currentUser = authService.getCurrentUser();
        Optional<Tag> existedTag = tagStore.findByName(request.getName(), currentUser);
        if (existedTag.isPresent()){
            throw new IllegalArgumentException("Tag already exists");
        }
        Tag tag = new Tag();
        tag.setName(request.getName());
        tag.setOwner(currentUser);
        tagStore.save(tag);

        return tag.getId();
    }
}
