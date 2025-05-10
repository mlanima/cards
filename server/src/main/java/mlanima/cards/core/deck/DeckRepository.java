package mlanima.cards.core.deck;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeckRepository extends JpaRepository<Deck, Long> {
    List<Deck> findByUserId(Long userId);
    Optional<Deck> findByUserIdAndId(Long userId, Long id);

}
