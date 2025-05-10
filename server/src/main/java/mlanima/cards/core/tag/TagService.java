package mlanima.cards.core.tag;

import mlanima.cards.core.user.UserService;
import mlanima.cards.dtos.requests.TagRequest;
import mlanima.cards.exceptions.observed.TagNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;
    private final UserService userService;

    @Autowired
    public TagService(TagRepository tagRepository, UserService userService) {
        this.tagRepository = tagRepository;
        this.userService = userService;
    }

    public List<Tag> getTags() {
        return tagRepository.findByUserId(userService.getUser().getId());
    }

    public Tag getTag(String tagName) {
        return tagRepository.findByUserIdAndName(userService.getUser().getId(), tagName).orElseThrow();
    }

    public Tag createTag(TagRequest d) {
        return tagRepository.save( new Tag(d.getName(), userService.getUser()) );
    }

    public Tag updateTag(String tagName, TagRequest d) {
        Tag tag = tagRepository
                .findByUserIdAndName(userService.getUser().getId(), tagName)
                .orElseThrow(TagNotFoundException::new);

        tag.setName(d.getName());

        return tagRepository.save(tag);
    }

    public List<Tag> findTagsByNamePart(String tagPart) {
        return tagRepository.findByUserIdAndNameLikeIgnoreCase(userService.getUser().getId(), tagPart);
    }

    public void deleteTag(String tagName) {
        Tag tag = tagRepository.findByUserIdAndName(userService.getUser().getId(), tagName).orElseThrow(
                TagNotFoundException::new
        );

        tagRepository.delete(tag);
    }
}
