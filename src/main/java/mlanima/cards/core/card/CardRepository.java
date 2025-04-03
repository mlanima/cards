package mlanima.cards.core.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByDeckId(Long deckId);
    boolean findByPhrase(String phrase);

    @Query("SELECT c FROM Card c WHERE c.deck.user.id = :userId AND c.deck.id = :deckId")
    List<Card> findByUserIdAndDeckId(@Param("userId") Long userId,@Param("deckId") Long deckId);

    @Query("SELECT c FROM Card c WHERE c.deck.user.id = :userId AND  c.deck.id = :deckId AND c.id = :id ")
    Optional<Card> findByUserIdAndDeckIdAndId(@Param("userId") Long userId,@Param("deckId") Long deckId,@Param("id") Long id);


}
