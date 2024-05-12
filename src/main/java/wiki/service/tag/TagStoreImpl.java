package wiki.service.tag;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wiki.model.Tag;
import wiki.model.User;
import wiki.repository.TagRepository;
import wiki.service.tag.api.TagStore;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Service
public class TagStoreImpl implements TagStore {

    private final TagRepository tagRepository;

    public TagStoreImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    @Transactional(propagation = MANDATORY)
    public Optional<Tag> findByName(String name, User currentUser) {
        return tagRepository.findByNameAndOwner(name, currentUser);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> T readTag(Long id,
                         Function<Tag, T> mapper) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tag don`t exists"));
        return mapper.apply(tag);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> List<T> readUserTags(User currentUser,
                                    Function<Tag, T> mapper) {
        List<Tag> tags = tagRepository.findByOwner(currentUser);
        return tags.stream()
                .map(mapper)
                .toList();
    }
}
