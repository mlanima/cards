package mlanima.cards.core.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByDeckId(Long deckId);
    boolean findByPhrase(String phrase);

    @Query("SELECT c FROM Card c WHERE c.deck.user.id = :userId AND c.deck.name = :deckName")
    List<Card> findByUserIdAndDeckName(@Param("userId") Long userId,@Param("deckName") Long deckName);

    @Query("SELECT c FROM Card c WHERE c.deck.user.id = :userId AND  c.deck.name = :deckName AND c.id = :id ")
    Optional<Card> findByUserIdAndDeckNameAndId(@Param("userId") Long userId, @Param("deckName") Long deckName, @Param("id") Long id);


}
