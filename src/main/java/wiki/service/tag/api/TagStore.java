package wiki.service.tag.api;

import wiki.model.Tag;
import wiki.model.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface TagStore {

    void save(Tag tag);

    Optional<Tag> findByName(String name,
                             User currentUser);

    <T> T readTag(Long id,
                  Function<Tag, T> mapper);

    <T> List<T> readUserTags(User currentUser,
                             Function<Tag, T> mapper);
}
