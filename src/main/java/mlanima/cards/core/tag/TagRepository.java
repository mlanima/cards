package mlanima.cards.core.tag;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, String> {
    List<Tag> findByUserId(Long userId);
    Optional<Tag> findByUserIdAndName(Long userId, String tagName);
    List<Tag> findByUserIdAndNameLikeIgnoreCase(Long userId, String name);
}
